/*
 * package com.tw.config;
 * 
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration;
 * 
 * import io.swagger.v3.oas.models.Components; import
 * io.swagger.v3.oas.models.OpenAPI; import io.swagger.v3.oas.models.info.Info;
 * import io.swagger.v3.oas.models.security.SecurityScheme; import
 * io.swagger.v3.oas.models.security.SecurityRequirement;
 * 
 * @Configuration public class SwaggerConfig {
 * 
 * @Bean public OpenAPI customOpenAPI() { // Define a Bearer Token security
 * scheme SecurityScheme bearerAuth = new SecurityScheme()
 * .type(SecurityScheme.Type.HTTP) .scheme("bearer") .bearerFormat("JWT")
 * .in(SecurityScheme.In.HEADER) .name("Authorization");
 * 
 * // Add the security scheme to the components section OpenAPI openAPI = new
 * OpenAPI() .components(new Components() .addSecuritySchemes("bearerAuth",
 * bearerAuth)) .info(new Info().title("Your API Title").version("1.0.0"));
 * 
 * // Define security requirements using the defined scheme SecurityRequirement
 * securityItem = new SecurityRequirement().addList("bearerAuth");
 * openAPI.addSecurityItem(securityItem);
 * 
 * return openAPI; } }
 * 
 */