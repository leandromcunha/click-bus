/**
 * Leandro Marques da Cunha.
 * Projeto HubFintech
 * Teste Tecnico - Transação com cartão de credito
 */
package br.com.cunha.transacaolimite.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * Classe RedisConfiguration responsável por efetuar a configuração com o Redis.
 *
 * @author <a href="mailto:leandromcunha@gmail.com>Leandro Marques da Cunha</a>
 * @version $Id$ Leandro
 */
@Configuration
@EnableRedisRepositories
public class APIRedisConfiguration {
    
    @Value( "${spring.redis.host}" )
    private String redisHostName;
    
    @Value( "${spring.redis.password}" )
    private String password;
    
    @Value( "${spring.redis.port}" )
    private int redisPort;
    
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        final LettuceConnectionFactory factory = new LettuceConnectionFactory();
        factory.setHostName( this.redisHostName );
        factory.setPassword( this.password );
        factory.setPort( this.redisPort );
        return factory;
    }
    
    @Bean
    public RedisTemplate<?,?> redisTemplate() {
        final RedisTemplate<byte[],byte[]> template = new RedisTemplate<>();
        template.setConnectionFactory( this.redisConnectionFactory() );
        return template;
    }
}
