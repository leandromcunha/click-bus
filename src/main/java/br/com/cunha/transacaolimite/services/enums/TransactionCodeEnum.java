/**
 * Leandro Marques da Cunha.
 * Projeto HubFintech
 * Teste Tecnico - Transação com cartão de credito
 */
package br.com.cunha.transacaolimite.services.enums;

/**
 * Classe TransactionCodeEnum responsável por identificar o condigo de resposta
 * do processamento/erro na soliciatação da transação.
 *
 * @author <a href="mailto:leandromcunha@gmail.com>Leandro Marques da Cunha</a>
 * @version $Id$ Leandro
 */
public enum TransactionCodeEnum {
    
    APPROVED("00"), INVALID_ACCOUNT("14"), INSUFFICIENT_FUNDS("51"), PROCESSING_ERROR("96"), GENERIC_ERROR("99"),;
    
    private String code;
    
    TransactionCodeEnum( final String code ) {
        this.code = code;
    }
    
    /*
     * (non-Javadoc)
     *
     * @see java.lang.Enum#toString()
     */
    
    @Override
    public String toString() {
        return this.code;
    }
}