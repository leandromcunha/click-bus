/**
 * Leandro Marques da Cunha.
 * Projeto HubFintech
 * Teste Tecnico - Transação com cartão de credito
 */
package br.com.cunha.transacaolimite.handler.exception;

import br.com.cunha.transacaolimite.services.enums.TransactionCodeEnum;

/**
 * Classe HubProcessException responsável por lançar a exceção generica.
 *
 * @author <a href="mailto:leandromcunha@gmail.com>Leandro Marques da Cunha</a>
 * @version $Id$ Leandro
 */
public class ProcessException extends RuntimeException implements ApiExeception {
    
    /**
     * Atributo serialVersionUID do tipo long responsável por guarda o valor que
     * representa $field$.
     */
    private static final long serialVersionUID = -3157268861766431642L;
    
    public ProcessException( final String message, final Throwable e ) {
        super( message, e );
    }
    
    public ProcessException( final String message ) {
        this( message, null );
    }
    
    public ProcessException( final Throwable e ) {
        this( e.getMessage(), e );
    }
    
    /**
     * Método get do atributo transactionCodeEnum
     *
     * @return O valor do atributo transactionCodeEnum
     */
    @Override
    public TransactionCodeEnum getTransactionCodeEnum() {
        return TransactionCodeEnum.GENERIC_ERROR;
    }
}
