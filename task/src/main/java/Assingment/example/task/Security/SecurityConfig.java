package Assingment.example.task.Security;

import Assingment.example.task.Service.UserServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    private final UserServiceImp userServiceImp;
    private final JwtAuthendicationFilter jwtAuthendicationFilter;

    public SecurityConfig(UserServiceImp userServiceImp, JwtAuthendicationFilter jwtAuthendicationFilter) {
        this.userServiceImp = userServiceImp;
        this.jwtAuthendicationFilter = jwtAuthendicationFilter;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(

                        req -> req.requestMatchers("/api/v1/login").permitAll()
                                .requestMatchers("/api/v1/signup").permitAll()
                                .requestMatchers("/api/v1/task/**").permitAll()
                                .anyRequest()
                                .authenticated()
                ).userDetailsService(userServiceImp)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthendicationFilter , UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
