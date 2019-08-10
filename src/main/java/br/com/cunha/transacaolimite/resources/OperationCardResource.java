/**
 * Leandro Marques da Cunha.
 * Projeto HubFintech
 * Teste Tecnico - Transação com cartão
 */
package br.com.cunha.transacaolimite.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;

import br.com.cunha.transacaolimite.services.ProcessTransactionService;
import br.com.cunha.transacaolimite.services.dto.OperationCardDTO;
import br.com.cunha.transacaolimite.services.dto.OperationResultDTO;
import br.com.cunha.transacaolimite.services.dto.TransactioCardDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Classe OperationCardResource responsável por exportor a API para efetuar uma
 * transação financeira de um cartão pré-pago.
 *
 * @author <a href="mailto:leandromcunha@gmail.com>Leandro Marques da Cunha</a>
 * @version $Id$ Leandro
 */

@RestController
@RequestMapping( "/api" )
@Api( value = "Transação financeira de um cartão pré-pago " )
public class OperationCardResource {
    
    @Autowired
    private ProcessTransactionService processTransactionService;
    
    @PostMapping( "/transaction" )
    @ApiOperation( value = "Efetuar uma transação", response = OperationResultDTO.class )
    @ApiResponses( value = {@ApiResponse( code = 200, message = "200 OK. Transação Realizada comsucesso." ),
            @ApiResponse( code = 204, message = "204 NO CONTENT. Nenhum registro encontrado." ),
            @ApiResponse( code = 400, message = "400 BAD REQUEST. Erro na consulta?" ),
            @ApiResponse( code = 402, message = "402 UNAUTHORIZED. Você não está autorizado a acessar esse serviço." ),
            
            @ApiResponse( code = 403, message = "403 FORBIDDEN. Não é permitidoacessar esse serviço." ),
            @ApiResponse( code = 404, message = "404 NOT FOUND. O serviço não foiencontrado." ),
            @ApiResponse( code = 405, message = "405 METHOD NOT ALLOWED. Método derequisição não esperado (final GET ou POST)." ),
            @ApiResponse( code = 422, message = "422 UNPROCESSABLE ENTITY. Erro de validação." ),
            @ApiResponse( code = 500, message = "500 INTERNAL SERVER ERROR. Um erro ocorreu em nossa API." )} )
    
    @Timed
    
    public ResponseEntity<OperationResultDTO> transactions( @RequestBody
    final OperationCardDTO operationCardDTO ) {
        
        final OperationResultDTO operationResultDTO = this.processTransactionService.process( operationCardDTO );
        
        return new ResponseEntity<>( operationResultDTO, HttpStatus.OK );
    }
    
    @GetMapping( "/transaction/{nuCard}" )
    @ApiOperation( value = "Recupera as ultimas transações", response = TransactioCardDTO.class )
    @ApiResponses( value = {@ApiResponse( code = 200, message = "200 OK. Transação Realizada com sucesso." ),
            @ApiResponse( code = 204, message = "204 NO CONTENT. Nenhum registro encontrado." ),
            @ApiResponse( code = 400, message = "400 BAD REQUEST. Erro na consulta?" ),
            @ApiResponse( code = 402, message = "402 UNAUTHORIZED. Você não está autorizado a acessar esse serviço." ),
            @ApiResponse( code = 403, message = "403 FORBIDDEN. Não é permitidoacessar esse serviço." ),
            @ApiResponse( code = 404, message = "404 NOT FOUND. O serviço não foiencontrado." ),
            @ApiResponse( code = 405, message = "405 METHOD NOT ALLOWED. Método derequisição não esperado (final GET ou POST)." ),
            @ApiResponse( code = 422, message = "422 UNPROCESSABLE ENTITY. Erro de validação." ),
            @ApiResponse( code = 500, message = "500 INTERNAL SERVER ERROR. Um erro ocorreu em nossa API." )} )
    
    @Timed
    public ResponseEntity<TransactioCardDTO> transactions( @PathVariable
    final String nuCard ) {
        
        final TransactioCardDTO dto = this.processTransactionService.transactions( nuCard );
        
        return new ResponseEntity<>( dto, HttpStatus.OK );
    }
}
