/**
 * Leandro Marques da Cunha.
 * Projeto HubFintech
 * Teste Tecnico - Transação com cartão de credito
 */
package br.com.cunha.transacaolimite.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.cunha.transacaolimite.handler.exception.ProcessingErrorException;
import br.com.cunha.transacaolimite.repository.TransactionCardRepository;
import br.com.cunha.transacaolimite.services.TransactionCardService;
import br.com.cunha.transacaolimite.services.entities.TransCard;

/**
 * Classe TransactionCardServiceImpl responsável por gerencias as transações
 * efetuado com o cartão com a base de dados.
 *
 * @author <a href="mailto:leandromcunha@gmail.com>Leandro Marques da Cunha</a>
 * @version $Id$ Leandro
 */
@Service
@Transactional
public class TransactionCardServiceImpl implements TransactionCardService {
    
    private static final Logger LOG = LoggerFactory.getLogger( TransactionCardService.class );
    
    @Autowired
    private TransactionCardRepository repository;
    
    /*
     * (non-Javadoc)
     *
     * @see
     * br.com.cunha.hub.services.TransactionCardService#save(br.com.cunha.hub.
     * services.entities.TransCard)
     */
    @Override
    public void save( final TransCard transCard ) {
        try{
            this.repository.save( transCard );
        }catch( final Exception e ){
            TransactionCardServiceImpl.LOG.error( "Erro ao tentar gravar a transação {} ", e );
            throw new ProcessingErrorException( "Erro ao tentar gravar uma transação", e );
        }
    }
    
    /*
     * (non-Javadoc)
     *
     * @see
     * br.com.cunha.hub.services.TransactionCardService#authorizationCode(br.com
     * .cunha.hub.services.entities.TransCard)
     */
    @Override
    public String authorizationCode() {
        return String.format( "%06d", ( int ) ( Math.random() * 1000000 ) );
    }
}
