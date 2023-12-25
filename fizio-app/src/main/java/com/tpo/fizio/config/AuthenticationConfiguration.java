package com.tpo.fizio.config;

import com.tpo.fizio.security.JwtTokenConverter;
import com.tpo.fizio.security.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.method.AuthorizationManagerBeforeMethodInterceptor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @author Tadej Delopst
 */
@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(SecurityProperties.class)
public class AuthenticationConfiguration {

    private final SecurityProperties securityProperties;

    public AuthenticationConfiguration(@Autowired SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .httpBasic().disable()
                .cors(cors -> cors.configurationSource(toCorsConfigurationSource()))
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .headers(headers -> headers.frameOptions().disable())
                .authorizeHttpRequests(authorize -> authorize
                        .antMatchers(HttpMethod.OPTIONS).permitAll()
                        .antMatchers(HttpMethod.GET, "/rest/v1/app").permitAll()
                        .antMatchers(HttpMethod.GET, "/health").permitAll()
                        .antMatchers("/rest/v1/**")
                        .authenticated()
                )
                .oauth2ResourceServer(resourceServer -> resourceServer
                        .jwt(jwt -> jwt
                                .decoder(JwtDecoders.fromIssuerLocation(securityProperties.getIssuerUri()))
                                .jwtAuthenticationConverter(new JwtTokenConverter(
                                        securityProperties.getUsernameClaim(),
                                        securityProperties.getNameClaim(),
                                        securityProperties.getRolesPath()))
                        )
                ).build();
    }

    @Bean
    public AuthorizationManagerBeforeMethodInterceptor authorizationManagerBeforeMethodInterceptor() {
        return AuthorizationManagerBeforeMethodInterceptor.secured();
    }

    @SuppressWarnings("DuplicatedCode")
    private CorsConfigurationSource toCorsConfigurationSource() {
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        corsConfiguration.addAllowedOriginPattern(CorsConfiguration.ALL);
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
        corsConfiguration.setAllowCredentials(true);

        corsConfiguration.setMaxAge(24L * 3600L);
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return urlBasedCorsConfigurationSource;
    }
}
