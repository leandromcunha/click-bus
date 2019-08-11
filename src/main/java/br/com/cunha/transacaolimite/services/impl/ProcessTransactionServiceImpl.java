/**
 * Leandro Marques da Cunha.
 * Projeto HubFintech
 * Teste Tecnico - Transação com cartão de credito
 */
package br.com.cunha.transacaolimite.services.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cunha.transacaolimite.config.APIProperties;
import br.com.cunha.transacaolimite.config.rabbitmq.sender.TransactionSender;
import br.com.cunha.transacaolimite.handler.exception.InsufficientFundsException;
import br.com.cunha.transacaolimite.handler.exception.InvalidAccountException;
import br.com.cunha.transacaolimite.handler.exception.ProcessingErrorException;
import br.com.cunha.transacaolimite.repository.custom.TransactionCardCustomRepository;
import br.com.cunha.transacaolimite.services.ProcessTransactionService;
import br.com.cunha.transacaolimite.services.TransactionCardService;
import br.com.cunha.transacaolimite.services.TransactionRedisService;
import br.com.cunha.transacaolimite.services.dto.OperationCardDTO;
import br.com.cunha.transacaolimite.services.dto.OperationResultDTO;
import br.com.cunha.transacaolimite.services.dto.TransactioCardDTO;
import br.com.cunha.transacaolimite.services.dto.TransactionDTO;
import br.com.cunha.transacaolimite.services.entities.Card;
import br.com.cunha.transacaolimite.services.entities.TransCard;
import br.com.cunha.transacaolimite.services.enums.TransactionCodeEnum;

/**
 * Classe ProcessTransactionServiceImpl responsável por.
 *
 * @author <a href="mailto:leandromcunha@gmail.com>Leandro Marques da Cunha</a>
 * @version $Id$ Leandro
 */
@Service
@Transactional
public class ProcessTransactionServiceImpl implements ProcessTransactionService {
    
    private static final Logger LOG = LoggerFactory.getLogger( ProcessTransactionService.class );
    
    @Autowired
    private TransactionRedisService transactionRedisService;
    
    @Autowired
    private APIProperties properties;
    
    @Autowired
    private TransactionSender transactionSender;
    
    @Autowired
    private TransactionCardCustomRepository transactionCardCustomRepository;
    
    @Autowired
    public TransactionCardService transactionCardService;
    
    /*
     * (non-Javadoc)
     *
     * @see
     * br.com.cunha.hub.services.ProcessTransactionService#process(br.com.cunha.
     * hub.services.dto.OperationCardDTO)
     */
    @Override
    public OperationResultDTO process( final OperationCardDTO operationCardDTO ) {
        
        ProcessTransactionServiceImpl.LOG.info( "Verificando a ação: {} ", operationCardDTO.getAction() );
        if( !this.properties.getActions().contains( operationCardDTO.getAction() ) ){
            throw new ProcessingErrorException( "Ação invalida", null );
        }
        
        ProcessTransactionServiceImpl.LOG.info( "Recuperando as informações do cartão no Redis: " );
        final TransactioCardDTO transaction = this.transactions( operationCardDTO.getCardnumber() );
        
        ProcessTransactionServiceImpl.LOG.info( "Verificando o saldo: " );
        if( transaction.getAvailableAmount().compareTo( operationCardDTO.getAmount() ) == -1 ){
            ProcessTransactionServiceImpl.LOG.error( "Informações: Saldo Insuficiente" );
            throw new InsufficientFundsException( "Saldo Insuficiente" );
        }
        
        final TransactionDTO transactionDTO = this.transactionDTO( operationCardDTO, transaction );
        
        ProcessTransactionServiceImpl.LOG.info( "Gerando codigo de autorização: " );
        transactionDTO.setAuthorizationCode( this.transactionCardService.authorizationCode() );
        
        final TransCard transCard = this.transCard( transaction, transactionDTO );
        
        ProcessTransactionServiceImpl.LOG.info( "Enviando para a fila- Persistir na base de dados: " );
        this.transactionSender.send( transCard );
        
        ProcessTransactionServiceImpl.LOG.info( "Enviando para o Redis: " );
        this.transactionRedisService.save( transaction );
        
        return this.operationResultDTO( operationCardDTO, transactionDTO );
        
    }
    
    /**
     * Prepara o retorno de sucesso
     *
     * @param operationCardDTO
     * @param transactionDTO
     * @return
     */
    private OperationResultDTO operationResultDTO( final OperationCardDTO operationCardDTO,
            final TransactionDTO transactionDTO ) {
        final OperationResultDTO result = new OperationResultDTO();
        result.setAction( operationCardDTO.getAction() );
        result.setAuthorizationCode( transactionDTO.getAuthorizationCode() );
        result.setCode( TransactionCodeEnum.APPROVED.toString() );
        return result;
    }
    
    /**
     * Converte em entidade para enviar para filha de gravação
     *
     * @param transaction
     * @param transactionDTO
     * @return
     */
    private TransCard transCard( final TransactioCardDTO transaction, final TransactionDTO transactionDTO ) {
        final TransCard transCard = new TransCard();
        transCard.setAmount( transactionDTO.getAmount() );
        transCard.setCodeAuthorization( transactionDTO.getAuthorizationCode() );
        transCard.setDateTransaction( transactionDTO.getDate() );
        final Card card = new Card();
        card.setIdCard( transaction.getIdCard() );
        card.setNuCard( transaction.getNuCard() );
        card.setLimit( transaction.getAvailable() );
        transCard.setCard( card );
        return transCard;
    }
    
    /**
     * Retorna a lista de transações conforme limite parametrizado
     *
     * @param operationCardDTO
     * @param transaction
     * @return
     */
    private TransactionDTO transactionDTO( final OperationCardDTO operationCardDTO,
            final TransactioCardDTO transaction ) {
        
        List<TransactionDTO> transactionList = transaction.getTransactions();
        if( transactionList == null ){
            transactionList = new ArrayList<>();
        }
        
        final TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAmount( operationCardDTO.getAmount() );
        
        transactionDTO.setDate( new Date() );
        transaction.setAvailableAmount( transaction.getAvailableAmount().subtract( transactionDTO.getAmount() ) );
        
        transactionList.add( transactionDTO );
        
        final Comparator<TransactionDTO> comparator = new Comparator<TransactionDTO>() {
            @Override
            public int compare( final TransactionDTO o1, final TransactionDTO o2 ) {
                return o2.getDate().compareTo( o1.getDate() );
            }
        };
        
        transactionList.sort( comparator );
        
        final int left = transactionList.size() > this.properties.getMaxTransactionDetail()
                ? this.properties.getMaxTransactionDetail()
                : transactionList.size();
        
        transaction.setTransactions( transactionList.subList( 0, left ) );
        
        return transactionDTO;
    }
    
    /*
     * (non-Javadoc)
     *
     * @see
     * br.com.cunha.hub.services.ProcessTransactionService#transactions(java.
     * lang.String)
     */
    @Override
    public TransactioCardDTO transactions( final String nuCard ) {
        
        final Optional<TransactioCardDTO> optionalTransaction = this.transactionRedisService.findById( nuCard );
        
        if( optionalTransaction.isPresent() ){
            return optionalTransaction.get();
        }
        
        final TransactioCardDTO transactionCard = this.transactionCardCustomRepository
                .transactionCardByNuCard( nuCard );
        
        if( transactionCard == null ){
            throw new InvalidAccountException( "Conta Invalida" );
        }
        
        return transactionCard;
    }
}
