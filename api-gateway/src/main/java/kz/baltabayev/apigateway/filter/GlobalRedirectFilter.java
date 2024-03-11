package kz.baltabayev.apigateway.filter;

import kz.baltabayev.apigateway.dto.AuthorizationResponse;
import kz.baltabayev.apigateway.security.CustomUserDetails;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class GlobalRedirectFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        WebClient webClient = WebClient.create();

        Mono<AuthorizationResponse> responseMono = webClient.get()
                .uri("http://security-service")
                .retrieve()
                .bodyToMono(AuthorizationResponse.class);

        return responseMono.flatMap(response -> {
            UserDetails userDetails = createUserDetails(response);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setAuthenticated(response.isAuthorized());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return chain.filter(exchange);
        });
    }

    private UserDetails createUserDetails(AuthorizationResponse response) {
        return new CustomUserDetails(response);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
