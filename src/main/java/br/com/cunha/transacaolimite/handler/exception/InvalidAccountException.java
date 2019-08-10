/**
 * Leandro Marques da Cunha.
 * Projeto HubFintech
 * Teste Tecnico - Transação com cartão de credito
 */
package br.com.cunha.transacaolimite.handler.exception;

import br.com.cunha.transacaolimite.services.enums.TransactionCodeEnum;

/**
 * Classe InvalidAccountException responsável por lançar a exceção conta
 * invalida.
 *
 * @author <a href="mailto:leandromcunha@gmail.com>Leandro Marques da Cunha</a>
 * @version $Id$ Leandro
 */
public class InvalidAccountException extends RuntimeException implements ApiExeception {
    
    /**
     * Atributo serialVersionUID do tipo long responsável por guarda o valor que
     * representa $field$.
     */
    private static final long serialVersionUID = -3632352189545389944L;
    
    public InvalidAccountException( final String message, final Throwable e ) {
        super( message, e );
    }
    
    public InvalidAccountException( final String message ) {
        this( message, null );
    }
    
    public InvalidAccountException( final Throwable e ) {
        this( e.getMessage(), e );
    }
    
    /**
     * Método get do atributo transactionCodeEnum
     *
     * @return O valor do atributo transactionCodeEnum
     */
    @Override
    public TransactionCodeEnum getTransactionCodeEnum() {
        return TransactionCodeEnum.INVALID_ACCOUNT;
    }
}
