package com.team012.server.config;

import com.team012.server.config.filter.JwtAuthenticationFilter;
import com.team012.server.config.filter.JwtAuthorizationFilter;
import com.team012.server.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CorsFilter corsFilter;
    private final UsersRepository usersRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic()
                .disable()
                .apply(new CustomDsl())
                .and()
                .authorizeRequests()
                .antMatchers("/v1/users/company/**")
                .access("hasRole('USER_COMPANY') or hasRole('ROLE_ADMIN')")
                .antMatchers("/v1/users/customer/**")
                .access("hasRole('USER_CUSTOMER') or hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll();

        http.formLogin()
                .loginPage("/v1/users/login")
                .loginProcessingUrl("/v1/users/login")
                .defaultSuccessUrl("/");

//        http.oauth2Login()
//                .loginPage("/login")
//                .userInfoEndpoint()
//                .userService(principalOauth2UserService);

        return http.build();
    }


    public class CustomDsl extends AbstractHttpConfigurer<CustomDsl, HttpSecurity> {

        @Override
        public void configure(HttpSecurity builder) throws Exception {
            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
            builder
                    .addFilter(corsFilter)
                    .addFilter(new JwtAuthenticationFilter(authenticationManager))
                    .addFilter(new JwtAuthorizationFilter(authenticationManager, usersRepository));
        }
    }
}