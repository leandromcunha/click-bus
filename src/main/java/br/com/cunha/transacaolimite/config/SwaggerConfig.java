package br.com.cunha.transacaolimite.config;

import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
    @Bean
    public Docket api() {
        return new Docket( DocumentationType.SWAGGER_2 ).select().paths( PathSelectors.any() )
                .apis( Predicates.or( RequestHandlerSelectors.basePackage( "br.com.cunha.transacaolimite.resources" ),
                        RequestHandlerSelectors.basePackage( "br.com.cunha.transacaolimite.resources" ) ) )
                .build().directModelSubstitute( LocalDate.class, String.class )
                .genericModelSubstitutes( ResponseEntity.class ).apiInfo( this.apiInfo() );
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title( "API Teste Tecnico" ).description( "" )
                .contact( new Contact( "Teste Tecnico HubFinteh", "http://www.cunhasoftware.com.br",
                        "leandromcunha@gmail.com" ) )
                .license( "Privado" ).licenseUrl( "http://www.cunhasoftware.com.br" ).version( "1.0.0" ).build();
    }
}