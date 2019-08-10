/**
 * Leandro Marques da Cunha.
 * Projeto HubFintech
 * Teste Tecnico - Transação com cartão de credito
 *
 *
 */
package br.com.cunha.transacaolimite.services.dto;

import java.util.LinkedList;

/**
 * Classe GraficoDTO responsável por.
 *
 * @author <a href="mailto:leandromcunha@gmail.com>Leandro Marques da Cunha</a>
 * @version $Id$ Leandro
 */
public class GraficoDTO {

    private LinkedList<LabelDTO> labels;

    private LinkedList<DataSetDTO> datasets;

    /**
     * Método get do atributo labels
     *
     * @return O valor do atributo labels
     */
    public LinkedList<LabelDTO> getLabels() {
        return this.labels;
    }

    /**
     * Método set do atributo labels
     *
     * @param labels Valor para setar no atributo labels
     */
    public void setLabels(final LinkedList<LabelDTO> labels) {
        this.labels = labels;
    }

    /**
     * Método get do atributo datasets
     *
     * @return O valor do atributo datasets
     */
    public LinkedList<DataSetDTO> getDatasets() {
        return this.datasets;
    }

    /**
     * Método set do atributo datasets
     *
     * @param datasets Valor para setar no atributo datasets
     */
    public void setDatasets(final LinkedList<DataSetDTO> datasets) {
        this.datasets = datasets;
    }
}
