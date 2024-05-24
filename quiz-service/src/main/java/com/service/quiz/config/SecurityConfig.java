package com.service.quiz.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.authorizeHttpRequests(
                requestMatcherRegistry ->
                {
//                    requestMatcherRegistry.requestMatchers("/graphiql").permitAll();
//                    requestMatcherRegistry.requestMatchers("/graphql").permitAll();
                    requestMatcherRegistry.anyRequest().authenticated();
                });
        httpSecurity.oauth2ResourceServer(server -> server.jwt(Customizer.withDefaults()));
        return httpSecurity.build();
    }

//    kafka topic configuration

    @Value("${spring.kafka.topic.name}")
    private String topicName;


    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(topicName).build();
    }


}
