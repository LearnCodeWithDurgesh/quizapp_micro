package com.service.gateway.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable);
        httpSecurity.authorizeExchange(ex ->
        {
            ex.pathMatchers("/oauth2").permitAll();
            ex.anyExchange().authenticated();
        });

        httpSecurity.oauth2Client(Customizer.withDefaults());
        httpSecurity.oauth2ResourceServer(server -> server.jwt(Customizer.withDefaults()));
        return httpSecurity.build();

    }
}
