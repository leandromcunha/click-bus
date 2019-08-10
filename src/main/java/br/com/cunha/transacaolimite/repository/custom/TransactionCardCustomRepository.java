/**
 * Leandro Marques da Cunha.
 * Projeto HubFintech
 * Teste Tecnico - Transação com cartão de credito
 *
 *
 */
package br.com.cunha.transacaolimite.repository.custom;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import br.com.cunha.transacaolimite.services.dto.TransactioCardDTO;

/**
 * @author <a href="mailto:leandromcunha@gmail.com>Leandro Marques da Cunha</a>
 * @version $Id$ Leandro
 */
@Mapper
public interface TransactionCardCustomRepository {
    public List<TransactioCardDTO> transactionCardLoad();

    public TransactioCardDTO transactionCardByNuCard(
                    @Param("nuCard") String nuCard);
}
