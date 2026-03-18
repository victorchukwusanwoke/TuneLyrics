package com.example.tunelyrics.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers("/admin/**").hasRole("ADMIN") // Admin access only.
                                .requestMatchers("/uploader/**").hasRole("UPLOADER") // Uploader access only.
                                .requestMatchers("/**").permitAll() // Allow unrestricted access to public pages like login and home.
                )
                .formLogin(form -> form
                        .loginPage("/login") // Custom login page.
                        .loginProcessingUrl("/login") // Process login form submissions.
                        .defaultSuccessUrl("/", true) // Redirect to home after successful login.
                        .permitAll() // Allow anyone to access the login page.
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // Custom logout URL.
                        .permitAll()); // Allow anyone to logout.

        return http.build();
    }

}
