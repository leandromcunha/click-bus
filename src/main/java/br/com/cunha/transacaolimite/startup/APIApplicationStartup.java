/**
 * Leandro Marques da Cunha.
 * Projeto HubFintech
 * Teste Tecnico - Transação com cartão de credito
 */
package br.com.cunha.transacaolimite.startup;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.cunha.transacaolimite.repository.custom.TransactionCardCustomRepository;
import br.com.cunha.transacaolimite.repository.redis.TransactionLoadRedisRepository;
import br.com.cunha.transacaolimite.repository.redis.TransactionRedisRepository;
import br.com.cunha.transacaolimite.services.dto.TransactioCardDTO;
import br.com.cunha.transacaolimite.services.dto.redis.TransactionLoadDTO;

/**
 * Classe HubApplicationStartup responsável por carregar as transações no REDIS.
 *
 * @author <a href="mailto:leandromcunha@gmail.com>Leandro Marques da Cunha</a>
 * @version $Id$ Leandro
 */
@Component
public class APIApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {
    
    private static final Logger LOG = LoggerFactory.getLogger( APIApplicationStartup.class );
    
    @Autowired
    private TransactionLoadRedisRepository transactionLoadRedisRepository;
    
    @Autowired
    private TransactionRedisRepository transactionRedisRepository;
    
    @Autowired
    private TransactionCardCustomRepository transactionCardCustomRepository;
    
    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.context.ApplicationListener#onApplicationEvent(org.
     * springframework.context.ApplicationEvent)
     */
    @Override
    public void onApplicationEvent( final ContextRefreshedEvent event ) {
        
        APIApplicationStartup.LOG.info( "Iniciando a primeira carga" );
        final Optional<TransactionLoadDTO> trOptional = this.transactionLoadRedisRepository.findById( 1l );
        APIApplicationStartup.LOG.info( "Verifica se a primeira carga já ocorreu" );
        
        if( !trOptional.isPresent() ){
            
            APIApplicationStartup.LOG.info( "Flagando que está ocorrendo a primeira carga" );
            final TransactionLoadDTO transLoadRedis = new TransactionLoadDTO( 1L );
            this.transactionLoadRedisRepository.save( transLoadRedis );
            
            APIApplicationStartup.LOG.info( "Carregando as transação da base no redis" );
            final List<TransactioCardDTO> transLoad = this.transactionCardCustomRepository.transactionCardLoad();
            for( final TransactioCardDTO transactioCardDTO: transLoad ){
                APIApplicationStartup.LOG.info( "Carregando a transação: {} ", transactioCardDTO.getIdCard() );
                this.transactionRedisRepository.save( transactioCardDTO );
            }
        }
        APIApplicationStartup.LOG.info( "Fim do inicio da primeira carga" );
    }
}