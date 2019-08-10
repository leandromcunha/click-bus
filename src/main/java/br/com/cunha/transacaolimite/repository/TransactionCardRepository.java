/**
 * Leandro Marques da Cunha.
 * Projeto HubFintech
 * Teste Tecnico - Transação com cartão de credito
 *
 *
 */
package br.com.cunha.transacaolimite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cunha.transacaolimite.services.entities.TransCard;

/**
 * Classe TransactionCardRepository responsável por persistir as informações das
 * transações na base de dados.
 *
 * @author <a href="mailto:leandromcunha@gmail.com>Leandro Marques da Cunha</a>
 * @version $Id$ Leandro
 */
@Repository
public interface TransactionCardRepository
                extends
                    JpaRepository<TransCard, Long> {

}
