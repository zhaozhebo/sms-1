package com.trialdata.sms.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.*;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = {"com.trialdata.sms.controller"})
public class SwaggerConfig {

  @Bean
  public Docket buildDocket() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(buildApiInf())
        .pathMapping("/")
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.trialdata.sms.controller"))
        .paths(PathSelectors.any())
        .build();
//        .securitySchemes(securitySchemes())
//        .securityContexts(securityContexts());
  }

  private ApiInfo buildApiInf() {
    return new ApiInfoBuilder()
        .title("SMS文档")
        .description("SMS文档")
        .build();
  }

  private List<ApiKey> securitySchemes() {
    List<ApiKey> apiKeys = new ArrayList<>();
    apiKeys.add(new ApiKey("Authorization", "Authorization", "header"));
    return apiKeys;
  }

  private List<SecurityContext> securityContexts() {
    List<SecurityContext> securityContexts = new ArrayList<>();
    securityContexts.add(
        SecurityContext.builder()
            .securityReferences(defaultAuth())
            .forPaths(PathSelectors.regex("^(?!auth).*$"))
            .build());
    return securityContexts;
  }

  private List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    List<SecurityReference> securityReferences = new ArrayList<>();
    securityReferences.add(new SecurityReference("Authorization", authorizationScopes));
    return securityReferences;
  }
}
