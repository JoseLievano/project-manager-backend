package com.bgsystem.bugtracker.configuration.security;

import com.bgsystem.bugtracker.configuration.filter.JWTTokenGeneratorFilter;
import com.bgsystem.bugtracker.configuration.filter.JWTTokenValidatorFilter;
import com.bgsystem.bugtracker.shared.models.securityUser.SecurityUserServiceImplements;
import com.bgsystem.bugtracker.shared.models.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserRepository userRepository;

    private final SecurityUserServiceImplements securityUserServiceImplements;

    @Autowired
    public SecurityConfig(UserRepository userRepository, SecurityUserServiceImplements securityUserServiceImplements) {
        this.userRepository = userRepository;
        this.securityUserServiceImplements = securityUserServiceImplements;
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors().configurationSource(request -> {
                    CorsConfiguration config = new CorsConfiguration();
                    config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                    config.setAllowedMethods(Collections.singletonList("*"));
                    config.setAllowCredentials(true);
                    config.setAllowedHeaders(Collections.singletonList("*"));
                    config.setExposedHeaders(List.of("Authorization"));
                    config.setMaxAge(3600L);
                    return config;
                }).and().csrf().disable()
                .addFilterBefore(new JWTTokenValidatorFilter(securityUserServiceImplements), BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTTokenGeneratorFilter(userRepository), BasicAuthenticationFilter.class)
                .authorizeHttpRequests((auth) -> auth
                        .anyRequest().authenticated()
        ).httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
