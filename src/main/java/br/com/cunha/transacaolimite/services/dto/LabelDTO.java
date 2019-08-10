/**
 * Leandro Marques da Cunha.
 * Projeto HubFintech
 * Teste Tecnico - Transação com cartão de credito
 *
 *
 */
package br.com.cunha.transacaolimite.services.dto;

/**
 * Classe LabelDTO responsável por.
 *
 * @author <a href="mailto:leandromcunha@gmail.com>Leandro Marques da Cunha</a>
 * @version $Id$ Leandro
 */
public class LabelDTO {

    private String key;

    private String label;

    /**
     * Construtor padrão LabelDTO.java.
     */
    public LabelDTO(final String key, final String label) {
        this.key = key;
        this.label = label;
    }

    /**
     * Método get do atributo key
     *
     * @return O valor do atributo key
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Método set do atributo key
     *
     * @param key Valor para setar no atributo key
     */
    public void setKey(final String key) {
        this.key = key;
    }

    /**
     * Método get do atributo label
     *
     * @return O valor do atributo label
     */
    public String getLabel() {
        return this.label;
    }

    /**
     * Método set do atributo label
     *
     * @param label Valor para setar no atributo label
     */
    public void setLabel(final String label) {
        this.label = label;
    }
}
