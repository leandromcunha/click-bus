/**
 * Leandro Marques da Cunha.
 * Projeto HubFintech
 * Teste Tecnico - Transação com cartão
 */
package br.com.cunha.transacaolimite.resources;

import java.math.BigDecimal;
import java.util.LinkedList;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.codahale.metrics.annotation.Timed;

import br.com.cunha.transacaolimite.services.dto.DataSetDTO;
import br.com.cunha.transacaolimite.services.dto.GraficoDTO;
import br.com.cunha.transacaolimite.services.dto.LabelDTO;
import br.com.cunha.transacaolimite.services.dto.OperationCardDTO;
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
@Api( value = "Enviar Multiplos arquivos para o Storage" )
public class OperationMultiPartFilesResource {
    
    // @PostMapping("/enviar")
    
    @RequestMapping( value = "/enviar", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, headers = ( "content-type=multipart/form-data" ) )
    @ApiOperation( value = "Enviar Multiplos arquivos para o Storage", response = String.class )
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
    public ResponseEntity<String> enviar( @RequestPart( "arquivos" )
    final MultipartFile[] arquivos, final OperationCardDTO operationCard, final MultipartHttpServletRequest request ) {
        
        try{
            System.out.println( operationCard );
        }catch( final Exception e ){
            e.printStackTrace();
        }
        return new ResponseEntity<>( "OK", HttpStatus.OK );
    }
    
    @GetMapping( value = "/grafico" )
    @ApiOperation( value = "Enviar Multiplos arquivos para o Storage", response = GraficoDTO.class )
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
    public ResponseEntity<GraficoDTO> grafico() {
        final GraficoDTO grafico = new GraficoDTO();
        
        final LinkedList<LabelDTO> labels = new LinkedList<>();
        labels.add( new LabelDTO( "201701", "jan-17" ) );
        labels.add( new LabelDTO( "201702", "fev-17" ) );
        labels.add( new LabelDTO( "201703", "mar-17" ) );
        labels.add( new LabelDTO( "201704", "abr-17" ) );
        grafico.setLabels( labels );
        
        final LinkedList<DataSetDTO> datasets = new LinkedList<>();
        DataSetDTO dataSetDTO = new DataSetDTO();
        dataSetDTO.setLabel( "Exame" );
        dataSetDTO.addValor( "201701", new BigDecimal( 21 ) );
        dataSetDTO.addValor( "201702", new BigDecimal( 31 ) );
        dataSetDTO.addValor( "201703", new BigDecimal( 41 ) );
        dataSetDTO.addValor( "201704", new BigDecimal( 51 ) );
        datasets.add( dataSetDTO );
        
        dataSetDTO = new DataSetDTO();
        dataSetDTO.setLabel( "Consulta" );
        dataSetDTO.addValor( "201701", new BigDecimal( 11 ) );
        dataSetDTO.addValor( "201702", new BigDecimal( 12 ) );
        dataSetDTO.addValor( "201703", new BigDecimal( 13 ) );
        dataSetDTO.addValor( "201704", new BigDecimal( 14 ) );
        datasets.add( dataSetDTO );
        
        grafico.setDatasets( datasets );
        
        return new ResponseEntity<>( grafico, HttpStatus.OK );
    }
}
