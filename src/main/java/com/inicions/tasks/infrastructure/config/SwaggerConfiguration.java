package com.inicions.tasks.infrastructure.config;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
                .title("Task Service API")
                .description("Task Service API Description")
                .license(new License()
                        .name("MIT")
                        .url("LICENSE URL")
                )
                .version("1.0.0")
                .contact(new Contact()
                        .name("Example")
                        .email("apis@example.com")
                        .url("https://example.com")
                )
        );
    }
/*
    @Bean
    public Docket api () {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.getApiInfo())
                .useDefaultResponseMessages(false)
                .securitySchemes(securitySchemes());
    }

    private ApiKey apikeyScheme() {
        return new ApiKey("access-token", "x-access-token", "header");
    }

    private ArrayList securitySchemes () {
        ArrayList secList = new ArrayList();
        secList.add(apikeyScheme());

        return secList;
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Task Service API",
                "Task Service API Description",
                "1.0",
                "http://example.com/terms",
                new Contact("Example", "https://example.com", "apis@example.com"),
                "MIT",
                "LICENSE URL",
                Collections.emptyList()
        );
    }

    @Bean
    UiConfiguration uiConfig() {
        return UiConfigurationBuilder.builder()
                .deepLinking(true)
                .displayOperationId(false)
                .defaultModelsExpandDepth(1)
                .defaultModelExpandDepth(1)
                .defaultModelRendering(ModelRendering.EXAMPLE)
                .displayRequestDuration(false)
                .docExpansion(DocExpansion.NONE)
                .filter(false)
                .maxDisplayedTags(null)
                .operationsSorter(OperationsSorter.ALPHA)
                .showExtensions(false)
                .tagsSorter(TagsSorter.ALPHA)
                .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                .validatorUrl(null)
                .build();
    }

    @Bean
    SecurityScheme tenantKey() {
        return new ApiKey("tenant", "Constants.HTTP_TENANT_HEADER", "header");
    }
    */
}
