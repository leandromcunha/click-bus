/**
 * Leandro Marques da Cunha.
 * Projeto HubFintech
 * Teste Tecnico - Transação com cartão de credito
 */
package br.com.cunha.transacaolimite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cunha.transacaolimite.services.entities.Card;

/**
 * Classe CardRepository responsável por persistir as informações do cartão na
 * base de dados.
 *
 * @author <a href="mailto:leandromcunha@gmail.com>Leandro Marques da Cunha</a>
 * @version $Id$ Leandro
 */
@Repository
public interface CardRepository extends JpaRepository<Card,Long> {
    
    Card findByNuCard( String nuCard );
    
    Boolean existsByNuCard( String nuCard );
    
}