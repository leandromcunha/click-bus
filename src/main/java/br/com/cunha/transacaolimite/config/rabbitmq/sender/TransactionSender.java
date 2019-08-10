/**
 * Leandro Marques da Cunha.
 * Projeto HubFintech
 * Teste Tecnico - Transação com cartão de credito
 */
package br.com.cunha.transacaolimite.config.rabbitmq.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cunha.transacaolimite.config.APIProperties;
import br.com.cunha.transacaolimite.handler.exception.ProcessException;
import br.com.cunha.transacaolimite.services.entities.TransCard;

/**
 * Classe TransactionSender responsável por enviar as informações da transações
 * para a fila RabbitMQ.
 *
 * @author <a href="mailto:leandromcunha@gmail.com>Leandro Marques da Cunha</a>
 * @version $Id$ Leandro
 */
@Component
public class TransactionSender {
    
    private static final Logger LOG = LoggerFactory.getLogger( TransactionSender.class );
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Autowired
    private APIProperties hubFintechProperty;
    
    /**
     * Enviar a transação para a fila de gração na base de dados
     *
     * @param transCard
     */
    public void send( final TransCard transCard ) {
        try{
            final String json = this.objectMapper.writeValueAsString( transCard );
            this.rabbitTemplate.convertAndSend( this.hubFintechProperty.getRabbitSaveTransaction(), json.getBytes() );
        }catch( final Exception e ){
            TransactionSender.LOG.error( "Erro ao tentar enviar a transação para a fila: {}", e.getMessage() );
            throw new ProcessException( "Erro ao tentar enviar para a fila de gravação", e );
        }
    }
}
