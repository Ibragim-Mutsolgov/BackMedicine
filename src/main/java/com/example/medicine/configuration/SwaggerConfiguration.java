package com.example.medicine.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfiguration {

    private final Contact DEFAULT_CONTACT = new Contact(
            "Binit Datta", "http://binitdatta.com",
            "binit-sample-email.com"
    );

    private final ApiInfo DEFAULT_API_INFO = new ApiInfo(
            "Запросы Rest API", "Описание Rest API", "1.0",
            "urn:tos", DEFAULT_CONTACT, "Apache 2.0",
            "http://www.apache.org/licenses/LICENSE-2.0", List.of()
    );

    private final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = new HashSet<>(
            List.of("application/json")
    );

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO)
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
                .select().apis(RequestHandlerSelectors.basePackage("com.example.medicine"))
                .build();
    }
}
