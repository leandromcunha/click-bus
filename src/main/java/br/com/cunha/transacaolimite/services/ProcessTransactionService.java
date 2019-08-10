/**
 * Leandro Marques da Cunha.
 * Projeto HubFintech
 * Teste Tecnico - Transação com cartão de credito
 */
package br.com.cunha.transacaolimite.services;

import br.com.cunha.transacaolimite.services.dto.OperationCardDTO;
import br.com.cunha.transacaolimite.services.dto.OperationResultDTO;
import br.com.cunha.transacaolimite.services.dto.TransactioCardDTO;

/**
 * Classe ProcessTransactionService responsável por.
 *
 * @author <a href="mailto:leandromcunha@gmail.com>Leandro Marques da Cunha</a>
 * @version $Id$ Leandro
 */
public interface ProcessTransactionService {
    
    /**
     * @param operationCardDTO
     * @return
     */
    OperationResultDTO process( OperationCardDTO operationCardDTO );
    
    /**
     * Metodo transactions responsavel por (execultar/retornar/enviar).
     *
     * @param nuCard
     * @return
     */
    TransactioCardDTO transactions( String nuCard );
}
