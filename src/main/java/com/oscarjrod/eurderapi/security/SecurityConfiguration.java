package com.oscarjrod.eurderapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private static final String H2_CONSOLE_PATH = "/h2-console/**";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

        //Temporarily opening spring boot security
        http.csrf().disable();

        return http.build();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() { // Enables use of H2 console
        return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher(H2_CONSOLE_PATH));
    }

}
