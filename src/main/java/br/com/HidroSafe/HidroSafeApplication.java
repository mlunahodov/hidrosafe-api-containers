package br.com.HidroSafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(info = @Info(title = "Hidro Safe API", version = "v1", description = "API do sistema de controle de níveis de água"))
public class HidroSafeApplication {
	public static void main(String[] args) {
		SpringApplication.run(HidroSafeApplication.class, args);
	}
}
