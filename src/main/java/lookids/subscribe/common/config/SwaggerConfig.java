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
			.addServersItem(new Server().url("http://localhost:8081"))//"/subscribe-service"))//"http://localhost:8081"))
			.info(new Info()
				.title("Subscribe API")
				.version("1.0.0")
				.description("Subscribe API 문서"))
			.addSecurityItem(new SecurityRequirement().addList(securityJwtName))
			.components(new Components()
				.addSecuritySchemes(securityJwtName,
					new SecurityScheme()
						.name(securityJwtName)
						.type(SecurityScheme.Type.HTTP)
						.scheme("bearer")
						.bearerFormat(securityJwtName)));
	}

	private Info apiInfo() {
		return new Info().title("SUBSCRIBE SERVICE 문서").description("lookids API 테스트를 위한 Swagger UI").version("1.0.0");
	}

}