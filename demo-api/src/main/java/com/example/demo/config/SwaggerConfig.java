package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author : Fate
 * @date : 2019/5/28
 */
@Configuration
@Profile({"dev", "test"})
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket controllerApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .groupName("controllers")
        .apiInfo(apiInfo())
        .select()
        .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Demo")
        .description("Demo")
        .version("v1.1")
        .build();
  }
}