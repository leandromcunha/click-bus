package br.com.cunha.transacaolimite.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order( Ordered.HIGHEST_PRECEDENCE )
public class CorsFilter implements Filter {
    
    @Override
    public void doFilter( final ServletRequest req, final ServletResponse resp, final FilterChain chain )
            throws IOException, ServletException {
        
        final HttpServletResponse response = ( HttpServletResponse )resp;
        
        response.setHeader( "Access-Control-Allow-Origin", "*" );
        response.setHeader( "Access-Control-Allow-Credentials", "true" );
        response.setHeader( "Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH" );
        response.setHeader( "Access-Control-Max-Age", "3600" );
        response.setHeader( "Access-Control-Allow-Headers",
                "X-Requested-With, Content-Type, Authorization, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers" );
        chain.doFilter( req, response );
    }
    
    @Override
    public void destroy() {
    }
    
    @Override
    public void init( final FilterConfig arg0 ) throws ServletException {
    }
    
}