/**
 * Leandro Marques da Cunha.
 * Projeto HubFintech
 * Teste Tecnico - Transação com cartão de credito
 */
package br.com.cunha.transacaolimite.services.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * Classe OperationCardDTO responsável por receber as informações da transação.
 *
 * @author <a href="mailto:leandromcunha@gmail.com>Leandro Marques da Cunha</a>
 * @version $Id$ Leandro
 */
@RedisHash( "OperationCardDTO" )
public class OperationCardDTO implements Serializable {
    
    private static final long serialVersionUID = -8507614249316775408L;
    
    @Id
    private String action;
    
    private String cardnumber;
    
    private BigDecimal amount;
    
    // @JsonFormat( shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-ddTHH:mm:ss" )
    // @JsonDeserialize( using=JsonDateTimeDeserializer.class )
    @DateTimeFormat( iso = ISO.DATE_TIME )
    private LocalDateTime data;
    
    /**
     * Método get do atributo action
     *
     * @return O valor do atributo action
     */
    public String getAction() {
        return this.action;
    }
    
    /**
     * Método set do atributo action
     *
     * @param action
     *                   Valor para setar no atributo action
     */
    public void setAction( final String action ) {
        this.action = action;
    }
    
    /**
     * Método get do atributo cardnumber
     *
     * @return O valor do atributo cardnumber
     */
    public String getCardnumber() {
        return this.cardnumber;
    }
    
    /**
     * Método set do atributo cardnumber
     *
     * @param cardnumber
     *                       Valor para setar no atributo cardnumber
     */
    public void setCardnumber( final String cardnumber ) {
        this.cardnumber = cardnumber;
    }
    
    /**
     * Método get do atributo amount
     *
     * @return O valor do atributo amount
     */
    public BigDecimal getAmount() {
        return this.amount;
    }
    
    /**
     * Método set do atributo amount
     *
     * @param amount
     *                   Valor para setar no atributo amount
     */
    public void setAmount( final BigDecimal amount ) {
        this.amount = amount;
    }
    
    public LocalDateTime getData() {
        return this.data;
    }
    
    public void setData( final LocalDateTime data ) {
        this.data = data;
    }
    
    @Override
    public String toString() {
        return "OperationCardDTO [action=" + this.action +
                ", cardnumber=" +
                this.cardnumber +
                ", amount=" +
                this.amount +
                ", data=" +
                this.data +
                "]";
    }
    
}
