package edu.uady.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}


	@GetMapping("/error")
	public String erro(){
		return "microservicio de uady no disponible por el momento";
	}

	@Bean
	@LoadBalanced
	public RouteLocator router(RouteLocatorBuilder builder) {
		return builder.routes()
//				.route(p -> p.path("/test1")
//						.filters(f -> f.addRequestHeader("Hola", "UADY"))
//						.uri("https://apidemoportal.uady.mx/api/menu/secondary/1"))
//				.route(p-> p.host("*.test2")
//						.filters(f-> f.circuitBreaker(config -> config.setName("test")
//								.setFallbackUri("fordward:/test")))
//						.uri("http://localhost:80")				)
				.build();
	}

}
