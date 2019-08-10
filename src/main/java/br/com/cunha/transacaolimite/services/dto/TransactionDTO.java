/**
 * Leandro Marques da Cunha.
 * Projeto HubFintech
 * Teste Tecnico - Transação com cartão de credito
 */
package br.com.cunha.transacaolimite.services.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Classe TransactionDTO responsável por representar as transações do cartão.
 *
 * @author <a href="mailto:leandromcunha@gmail.com>Leandro Marques da Cunha</a>
 * @version $Id$ Leandro
 */
@ApiModel( value = "TransactionDTO", description = "Transações do cartão" )
// @RedisHash( "TransactionDTO" )
public class TransactionDTO implements Serializable {
    
    /**
     * Atributo serialVersionUID do tipo long responsável por guarda o valor que
     * representa $field$.
     */
    private static final long serialVersionUID = 893909813097289589L;
    
    @JsonIgnore
    @Id
    private Long idTransCard;
    
    @ApiModelProperty( "date" )
    @JsonProperty( "date" )
    private Date date;
    
    @ApiModelProperty( "amount" )
    @JsonProperty( "amount" )
    private BigDecimal amount;
    
    @JsonIgnore
    private String authorizationCode;
    
    /**
     * Método get do atributo date
     *
     * @return O valor do atributo date
     */
    public Date getDate() {
        return this.date;
    }
    
    /**
     * Método set do atributo date
     *
     * @param date
     *                 Valor para setar no atributo date
     */
    public void setDate( final Date date ) {
        this.date = date;
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
    
    public String getAuthorizationCode() {
        return this.authorizationCode;
    }
    
    public void setAuthorizationCode( final String authorizationCode ) {
        this.authorizationCode = authorizationCode;
    }
    
    public Long getIdTransCard() {
        return this.idTransCard;
    }
    
    public void setIdTransCard( final Long idTransCard ) {
        this.idTransCard = idTransCard;
    }
    
    @Override
    public String toString() {
        return "TransactionDTO [idTransCard=" + this.idTransCard +
                ", date=" +
                this.date +
                ", amount=" +
                this.amount +
                ", authorizationCode=" +
                this.authorizationCode +
                "]";
    }
    
}