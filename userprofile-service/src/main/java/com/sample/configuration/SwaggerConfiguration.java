package com.sample.configuration;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Swagger Configs
 */

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {


    /**
     *
     * @return the docket
     */
    @Bean
    public Docket accountsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("users")
                .apiInfo(apiInfo())
                .select()
                .paths(accountsPaths())
                .build();
    }

    /**
     *
     * @return the predicate
     */
    private Predicate<String> accountsPaths() {
        return PathSelectors.regex("/users.*");
    }

    /**
     * Api info.
     *
     * @return the api info
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("User Profile API")
                .license("User Profile MicroService Swagger API")
                .version("1.0-SNAPSHOT")
                .build();

    }




}
