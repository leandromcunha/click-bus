/**
 * Leandro Marques da Cunha.
 * Projeto HubFintech
 * Teste Tecnico - Transação com cartão de credito
 *
 *
 */
package br.com.cunha.transacaolimite.handler.exception;

import br.com.cunha.transacaolimite.services.enums.TransactionCodeEnum;

/**
 * Classe HubLimitUnavailableException responsável por lançar a exceção limite
 * indisponivel.
 *
 * @author <a href="mailto:leandromcunha@gmail.com>Leandro Marques da Cunha</a>
 * @version $Id$ Leandro
 */
public class InsufficientFundsException extends RuntimeException implements ApiExeception {
    
    /**
     * Atributo serialVersionUID do tipo long responsável por guarda o valor que
     * representa $field$.
     */
    private static final long serialVersionUID = -4694147543553377913L;
    
    public InsufficientFundsException( final String message, final Throwable e ) {
        super( message, e );
    }
    
    public InsufficientFundsException( final String message ) {
        this( message, null );
    }
    
    public InsufficientFundsException( final Throwable e ) {
        this( e.getMessage(), e );
    }
    
    /**
     * Método get do atributo transactionCodeEnum
     *
     * @return O valor do atributo transactionCodeEnum
     */
    @Override
    public TransactionCodeEnum getTransactionCodeEnum() {
        return TransactionCodeEnum.INSUFFICIENT_FUNDS;
    }
}
