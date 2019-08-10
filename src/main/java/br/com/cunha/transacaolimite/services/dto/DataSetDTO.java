/**
 * Leandro Marques da Cunha.
 * Projeto HubFintech
 * Teste Tecnico - Transação com cartão de credito
 *
 *
 */
package br.com.cunha.transacaolimite.services.dto;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

/**
 * Classe DataSetDTO responsável por.
 *
 * @author <a href="mailto:leandromcunha@gmail.com>Leandro Marques da Cunha</a>
 * @version $Id$ Leandro
 */
public class DataSetDTO {

    private String label;

    private LinkedHashMap<String, BigDecimal> data;

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

    /**
     * Método get do atributo data
     *
     * @return O valor do atributo data
     */
    public LinkedHashMap<String, BigDecimal> getData() {
        return this.data;
    }

    /**
     * Método set do atributo data
     *
     * @param data Valor para setar no atributo data
     */
    public void setData(final LinkedHashMap<String, BigDecimal> data) {
        this.data = data;
    }

    public void addValor(final String key, final BigDecimal valor) {
        if (this.data == null) {
            this.data = new LinkedHashMap<>();
        }
        this.data.put(key, valor == null ? new BigDecimal(0) : valor);
    }
}
