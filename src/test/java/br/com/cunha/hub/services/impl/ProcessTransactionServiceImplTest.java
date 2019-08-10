package br.com.cunha.hub.services.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import br.com.cunha.transacaolimite.config.APIProperties;
import br.com.cunha.transacaolimite.config.rabbitmq.sender.TransactionSender;
import br.com.cunha.transacaolimite.handler.exception.ProcessException;
import br.com.cunha.transacaolimite.handler.exception.InsufficientFundsException;
import br.com.cunha.transacaolimite.handler.exception.InvalidAccountException;
import br.com.cunha.transacaolimite.handler.exception.ProcessingErrorException;
import br.com.cunha.transacaolimite.repository.custom.TransactionCardCustomRepository;
import br.com.cunha.transacaolimite.services.TransactionCardService;
import br.com.cunha.transacaolimite.services.dto.OperationCardDTO;
import br.com.cunha.transacaolimite.services.dto.OperationResultDTO;
import br.com.cunha.transacaolimite.services.dto.TransactioCardDTO;
import br.com.cunha.transacaolimite.services.entities.TransCard;
import br.com.cunha.transacaolimite.services.enums.TransactionCodeEnum;
import br.com.cunha.transacaolimite.services.impl.ProcessTransactionServiceImpl;
import br.com.cunha.transacaolimite.services.impl.TransactionRedisServiceImpl;

public class ProcessTransactionServiceImplTest {
    
    @InjectMocks
    private ProcessTransactionServiceImpl processTransactionServiceImpl;
    
    @Mock
    private APIProperties properties;
    
    @Mock
    private TransactionSender transactionSender;
    
    @Mock
    private TransactionRedisServiceImpl transactionRedisService;
    
    @Mock
    private TransactionCardCustomRepository transactionCardCustomRepository;
    
    @Mock
    public TransactionCardService transactionCardService;
    
    @Before
    public void init() {
        MockitoAnnotations.initMocks( this );
    }
    
    /**
     * Verifica se ação é permitida quando é enviado vazio
     */
    @Test( expected = ProcessingErrorException.class )
    public void processTestActionEmpty() {
        final OperationCardDTO operationCardDTO = new OperationCardDTO();
        operationCardDTO.setAction( "" );
        operationCardDTO.setAmount( BigDecimal.ONE );
        operationCardDTO.setCardnumber( "1000023412341232" );
        operationCardDTO.setData( LocalDateTime.now() );
        
        Mockito.when( this.properties.getActions() ).thenReturn( Arrays.asList( "withdraw" ) );
        
        this.processTransactionServiceImpl.process( operationCardDTO );
    }
    
    /**
     * Verifica se ação é permitida quando é enviado null
     */
    @Test( expected = ProcessingErrorException.class )
    public void processTestActionNull() {
        final OperationCardDTO operationCardDTO = new OperationCardDTO();
        operationCardDTO.setAmount( BigDecimal.ONE );
        operationCardDTO.setCardnumber( "1000023412341232" );
        operationCardDTO.setData( LocalDateTime.now() );
        Mockito.when( this.properties.getActions() ).thenReturn( Arrays.asList( "withdraw" ) );
        this.processTransactionServiceImpl.process( operationCardDTO );
    }
    
    /**
     * Verifica se ação é permitida quando é enviado uma ação não configurada
     */
    @Test( expected = ProcessingErrorException.class )
    public void processTestActionDiff() {
        final OperationCardDTO operationCardDTO = new OperationCardDTO();
        operationCardDTO.setAction( "with-draw" );
        operationCardDTO.setAmount( BigDecimal.ONE );
        operationCardDTO.setCardnumber( "1000023412341232" );
        operationCardDTO.setData( LocalDateTime.now() );
        Mockito.when( this.properties.getActions() ).thenReturn( Arrays.asList( "withdraw" ) );
        this.processTransactionServiceImpl.process( operationCardDTO );
    }
    
    /**
     * Verifica se a conta é invalida
     */
    @Test( expected = InvalidAccountException.class )
    public void processTestActionInvalidAccount() {
        final OperationCardDTO operationCardDTO = new OperationCardDTO();
        operationCardDTO.setAction( "withdraw" );
        operationCardDTO.setAmount( BigDecimal.ONE );
        operationCardDTO.setCardnumber( "1000023412341232" );
        operationCardDTO.setData( LocalDateTime.now() );
        Mockito.when( this.properties.getActions() ).thenReturn( Arrays.asList( "withdraw" ) );
        
        Mockito.when( this.transactionRedisService.findById( ArgumentMatchers.anyString() ) )
                .thenReturn( Optional.empty() );
        
        Mockito.when( this.transactionRedisService.findById( ArgumentMatchers.anyString() ) )
                .thenReturn( Optional.empty() );
        
        this.processTransactionServiceImpl.process( operationCardDTO );
    }
    
