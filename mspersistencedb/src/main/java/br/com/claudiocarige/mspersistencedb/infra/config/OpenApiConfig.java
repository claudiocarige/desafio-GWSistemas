package br.com.claudiocarige.mspersistencedb.infra.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info( new Info()
                        .title( "API Rest with Spring Boot 3 and Java 17 " )
                        .version( "v1" )
                        .description( "This API is a project challenge proposed by the company GW SISTEMAS, " +
                                "which aims to provide access to delivery control" )
                        .license( new License()
                                .name( "Apache 2.0" )
                                .url( "https://springdoc.org" ) ) );
    }

}
