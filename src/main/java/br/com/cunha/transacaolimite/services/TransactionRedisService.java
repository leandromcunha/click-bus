package br.com.cunha.transacaolimite.services;

import java.util.Optional;

import br.com.cunha.transacaolimite.services.dto.TransactioCardDTO;

/**
 * Interface que define a assinatura do serviço para acessar o redis das
 * transações
 *
 * @author Leandro
 */
public interface TransactionRedisService {
    
    /**
     * Retorna uma transação do cache redis
     *
     * @param nuCard
     * @return {@link Optional<TransactioCardDTO>}
     */
    public Optional<TransactioCardDTO> findById( String nuCard );
    
    /**
     * Salva no redis as transações
     *
     * @param TransactioCardDTO
     * @return {@link TransactioCardDTO}
     */
    public TransactioCardDTO save( TransactioCardDTO TransactioCardDTO );
}