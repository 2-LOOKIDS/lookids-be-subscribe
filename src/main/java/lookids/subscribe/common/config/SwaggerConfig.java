package lookids.subscribe.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class SwaggerConfig {
	// Barear과 JWT 관련 설정 추가
	private static final String BEARER_TOKEN_PREFIX = "Bearer";

	@Bean
	public OpenAPI openAPI() {
		String securityJwtName = "JWT";
		SecurityRequirement securityRequirement = new SecurityRequirement().addList(securityJwtName);
		Components components = new Components().addSecuritySchemes(securityJwtName,
			new SecurityScheme().name(securityJwtName)
				.type(SecurityScheme.Type.HTTP)
				.scheme(BEARER_TOKEN_PREFIX)
				.bearerFormat(securityJwtName));

		return new OpenAPI()
			// .addServersItem(new Server().url("/"))
			.addSecurityItem(securityRequirement)
			.components(components)
			.addServersItem(new Server().url("/subscribe-service"))//"http://localhost:8080"))
			.info(new Info()
				.title("subscribe API ")
				.version("1.0.0")
				.description("subscribe API 문서에 대한 설명"))
			.addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
			.components(new Components()
				.addSecuritySchemes("bearerAuth",
					new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
	}

	private Info apiInfo() {
		return new Info().title("SUBSCRIBE SERVICE 문서").description("lookids API 테스트를 위한 Swagger UI").version("1.0.0");
	}

}