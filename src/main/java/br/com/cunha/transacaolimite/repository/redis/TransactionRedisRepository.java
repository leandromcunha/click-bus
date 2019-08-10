/**
 * Leandro Marques da Cunha.
 * Projeto HubFintech
 * Teste Tecnico - Transação com cartão de credito
 */
package br.com.cunha.transacaolimite.repository.redis;

import org.springframework.data.repository.CrudRepository;

import br.com.cunha.transacaolimite.services.dto.TransactioCardDTO;

/**
 * Classe TransactionRedisRepository responsável por persistir as informações no
 * Redis.
 *
 * @author <a href="mailto:leandromcunha@gmail.com>Leandro Marques da Cunha</a>
 * @version $Id$ Leandro
 */
// @Repository
public interface TransactionRedisRepository extends CrudRepository<TransactioCardDTO,String> {
    
}