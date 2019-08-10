/**
 * Leandro Marques da Cunha.
 * Projeto HubFintech
 * Teste Tecnico - Transação com cartão de credito
 */
package br.com.cunha.transacaolimite.services.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Classe TransactioCardRedisDTO responsável por representar as operação
 * realizdas e o status atual das transações efetuada com o cartão no ambiente
 * de cache redis.
 *
 * @author <a href="mailto:leandromcunha@gmail.com>Leandro Marques da Cunha</a>
 * @version $Id$ Leandro
 */

@RedisHash( "TransactioCardDTO" )
@ApiModel( value = "TransactioCardDTO", description = "Status atual das transações do cartão" )
public class TransactioCardDTO implements Serializable {
    
    /**
     * Atributo serialVersionUID do tipo long responsável por guarda o valor que
     * representa $field$.
     */
    private static final long serialVersionUID = -475309775401023731L;
    
    @Id
    @JsonProperty( value = "cardnumber" )
    @ApiModelProperty( "cardnumber - Numero do cartao" )
    private String id;
    
    @ApiModelProperty( "availableAmount - Limite Disponivel" )
    @JsonProperty( value = "availableAmount" )
    private BigDecimal availableAmount;
    
    @JsonIgnore
    @ApiModelProperty( "available - Limite bruto" )
    private BigDecimal available;
    
    @JsonIgnore
    private Long idCard;
    
    @JsonProperty( value = "transactions" )
    @ApiModelProperty( "transactions - Transações" )
    private List<TransactionDTO> transactions;
    
    /**
     * Método get do atributo nuCard
     *
     * @return O valor do atributo nuCard
     */
    public String getNuCard() {
        return this.getId();
    }
    
    /**
     * Método set do atributo nuCard
     *
     * @param nuCard
     *                   Valor para setar no atributo nuCard
     */
    public void setNuCard( final String nuCard ) {
        this.setId( nuCard );
    }
    
    /**
     * Método get do atributo availableAmount
     *
     * @return O valor do atributo availableAmount
     */
    public BigDecimal getAvailableAmount() {
        return this.availableAmount;
    }
    
    /**
     * Método set do atributo availableAmount
     *
     * @param availableAmount
     *                            Valor para setar no atributo availableAmount
     */
    public void setAvailableAmount( final BigDecimal availableAmount ) {
        this.availableAmount = availableAmount;
    }
    
    /**
     * Método get do atributo transactions
     *
     * @return O valor do atributo transactions
     */
    public List<TransactionDTO> getTransactions() {
        return this.transactions;
    }
    
    /**
     * Método set do atributo transactions
     *
     * @param transactions
     *                         Valor para setar no atributo transactions
     */
    public void setTransactions( final List<TransactionDTO> transactions ) {
        this.transactions = transactions;
    }
    
    public Long getIdCard() {
        return this.idCard;
    }
    
    public void setIdCard( final Long idCard ) {
        this.idCard = idCard;
    }
    
    public BigDecimal getAvailable() {
        return this.available;
    }
    
    public void setAvailable( final BigDecimal available ) {
        this.available = available;
    }
    
    public String getId() {
        return this.id;
    }
    
    public void setId( final String id ) {
        this.id = id;
    }
}
