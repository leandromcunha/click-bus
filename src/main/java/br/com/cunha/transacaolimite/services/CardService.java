/**
 * Leandro Marques da Cunha.
 * Projeto HubFintech
 * Teste Tecnico - Transação com cartão de credito
 *
 *
 */
package br.com.cunha.transacaolimite.services;

import br.com.cunha.transacaolimite.services.entities.Card;

/**
 * Classe CardService responsável por.
 *
 * @author <a href="mailto:leandromcunha@gmail.com>Leandro Marques da Cunha</a>
 * @version $Id$ Leandro
 */
public interface CardService {
    
    /**
     * @param card
     */
    void save( Card card);
    
    /**
     * @param nuCard
     * @return
     */
    Card findByNuCard( String nuCard);
    
}
