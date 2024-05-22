package com.service.questions.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.authorizeHttpRequests(server -> {
            server.requestMatchers("/h2-console/**").permitAll();
            server.requestMatchers("/api/**").authenticated();
        });
        httpSecurity.oauth2ResourceServer(oauth -> oauth.jwt(Customizer.withDefaults()));
        return httpSecurity.build();
    }
}