    /**
     * Verifica se a tem valor limite para transação recuperando as informações do
     * cache
     */
    @Test( expected = InsufficientFundsException.class )
    public void processTestInsufficientFunds() {
        final OperationCardDTO operationCardDTO = new OperationCardDTO();
        operationCardDTO.setAction( "withdraw" );
        operationCardDTO.setAmount( BigDecimal.ONE );
        operationCardDTO.setCardnumber( "1000023412341232" );
        operationCardDTO.setData( LocalDateTime.now() );
        
        final TransactioCardDTO transaction = new TransactioCardDTO();
        transaction.setAvailable( new BigDecimal( 10000 ) );
        transaction.setNuCard( operationCardDTO.getCardnumber() );
        transaction.setAvailableAmount( new BigDecimal( 0 ) );
        
        Mockito.when( this.properties.getActions() ).thenReturn( Arrays.asList( "withdraw" ) );
        
        Mockito.when( this.transactionRedisService.findById( ArgumentMatchers.anyString() ) )
                .thenReturn( Optional.of( transaction ) );
        
        this.processTransactionServiceImpl.process( operationCardDTO );
    }
    
    /**
     * Verifica se a transação foi enviada para o redis
     */
    @Test( expected = ProcessException.class )
    public void processTestExceptionSendRedis() {
        final OperationCardDTO operationCardDTO = new OperationCardDTO();
        operationCardDTO.setAction( "withdraw" );
        operationCardDTO.setAmount( BigDecimal.TEN );
        operationCardDTO.setCardnumber( "1000023412341232" );
        operationCardDTO.setData( LocalDateTime.now() );
        
        final TransactioCardDTO transaction = new TransactioCardDTO();
        transaction.setAvailable( BigDecimal.TEN );
        transaction.setNuCard( operationCardDTO.getCardnumber() );
        transaction.setAvailableAmount( BigDecimal.TEN );
        
        Mockito.when( this.properties.getActions() ).thenReturn( Arrays.asList( "withdraw" ) );
        Mockito.when( this.properties.getMaxTransactionDetail() ).thenReturn( 5 );
        
        Mockito.when( this.transactionCardService.authorizationCode() )
                .thenReturn( String.format( "%06d", ( int ) ( Math.random() * 1000000 ) ) );
        
        Mockito.when( this.transactionRedisService.findById( ArgumentMatchers.anyString() ) )
                .thenThrow( ProcessException.class );
        
        this.processTransactionServiceImpl.process( operationCardDTO );
    }
    
    /**
     * Verifica se a transação foi enviada para fila de gravação na base de dados
     */
    @Test( expected = ProcessException.class )
    public void processTestExceptionSendQueue() {
        final OperationCardDTO operationCardDTO = new OperationCardDTO();
        operationCardDTO.setAction( "withdraw" );
        operationCardDTO.setAmount( BigDecimal.TEN );
        operationCardDTO.setCardnumber( "1000023412341232" );
        operationCardDTO.setData( LocalDateTime.now() );
        
        final TransactioCardDTO transaction = new TransactioCardDTO();
        transaction.setAvailable( BigDecimal.TEN );
        transaction.setNuCard( operationCardDTO.getCardnumber() );
        transaction.setAvailableAmount( BigDecimal.TEN );
        
        Mockito.when( this.properties.getActions() ).thenReturn( Arrays.asList( "withdraw" ) );
        Mockito.when( this.properties.getMaxTransactionDetail() ).thenReturn( 5 );
        Mockito.when( this.transactionCardService.authorizationCode() )
                .thenReturn( String.format( "%06d", ( int ) ( Math.random() * 1000000 ) ) );
        
        Mockito.when( this.transactionRedisService.findById( ArgumentMatchers.anyString() ) )
                .thenReturn( Optional.of( transaction ) );
        
        Mockito.doThrow( new ProcessException( "Erro ao tentar enviar para a fila de gravação" ) )
                .when( this.transactionSender ).send( ArgumentMatchers.any( TransCard.class ) );
        
        this.processTransactionServiceImpl.process( operationCardDTO );
    }
    
    /**
     * Verifica o processamento da transação com sucesso
     */
    @Test
    public void processTestSuccess() {
        final OperationCardDTO operationCardDTO = new OperationCardDTO();
        operationCardDTO.setAction( "withdraw" );
        operationCardDTO.setAmount( BigDecimal.TEN );
        operationCardDTO.setCardnumber( "1000023412341232" );
        operationCardDTO.setData( LocalDateTime.now() );
        
        final TransactioCardDTO transaction = new TransactioCardDTO();
        transaction.setAvailable( BigDecimal.TEN );
        transaction.setNuCard( operationCardDTO.getCardnumber() );
        transaction.setAvailableAmount( BigDecimal.TEN );
        
        Mockito.when( this.properties.getActions() ).thenReturn( Arrays.asList( "withdraw" ) );
        Mockito.when( this.properties.getMaxTransactionDetail() ).thenReturn( 5 );
        Mockito.when( this.transactionCardService.authorizationCode() )
                .thenReturn( String.format( "%06d", ( int ) ( Math.random() * 1000000 ) ) );
        
        Mockito.when( this.transactionRedisService.findById( ArgumentMatchers.anyString() ) )
                .thenReturn( Optional.of( transaction ) );
        
        Mockito.doAnswer( new Answer<Void>() {
            @Override
            public Void answer( final InvocationOnMock invocation ) throws Throwable {
                return null;
            }
        } ).when( this.transactionSender ).send( ArgumentMatchers.any( TransCard.class ) );
        
        final OperationResultDTO resultdo = this.processTransactionServiceImpl.process( operationCardDTO );
        
        Assert.assertEquals( resultdo.getCode(), TransactionCodeEnum.APPROVED.toString() );
        Assert.assertNotNull( resultdo.getAuthorizationCode() );
    }
    
}
