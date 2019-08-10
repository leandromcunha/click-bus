package br.com.cunha.transacaolimite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import br.com.cunha.transacaolimite.config.APIProperties;

@SpringBootApplication( scanBasePackages = {"br.com.cunha"} )
@Configuration
@SpringBootConfiguration
@EnableConfigurationProperties( value = {APIProperties.class} )
public class APIApplication {
    
    public static void main( final String[] args ) {
        SpringApplication.run( APIApplication.class, args );
    }
}