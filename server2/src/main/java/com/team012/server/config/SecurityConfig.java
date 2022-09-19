//package com.team012.server.config;
//
//import com.team012.server.config.filter.JwtAuthenticationFilter;
//import com.team012.server.config.filter.JwtAuthorizationFilter;
//import com.team012.server.config.oauth.PrincipalOauth2UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.filter.CorsFilter;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//
//    private final CorsFilter corsFilter;
////    private final MemberRepository memberRepository;
//    private final PrincipalOauth2UserService principalOauth2UserService;
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http.csrf().disable();
//        http.headers().frameOptions().disable();
//        http.httpBasic().disable()
//                .apply(new CustomDsl());
//
//        http.authorizeRequests()
//                .antMatchers("/v1/member/user/**")
//                .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
//                .antMatchers("/v1/member/manager/**")
//                .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
//                .antMatchers("/v1/member/admin/**")
//                .access("hasRole('ROLE_ADMIN')")
//                .anyRequest().permitAll();
//
//        http.formLogin()
//                .loginPage("/v1/member/login")
//                .loginProcessingUrl("/login")
//                .defaultSuccessUrl("/");
//
//        http.oauth2Login()
//                .loginPage("/login")
//                .userInfoEndpoint()
//                .userService(principalOauth2UserService);
//
//        return http.build();
//    }
//
//
//    public class CustomDsl extends AbstractHttpConfigurer<CustomDsl, HttpSecurity> {
//
//        @Override
//        public void configure(HttpSecurity builder) throws Exception {
//            AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
//            builder
//                    .addFilter(corsFilter)
//                    .addFilter(new JwtAuthenticationFilter(authenticationManager));
////                    .addFilter(new JwtAuthorizationFilter(authenticationManager, memberRepository));
//        }
//    }
//}