package com.example.springboot.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm;
import org.springframework.security.web.SecurityFilterChain;

import com.example.springboot.security.jwt.JwtConfigurer;
import com.example.springboot.security.jwt.JwtTokenProvider;
import com.example.springboot.service.UsuarioServices;

import java.util.logging.Logger;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	private static final Logger logger = Logger.getLogger(SecurityConfig.class.getName());

	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Bean
	PasswordEncoder passwordEncoder() {
		Map<String, PasswordEncoder> encoders = new HashMap<>();
				
		Pbkdf2PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder("", 8, 185000, SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
		encoders.put("pbkdf2", pbkdf2Encoder);
		DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
		passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);
		return passwordEncoder;
	}
	
    @Bean
    AuthenticationManager authenticationManagerBean(
    		AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Adicione logs de depuração
    	logger.info("SecurityFilterChain " + http + "!");

        return http
                .httpBasic().disable()
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(
                    session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                    authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers(
                            "/auth/signin",
                            "/auth/refresh/**",
                            "/swagger-ui/**",
                            "/v3/api-docs/**"
                        ).permitAll()
                        .requestMatchers("/api/**").authenticated()
                        .requestMatchers("/users").denyAll()
                )
                .cors()
                .and()
                .apply(new JwtConfigurer(tokenProvider))
                .and()
                .build();
    }
}
