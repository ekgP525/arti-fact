package ajou.artifact.arti_fact.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI artiFactOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Arti-Fact API")
                        .description("Arti-Fact 미술품 관리 시스템 API 문서")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Arti-Fact Team")
                                .email("arti-fact@example.com")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("로컬 개발 서버"),
                        new Server()
                                .url("https://api.arti-fact.com")
                                .description("프로덕션 서버")
                ));
    }
}

