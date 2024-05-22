package com.service.quiz.external;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.authorization.OAuth2AuthorizationManagers;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    public static final String BEARER = "Bearer";
    public static final String HEADER = "Authorization";


    @Autowired
    public OAuth2AuthorizedClientManager manager;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        System.out.println("Interceptor working..");
        String token = Objects.requireNonNull(manager.authorize(OAuth2AuthorizeRequest.withClientRegistrationId("keycloak").principal("internal").build())).getAccessToken().getTokenValue();
        System.out.println(token);
        requestTemplate.header(HEADER, BEARER + " " + token);

    }
}
