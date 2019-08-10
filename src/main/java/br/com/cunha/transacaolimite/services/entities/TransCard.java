/**
 * Leandro Marques da Cunha.
 * Projeto HubFintech
 * Teste Tecnico - Transação com cartão de credito
 */
package br.com.cunha.transacaolimite.services.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Classe Card responsável por representar a entidade TB_TRANS_CARD.
 *
 * @author <a href="mailto:leandromcunha@gmail.com>Leandro Marques da Cunha</a>
 * @version $Id$ Leandro
 */
@Entity
@Table( name = "TB_TRANS_CARD" )
@SequenceGenerator( name = "SEQ_TRANS_CARD", sequenceName = "SEQ_TRANS_CARD", initialValue = 1, allocationSize = 1 )
public class TransCard implements Serializable {
    
    /**
     * Atributo serialVersionUID do tipo long responsável por guarda o valor que
     * representa $field$.
     */
    private static final long serialVersionUID = 2077666456210684698L;
    
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "SEQ_TRANS_CARD" )
    @Column( name = "ID_TRANS_CARD" )
    private Long idTransCard;
    
    @ManyToOne
    @JoinColumn( name = "id_card", referencedColumnName = "id_card" )
    private Card card;
    
    @Column( name = "VL_AMOUNT", nullable = false )
    private BigDecimal amount;
    
    @Column( name = "DH_TRANS", nullable = false )
    @Temporal( TemporalType.TIMESTAMP )
    private Date dateTransaction;
    
    @Column( name = "CD_AUTHOR", nullable = false )
    private String codeAuthorization;
    
    public TransCard() {
    }
    
    public TransCard( final Long idTransCard, final Card card, final BigDecimal amount, final Date dateTransaction,
            final String codeAuthorization ) {
        super();
        this.idTransCard = idTransCard;
        this.card = card;
        this.amount = amount;
        this.dateTransaction = dateTransaction;
        this.codeAuthorization = codeAuthorization;
    }
    
    /**
     * Método get do atributo idTransCard
     *
     * @return O valor do atributo idTransCard
     */
    public Long getIdTransCard() {
        return this.idTransCard;
    }
    
    /**
     * Método set do atributo idTransCard
     *
     * @param idTransCard
     *                        Valor para setar no atributo idTransCard
     */
    public void setIdTransCard( final Long idTransCard ) {
        this.idTransCard = idTransCard;
    }
    
    /**
     * Método get do atributo card
     *
     * @return O valor do atributo card
     */
    public Card getCard() {
        return this.card;
    }
    
    /**
     * Método set do atributo card
     *
     * @param card
     *                 Valor para setar no atributo card
     */
    public void setCard( final Card card ) {
        this.card = card;
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
    
    /**
     * Método get do atributo dateTransaction
     *
     * @return O valor do atributo dateTransaction
     */
    public Date getDateTransaction() {
        return this.dateTransaction;
    }
    
    /**
     * Método set do atributo dateTransaction
     *
     * @param dateTransaction
     *                            Valor para setar no atributo dateTransaction
     */
    public void setDateTransaction( final Date dateTransaction ) {
        this.dateTransaction = dateTransaction;
    }
    
    /**
     * Método get do atributo codeAuthorization
     *
     * @return O valor do atributo codeAuthorization
     */
    public String getCodeAuthorization() {
        return this.codeAuthorization;
    }
    
    /**
     * Método set do atributo codeAuthorization
     *
     * @param codeAuthorization
     *                              Valor para setar no atributo codeAuthorization
     */
    public void setCodeAuthorization( final String codeAuthorization ) {
        this.codeAuthorization = codeAuthorization;
    }
    
    @Override
    public String toString() {
        return "TransCard [idTransCard=" + this.idTransCard +
                ", card=" +
                this.card +
                ", amount=" +
                this.amount +
                ", dateTransaction=" +
                this.dateTransaction +
                ", codeAuthorization=" +
                this.codeAuthorization +
                "]";
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = ( prime * result ) + ( ( this.card == null ) ? 0 : this.card.hashCode() );
        result = ( prime * result ) + ( ( this.idTransCard == null ) ? 0 : this.idTransCard.hashCode() );
        return result;
    }
    
    @Override
    public boolean equals( final Object obj ) {
        if( this == obj ){
            return true;
        }
        if( obj == null ){
            return false;
        }
        if( this.getClass() != obj.getClass() ){
            return false;
        }
        final TransCard other = ( TransCard )obj;
        if( this.card == null ){
            if( other.card != null ){
                return false;
            }
        }else if( !this.card.equals( other.card ) ){
            return false;
        }
        if( this.idTransCard == null ){
            if( other.idTransCard != null ){
                return false;
            }
        }else if( !this.idTransCard.equals( other.idTransCard ) ){
            return false;
        }
        return true;
    }
    
}
