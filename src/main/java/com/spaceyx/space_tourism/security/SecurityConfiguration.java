package com.spaceyx.space_tourism.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Autowired
    public DataSource dataSource;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .csrf().disable()
            .formLogin(login -> login
                .failureForwardUrl("/")
                .defaultSuccessUrl("/redirect-to-role-page", true)
                .usernameParameter("username")
                .passwordParameter("password")
            )
            .authorizeHttpRequests(authorization ->
                authorization
                    .requestMatchers("/login").permitAll()
                    .requestMatchers("/navettes/**").hasRole("Technicien")
                    .requestMatchers("/revisions/**").hasRole("Technicien")
                    .anyRequest().authenticated()
            )
            .build();
    }

    @Bean
    public UserDetailsService users() {
        User.UserBuilder userBuilder = User.builder()
            .passwordEncoder(pwd -> encoder().encode(pwd));
        UserDetails technicien = userBuilder
            .username("mrbricolage@spacyx.com")
            .password("33raptor")
            .roles("Technicien")
            .build();
        UserDetails planificateur = userBuilder
            .username("voyagevoyage@spaceyx.com")
            .password("ihaveaplan")
            .roles("Planificateur")
            .build();

        UserDetails voyageur = userBuilder
            .username("romain@mail.com")
            .password("çavaaller")
            .roles("Voyageur")
            .build();
        return new InMemoryUserDetailsManager(technicien, planificateur, voyageur);
}

@Bean
public PasswordEncoder encoder(){
    return new BCryptPasswordEncoder();
}
}