/**
 * Leandro Marques da Cunha.
 * Projeto HubFintech
 * Teste Tecnico - Transação com cartão de credito
 */
package br.com.cunha.transacaolimite.services;

import br.com.cunha.transacaolimite.services.entities.TransCard;

/**
 * Classe TransactionCardService responsável por.
 *
 * @author <a href="mailto:leandromcunha@gmail.com>Leandro Marques da Cunha</a>
 * @version $Id$ Leandro
 */
public interface TransactionCardService {
    
    void save( TransCard transCard );
    
    String authorizationCode();
    
}
