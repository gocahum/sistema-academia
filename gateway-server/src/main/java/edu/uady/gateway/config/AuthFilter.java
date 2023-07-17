package edu.uady.gateway.config;


import edu.uady.gateway.dto.RequestDto;
import edu.uady.gateway.dto.TokenDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Log4j2
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {
    @Value("${uri.auth.validate}")
    private String uri;

    private WebClient.Builder webClient;

    public AuthFilter(WebClient.Builder webClient) {
        super(Config.class);
        this.webClient = webClient;
    }

    @Override
    public GatewayFilter apply(Config config) {
        log.info("entra a validar url");
        return (((exchange, chain) -> {
            log.info("entra a validar url "+exchange.getRequest().getHeaders().toString());
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                log.info("no tiene autirizathion");
//                new Exception("Error no hay autorización");
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }
            log.info("entra a validar url");
            String tokenHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String[] chunks = tokenHeader.split(" ");
            if (chunks.length != 2 || !chunks[0].equals("Bearer")) {
                return onError(exchange, HttpStatus.UNAUTHORIZED);
            }
            RequestDto dto = new RequestDto(exchange.getRequest().getPath().toString(), exchange.getRequest().getMethod().toString());
            return webClient.build()
                    .post()
                    .uri("http://auth-service/auth/validate?token=" + chunks[1])
                    .bodyValue(dto)
                    .retrieve().bodyToMono(TokenDto.class)
                    .map(t -> {
                        t.getToken();
                        return exchange;
                    }).flatMap(chain::filter);
        }));
    }

    public Mono<Void> onError(ServerWebExchange exchange, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        return response.setComplete();
    }

    public static class Config {

    }
}
