/**
 * Leandro Marques da Cunha.
 * Projeto HubFintech
 * Teste Tecnico - Transação com cartão de credito
 *
 *
 */
package br.com.cunha.transacaolimite.services.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Classe OperationResultDTO responsável por retornar o resultado da operação
 * e/ou status de processamento.
 *
 * @author <a href="mailto:leandromcunha@gmail.com>Leandro Marques da Cunha</a>
 * @version $Id$ Leandro
 */

@ApiModel( value="OperationResultDTO", description="Retorno do resultado da solicitação da operação" )
public class OperationResultDTO implements Serializable {
    
    private static final long serialVersionUID=-1567360184052836818L;
    
    @ApiModelProperty( "action" )
    private String action;
    
    @ApiModelProperty( "code" )
    private String code;
    
    @ApiModelProperty( "authorization_code" )
    @JsonProperty( "authorization_code" )
    private String authorizationCode;
    
    /**
     * Construtor padrão OperationResultDTO.java.
     */
    public OperationResultDTO() {
    }
    
    public OperationResultDTO( final String code ) {
        this.code=code;
    }
    
    /**
     * Método get do atributo action
     *
     * @return O valor do atributo action
     */
    public String getAction() {
        return this.action;
    }
    
    /**
     * Método set do atributo action
     *
     * @param action Valor para setar no atributo action
     */
    public void setAction( final String action) {
        this.action=action;
    }
    
    /**
     * Método get do atributo code
     *
     * @return O valor do atributo code
     */
    public String getCode() {
        return this.code;
    }
    
    /**
     * Método set do atributo code
     *
     * @param code Valor para setar no atributo code
     */
    public void setCode( final String code) {
        this.code=code;
    }
    
    /**
     * Método get do atributo authorizationCode
     *
     * @return O valor do atributo authorizationCode
     */
    public String getAuthorizationCode() {
        return this.authorizationCode;
    }
    
    /**
     * Método set do atributo authorizationCode
     *
     * @param authorizationCode Valor para setar no atributo authorizationCode
     */
    public void setAuthorizationCode( final String authorizationCode) {
        this.authorizationCode=authorizationCode;
    }
    
    @Override
    public String toString() {
        return "OperationResultDTO [action="+this.action+", code="+this.code
                +", authorizationCode="+this.authorizationCode+"]";
    }
    
}
