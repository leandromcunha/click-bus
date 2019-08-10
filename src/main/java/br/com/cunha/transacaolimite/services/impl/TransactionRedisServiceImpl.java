package br.com.cunha.transacaolimite.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cunha.transacaolimite.handler.exception.ProcessException;
import br.com.cunha.transacaolimite.repository.redis.TransactionRedisRepository;
import br.com.cunha.transacaolimite.services.TransactionRedisService;
import br.com.cunha.transacaolimite.services.dto.TransactioCardDTO;

/**
 * Serviço responsavel por gerencias as transações com o redis
 *
 * @author Leandro
 */
@Service
public class TransactionRedisServiceImpl implements TransactionRedisService {
    
    private static final Logger LOG = LoggerFactory.getLogger( TransactionRedisService.class );
    
    @Autowired
    private TransactionRedisRepository transactionRedisRepository;
    
    @Override
    public Optional<TransactioCardDTO> findById( final String nuCard ) {
        try{
            return this.transactionRedisRepository.findById( nuCard );
        }catch( final Exception e ){
            TransactionRedisServiceImpl.LOG.error( "Erro ao tentar recuperar a transação para do redis: {}",
                    e.getMessage() );
            throw new ProcessException( "Erro ao tentar recuperar a transação para do redis", e );
        }
    }
    
    @Override
    public TransactioCardDTO save( final TransactioCardDTO transactioCardDTO ) {
        try{
            return this.transactionRedisRepository.save( transactioCardDTO );
        }catch( final Exception e ){
            TransactionRedisServiceImpl.LOG.error( "Erro ao tentar enviar a transação para o redis: {}",
                    e.getMessage() );
            throw new ProcessException( "Erro ao tentar enviar para gravação no redis", e );
        }
    }
}