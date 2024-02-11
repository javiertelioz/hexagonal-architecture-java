package com.inicions.tasks.infrastructure.config;

import com.inicions.tasks.infrastructure.security.CustomAccessDeniedHandler;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.inicions.tasks.infrastructure.security.JwtAuthenticationFilter;
import com.inicions.tasks.infrastructure.security.JwtAuthorizationFilter;
import com.inicions.tasks.infrastructure.security.jwt.JwtUtils;
import com.inicions.tasks.infrastructure.services.UserDetailsServiceImpl;

@Configuration
@EnableMethodSecurity()
public class SecurityConfig {

    private final JwtUtils jwtUtils;

    private final UserDetailsServiceImpl userDetailsService;

    private final JwtAuthorizationFilter authorizationFilter;

    private static final String[] SWAGGER_PATHS = {
            "/swagger-ui.html",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/webjars/swagger-ui/**"
    };


    public SecurityConfig(
            JwtUtils jwtUtils,
            UserDetailsServiceImpl userDetailsService,
            JwtAuthorizationFilter authorizationFilter) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
        this.authorizationFilter = authorizationFilter;
    }

    @Bean
    SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity,
            AuthenticationManager authenticationManager) throws Exception {

        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtUtils);
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/api/v1/users/login");

        return httpSecurity
                .csrf(config -> config.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/hello").permitAll();
                    auth.requestMatchers(SWAGGER_PATHS).permitAll();
                    auth.anyRequest().authenticated();
                })
                .sessionManagement(session -> {
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                })
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptions ->
                        exceptions.accessDeniedHandler(new CustomAccessDeniedHandler())
                )
                .build();
    }

//    @Bean
//    UserDetailsService userDetailsService(){
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("santiago")
//                .password("1234")
//                .roles()
//                .build());
//
//        return manager;
//    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and().build();
    }
}
