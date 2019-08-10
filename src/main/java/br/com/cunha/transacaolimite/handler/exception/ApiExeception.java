/**
 * Leandro Marques da Cunha.
 * Projeto HubFintech
 * Teste Tecnico - Transação com cartão de credito
 */
package br.com.cunha.transacaolimite.handler.exception;

import br.com.cunha.transacaolimite.services.enums.TransactionCodeEnum;

/**
 * Interface HubExeception responsável por definir a assinatura das exception do
 * projeto.
 *
 * @author <a href="mailto:leandromcunha@gmail.com>Leandro Marques da Cunha</a>
 * @version $Id$ Leandro
 */
public interface ApiExeception {
    
    public TransactionCodeEnum getTransactionCodeEnum();
}
