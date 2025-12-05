package com.mahir.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mahir.JWT.AuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final AuthenticationFilter authenticationFilter;
    private final AuthenticationEntryPoint authenticationEntryPoint;

    SecurityConfig(AuthenticationProvider authenticationProvider,
            AuthenticationFilter authenticationFilter,
            AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationProvider = authenticationProvider;
        this.authenticationFilter = authenticationFilter;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf((csrf) -> csrf.disable())
                .cors()
                .and()
                .authorizeHttpRequests((request) -> request

                        .requestMatchers("/authenticate", "/isauthenticate","/api/city/save","/rest/api/district/save","/rest/api/neighborhood/save","/Rest/Api/SaveAddress","/api/saveAccount","/Request/Mapping/save","/api/gallerists/save").permitAll()

                        .anyRequest().authenticated())
                .authenticationProvider(authenticationProvider)
                .exceptionHandling((exceptions) -> exceptions.authenticationEntryPoint(authenticationEntryPoint))
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}