/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.bilibili.myblogbackend.config.SecurityConfig
 *  com.bilibili.myblogbackend.filter.JwtAuthenticationFilter
 *  com.bilibili.myblogbackend.handler.AnnonyAuthenticationHandler
 *  com.bilibili.myblogbackend.handler.CustomerAccessDeniedHandler
 *  jakarta.servlet.Filter
 *  lombok.Generated
 *  org.springframework.context.annotation.Bean
 *  org.springframework.context.annotation.Configuration
 *  org.springframework.security.authentication.AuthenticationManager
 *  org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
 *  org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
 *  org.springframework.security.config.annotation.web.builders.HttpSecurity
 *  org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer
 *  org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer$AuthorizedUrl
 *  org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
 *  org.springframework.security.crypto.password.PasswordEncoder
 *  org.springframework.security.web.AuthenticationEntryPoint
 *  org.springframework.security.web.SecurityFilterChain
 *  org.springframework.security.web.access.AccessDeniedHandler
 *  org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
 */
package com.bilibili.myblogbackend.config;

import com.bilibili.myblogbackend.filter.JwtAuthenticationFilter;
import com.bilibili.myblogbackend.handler.AnnonyAuthenticationHandler;
import com.bilibili.myblogbackend.handler.CustomerAccessDeniedHandler;
import jakarta.servlet.Filter;
import lombok.Generated;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity(prePostEnabled=true)
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtFilter;
    private final CustomerAccessDeniedHandler customerAccessDeniedHandler;
    private final AnnonyAuthenticationHandler annonyAuthenticationHandler;

    @Value("${app.cors.allowed-origins}")
    private String allowedOrigins;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(withDefaults());
        http.authorizeHttpRequests(auth -> ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)auth.requestMatchers(new String[]{"/comment/video"})).authenticated().requestMatchers(new String[]{"/message/submit"})).authenticated().requestMatchers(new String[]{"/treehole/add"})).authenticated().requestMatchers(new String[]{"/file/**"})).authenticated().anyRequest()).permitAll());
        http.addFilterBefore((Filter)this.jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling(handler -> handler.accessDeniedHandler((AccessDeniedHandler)this.customerAccessDeniedHandler).authenticationEntryPoint((AuthenticationEntryPoint)this.annonyAuthenticationHandler));
        return (SecurityFilterChain)http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of(allowedOrigins.split(",")));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Generated
    public SecurityConfig(JwtAuthenticationFilter jwtFilter, CustomerAccessDeniedHandler customerAccessDeniedHandler, AnnonyAuthenticationHandler annonyAuthenticationHandler) {
        this.jwtFilter = jwtFilter;
        this.customerAccessDeniedHandler = customerAccessDeniedHandler;
        this.annonyAuthenticationHandler = annonyAuthenticationHandler;
    }
}

