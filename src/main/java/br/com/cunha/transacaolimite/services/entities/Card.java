/**
 * Leandro Marques da Cunha.
 * Projeto HubFintech
 * Teste Tecnico - Transação com cartão de credito
 */
package br.com.cunha.transacaolimite.services.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Classe Card responsável por representar a entidade TB_CARD.
 *
 * @author <a href="mailto:leandromcunha@gmail.com>Leandro Marques da Cunha</a>
 * @version $Id$ Leandro
 */
@Entity
@Table( name = "TB_CARD" )
@SequenceGenerator( name = "SEQ_CARD", sequenceName = "SEQ_CARD", initialValue = 1, allocationSize = 1 )
public class Card implements Serializable {
    
    /**
     * Atributo serialVersionUID do tipo long responsável por guarda o valor que
     * representa $field$.
     */
    private static final long serialVersionUID = 2077666456210684698L;
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "SEQ_CARD" )
    @Column( name = "ID_CARD" )
    private Long idCard;
    
    @Column( name = "NU_CARD", nullable = false, unique = true, length = 16 )
    private String nuCard;
    
    @Column( name = "VL_LIMIT", nullable = false )
    private BigDecimal limit;
    
    public Card() {
    }
    
    /**
     * Construtor padrão Card.java.
     *
     * @param idCard
     * @param nuCard
     * @param limit
     */
    public Card( final Long idCard, final String nuCard, final BigDecimal limit ) {
        super();
        this.idCard = idCard;
        this.nuCard = nuCard;
        this.limit = limit;
    }
    
    /**
     * Método get do atributo idCard - ID
     *
     * @return O valor do atributo idCard
     */
    public Long getIdCard() {
        return this.idCard;
    }
    
    /**
     * Método set do atributo idCard -ID
     *
     * @param idCard
     *                   Valor para setar no atributo idCard
     */
    public void setIdCard( final Long idCard ) {
        this.idCard = idCard;
    }
    
    /**
     * Método get do atributo nuCard - Numero do Cartão
     *
     * @return O valor do atributo nuCard
     */
    public String getNuCard() {
        return this.nuCard;
    }
    
    /**
     * Método set do atributo nuCard - Numero do Cartão
     *
     * @param nuCard
     *                   Valor para setar no atributo nuCard
     */
    public void setNuCard( final String nuCard ) {
        this.nuCard = nuCard;
    }
    
    /**
     * Método get do atributo limit - Limite Disponivel
     *
     * @return O valor do atributo limit
     */
    public BigDecimal getLimit() {
        return this.limit;
    }
    
    /**
     * Método set do atributo limit - Limite Disponivel
     *
     * @param limit
     *                  Valor para setar no atributo limit
     */
    public void setLimit( final BigDecimal limit ) {
        this.limit = limit;
    }
    
    @Override
    public String toString() {
        return "Card [idCard=" + this.idCard + ", nuCard=" + this.nuCard + ", limit=" + this.limit + "]";
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        final int result = ( prime * 1 ) + ( ( this.idCard == null ) ? 0 : this.idCard.hashCode() );
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
        final Card other = ( Card )obj;
        if( this.idCard == null ){
            if( other.idCard != null ){
                return false;
            }
        }else if( !this.idCard.equals( other.idCard ) ){
            return false;
        }
        return true;
    }
    
}
