package com.ejemplo.demo.Metadata;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
public class SwaggerMetadata {

	@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Worhshop:Spring_Home ")
                        .version("2.6.0")
                        .description("Workshop de la UMG")
                        .contact(new Contact()
                                .name("Fernando Jose Vega Gudiel -")
                                .email("fvegag1@miumg.edu.gt")));
	}
}
