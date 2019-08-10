/**
 * Leandro Marques da Cunha.
 * Projeto HubFintech
 * Teste Tecnico - Transação com cartão de credito
 */
package br.com.cunha.transacaolimite.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.cunha.transacaolimite.handler.exception.ProcessException;
import br.com.cunha.transacaolimite.handler.exception.InsufficientFundsException;
import br.com.cunha.transacaolimite.handler.exception.InvalidAccountException;
import br.com.cunha.transacaolimite.handler.exception.ProcessingErrorException;
import br.com.cunha.transacaolimite.services.dto.OperationResultDTO;

/**
 * Classe HubExceptionHandler responsável por controlar as exception.
 *
 * @author <a href="mailto:leandromcunha@gmail.com>Leandro Marques da Cunha</a>
 * @version $Id$ Leandro
 */
@ControllerAdvice
public class APIExceptionHandler extends ResponseEntityExceptionHandler {
    
    private static final Logger LOG = LoggerFactory.getLogger( APIExceptionHandler.class );
    
    @ExceptionHandler( {Exception.class} )
    public ResponseEntity<Object> handleHubException( final Exception ex, final WebRequest request ) {
        APIExceptionHandler.LOG.error( "Erro:: handleHubException:: Exception", ex );
        return this.handleExceptionInternal( ex, new OperationResultDTO( ex.getMessage() ), new HttpHeaders(),
                HttpStatus.BAD_REQUEST, request );
    }
    
    @ExceptionHandler( {InsufficientFundsException.class} )
    public ResponseEntity<Object> handleHubException( final InsufficientFundsException ex, final WebRequest request ) {
        APIExceptionHandler.LOG.error( "Erro:: handleHubException:: InsufficientFundsException", ex );
        return this.handleExceptionInternal( ex, new OperationResultDTO( ex.getTransactionCodeEnum().toString() ),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request );
    }
    
    @ExceptionHandler( {InvalidAccountException.class} )
    public ResponseEntity<Object> handleHubException( final InvalidAccountException ex, final WebRequest request ) {
        APIExceptionHandler.LOG.error( "Erro:: handleHubException:: InvalidAccountException", ex );
        return this.handleExceptionInternal( ex, new OperationResultDTO( ex.getTransactionCodeEnum().toString() ),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request );
    }
    
    @ExceptionHandler( {ProcessingErrorException.class} )
    public ResponseEntity<Object> handleHubException( final ProcessingErrorException ex, final WebRequest request ) {
        APIExceptionHandler.LOG.error( "Erro:: handleHubException:: ProcessingErrorException", ex );
        return this.handleExceptionInternal( ex, new OperationResultDTO( ex.getTransactionCodeEnum().toString() ),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request );
    }
    
    @ExceptionHandler( {ProcessException.class} )
    public ResponseEntity<Object> handleHubException( final ProcessException ex, final WebRequest request ) {
        APIExceptionHandler.LOG.error( "Erro:: handleHubException:: ProcessingErrorException", ex );
        return this.handleExceptionInternal( ex, new OperationResultDTO( ex.getTransactionCodeEnum().toString() ),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request );
    }
    
}
