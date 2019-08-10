/**
 * Leandro Marques da Cunha.
 * Projeto HubFintech
 * Teste Tecnico - Transação com cartão de credito
 */
package br.com.cunha.transacaolimite.services.dto.redis;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

/**
 * Classe TransactionLoadDTO responsável por.
 *
 * @author <a href="mailto:leandromcunha@gmail.com>Leandro Marques da Cunha</a>
 * @version $Id$ Leandro
 */
@RedisHash( "TransactionLoadDTO" )
public class TransactionLoadDTO implements Serializable {
    /**
     * Atributo serialVersionUID do tipo long responsável por guarda o valor que
     * representa $field$.
     */
    private static final long serialVersionUID = -1695958983884269498L;
    
    @Id
    private Long id;
    
    public TransactionLoadDTO() {
        
    }
    
    public TransactionLoadDTO( final Long id ) {
        this.id = id;
    }
    
    /**
     * Método get do atributo id
     *
     * @return O valor do atributo id
     */
    public Long getId() {
        return this.id;
    }
    
    public void setId( final Long id ) {
        this.id = id;
    }
}
