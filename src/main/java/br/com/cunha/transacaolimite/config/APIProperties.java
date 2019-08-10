/**
 * Leandro Marques da Cunha.
 * Projeto HubFintech
 * Teste Tecnico - Transação com cartão de credito
 */
package br.com.cunha.transacaolimite.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Classe HubFintechProperty responsável por carregar os parametro do sistema no
 * application.xml.
 *
 * @author <a href="mailto:leandromcunha@gmail.com>Leandro Marques da Cunha</a>
 * @version $Id$ Leandro
 */

@ConfigurationProperties( "api" )
public class APIProperties {
    
    private Integer maxTransactionDetail;
    
    private String rabbitSaveTransaction;
    
    private List<String> actions;
    
    public List<String> getActions() {
        return this.actions;
    }
    
    public void setActions( final List<String> actions ) {
        this.actions = actions;
    }
    
    public Integer getMaxTransactionDetail() {
        return this.maxTransactionDetail;
    }
    
    public void setMaxTransactionDetail( final Integer maxTransactionDetail ) {
        this.maxTransactionDetail = maxTransactionDetail;
    }
    
    public String getRabbitSaveTransaction() {
        return this.rabbitSaveTransaction;
    }
    
    public void setRabbitSaveTransaction( final String rabbitSaveTransaction ) {
        this.rabbitSaveTransaction = rabbitSaveTransaction;
    }
    
}
