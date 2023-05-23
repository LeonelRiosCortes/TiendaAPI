package Ico.Fes.Tienda.TiendaFrontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class TiendaFrontendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiendaFrontendApplication.class, args);
	}

	@Bean
	public WebClient webClient(WebClient.Builder builder){
		return builder.build();
	}

}
