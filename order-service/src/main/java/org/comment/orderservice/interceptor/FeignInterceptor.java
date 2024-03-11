package org.comment.orderservice.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class FeignInterceptor  implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        JwtAuthenticationToken jwtAuthenticationToken= (JwtAuthenticationToken) authentication;
        String jwtAccessToken = Optional.ofNullable(jwtAuthenticationToken)
                                .map(JwtAuthenticationToken::getToken)
                                .map(Jwt::getTokenValue)
                                .orElse(null);
        requestTemplate.header("Authorization","Bearer "+jwtAccessToken);
    }
}
