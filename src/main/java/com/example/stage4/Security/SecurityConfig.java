package com.example.stage4.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configures security settings for the application, establishing it as an
 * authorization server.
 * 
 * This configuration disables session management, enables HTTP basic
 * authentication,
 * and configures authorization rules for inbound requests.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configures the security filter chain for HTTP requests.
     * 
     * Disables session management to make the application stateless,
     * disables CSRF protection as it's unnecessary without sessions,
     * and configures authorization rules. Requests to "/account/**" are permitted
     * without authentication, while all other requests require authentication.
     * 
     * @param http The HttpSecurity object to configure.
     * @return The configured SecurityFilterChain.
     * @throws Exception If an error occurs while configuring security.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // Disable sessions. We want a stateless application:
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // CSRF protection is merely extra overhead with session management disabled:
                .csrf(csrf -> csrf.disable())

                // All inbound requests must be authenticated:
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/account/**").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())

                .build();
    }

    /**
     * Configures an in-memory user details manager with a default user.
     * 
     * The default user has a username of "client", a password of "DoNotTell",
     * and "read" authority. The password is stored in plain text (noop).
     * 
     * @return The configured InMemoryUserDetailsManager.
     */
    @Bean
    public InMemoryUserDetailsManager users() {
        return new InMemoryUserDetailsManager(
                User.withUsername("client")
                        .password("{noop}DoNotTell")
                        .authorities("read")
                        .build());
    }
}
