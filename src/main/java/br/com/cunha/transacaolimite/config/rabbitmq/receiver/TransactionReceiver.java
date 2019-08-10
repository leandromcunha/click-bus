/**
 * Leandro Marques da Cunha.
 * Projeto HubFintech
 * Teste Tecnico - Transação com cartão de credito
 */
package br.com.cunha.transacaolimite.config.rabbitmq.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cunha.transacaolimite.handler.exception.ProcessingErrorException;
import br.com.cunha.transacaolimite.services.TransactionCardService;
import br.com.cunha.transacaolimite.services.entities.TransCard;

/**
 * Classe TransactionReceiver responsável por ler a fila de transação para
 * persistir na base de dados.
 *
 * @author <a href="mailto:leandromcunha@gmail.com>Leandro Marques da Cunha</a>
 * @version $Id$ Leandro
 */
@Component
@RabbitListener( queues = "rabbit-save-transaction" )
public class TransactionReceiver {
    
    private static final Logger LOG = LoggerFactory.getLogger( TransactionReceiver.class );
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private TransactionCardService transactionCardService;
    
    @RabbitHandler
    public void receive( final byte[] mensagem ) {
        try{
            final TransCard transCard = this.objectMapper.readValue( mensagem, TransCard.class );
            
            this.transactionCardService.save( transCard );
            
            TransactionReceiver.LOG.debug( "Transação gravada com sucesso {}:", transCard.getIdTransCard() );
            
        }catch( final Exception ex ){
            TransactionReceiver.LOG.error( "Erro no recebimento/gravação da transação: {}", ex.getMessage() );
            throw new ProcessingErrorException( "Erro no recebimento/gravação da transação", ex );
        }
    }
}
