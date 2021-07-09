package com.example.jugalbeats.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.*;
import springfox.documentation.spi.service.contexts.SecurityContext;

import java.util.Arrays;
import java.util.List;
/*
 * Author by : dhruv ,2021*/
@Configuration
@EnableSwagger2
public class SwaggerConfig{
	
	  public static final String AUTHORIZATION_HEADER = "Authorization";
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.securityContexts(Arrays.asList(securityContext()))
		        .securitySchemes(Arrays.asList(apiKey()))
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo());

	}
    
    private ApiInfo getApiInfo() {
    	return new ApiInfoBuilder().title("Swagger API Doc").description("More description about the API")
				.version("1.0.0").build();
	}
    

    private ApiKey apiKey() {
      return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
    }

    private SecurityContext securityContext() {
      return SecurityContext.builder()
          .securityReferences(defaultAuth())
          .build();
    }

    List<SecurityReference> defaultAuth() {
      AuthorizationScope authorizationScope
          = new AuthorizationScope("global", "accessEverything");
      AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
      authorizationScopes[0] = authorizationScope;
      return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }
}
