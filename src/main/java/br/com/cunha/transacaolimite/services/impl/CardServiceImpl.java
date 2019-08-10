/**
 * Leandro Marques da Cunha.
 * Projeto HubFintech
 * Teste Tecnico - Transação com cartão de credito
 */
package br.com.cunha.transacaolimite.services.impl;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cunha.transacaolimite.handler.exception.InvalidAccountException;
import br.com.cunha.transacaolimite.handler.exception.ProcessingErrorException;
import br.com.cunha.transacaolimite.repository.CardRepository;
import br.com.cunha.transacaolimite.services.CardService;
import br.com.cunha.transacaolimite.services.entities.Card;

/**
 * Classe CardServiceImpl responsável por.
 *
 * @author <a href="mailto:leandromcunha@gmail.com>Leandro Marques da Cunha</a>
 * @version $Id$ Leandro
 */
@Service
@Transactional( value = TxType.REQUIRED )
public class CardServiceImpl implements CardService {
    
    private static final Logger LOG = LoggerFactory.getLogger( CardService.class );
    
    @Autowired
    private CardRepository cardRepository;
    
    /*
     * (non-Javadoc)
     *
     * @see
     * br.com.cunha.hub.services.CardService#save(br.com.cunha.hub.services.
     * entities.Card)
     */
    @Override
    public void save( final Card card ) {
        try{
            this.cardRepository.save( card );
        }catch( final Exception e ){
            CardServiceImpl.LOG.error( "Erro de Processamento: {} ", e );
            throw new ProcessingErrorException( "Erro ao tentar gravar os dados do cartão na base de dados ", e );
        }
    }
    
    /*
     * (non-Javadoc)
     *
     * @see br.com.cunha.hub.services.CardService#findByNuCard(java.lang.String)
     */
    @Override
    public Card findByNuCard( final String nuCard ) {
        if( this.cardRepository.existsByNuCard( nuCard ) ){
            return this.cardRepository.findByNuCard( nuCard );
        }
        CardServiceImpl.LOG.error( "Conta Invalida: {} ", nuCard );
        throw new InvalidAccountException( "Conta Inválida" );
        
    }
}