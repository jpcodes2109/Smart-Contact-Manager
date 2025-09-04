package com.smartcontactmanager.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;

@Configuration

public class SecurityConfig {

    private InMemoryUserDetailsManager inMemoryUserDetailsManager;
    
    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails user = User.withUsername("Jayant")
                .password("Jayant@12345")
                .roles("USER")
                .build();

        var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user);
        return inMemoryUserDetailsManager;
    }



}
