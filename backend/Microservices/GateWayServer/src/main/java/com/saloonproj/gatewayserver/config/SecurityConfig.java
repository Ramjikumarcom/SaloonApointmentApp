package com.saloonproj.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebFlux
public class SecurityConfig {


//    private final JwtAuthenticationConverter jwtAuthenticationConverter;
////
//    public SecurityConfig(JwtAuthenticationConverter jwtAuthenticationConverter) {
//        this.jwtAuthenticationConverter = jwtAuthenticationConverter;
//    }
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(
                exchanges -> exchanges
                        .pathMatchers("/api/category/salonOwner/**",
                                "/api/notifications/saloon-owner/**",
                                "/api/service-offering/saloon-owner/**")
                        .hasAnyRole("SALOON_OWNER")
                        .pathMatchers("/auth/**").permitAll()
                        .pathMatchers("/api/notifications/**").permitAll()
                        .pathMatchers("/api/saloonService/**",
                                "/api/bookings/**", "/api/category/**"
                                , "/api/payments/**", "/api/service-offering/**",
                                "/api/users/**",
                                "/api/reviews/**",
                                "/api/notifications/**")
                        .hasAnyRole("CUSTOMER", "SALOON_OWNER", "ADMIN")

        ).oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec.jwt(jwtSpec ->
                jwtSpec.jwtAuthenticationConverter(grantAuthoritiesExtractor())));
        http.csrf(ServerHttpSecurity.CsrfSpec::disable).
        cors(cors->cors.configurationSource(corsConfigurationSource()));

        return http.build();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration=new CorsConfiguration();
        configuration.setAllowedOrigins(
                Arrays.asList(
                        "http://localhost:3000",
                        "http://localhost:5173"

                )
        );

        configuration.setAllowedMethods(Arrays.asList("GET",
                "POST","PUT","PATCH","OPTIONS","DELETE"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setExposedHeaders(Collections.singletonList("Authorization"));
        configuration.setAllowCredentials(true);
        configuration.setMaxAge(36000L);

        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**",configuration);

        return source;

    }

    private Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> grantAuthoritiesExtractor() {
        JwtAuthenticationConverter jwtAuthenticationconverter = new JwtAuthenticationConverter();
        jwtAuthenticationconverter.setJwtGrantedAuthoritiesConverter(
                new KeyCloakRoleConverter());
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationconverter);


    }
}
