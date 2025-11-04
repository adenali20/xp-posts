package com.xp.xpposts.config;
import com.xp.xpposts.filter.JWTTokenGeneratorFilter;
import com.xp.xpposts.filter.JWTTokenValidatorFilter;
import com.xp.xpposts.service.MyUserDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corseConfig->corseConfig.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(List.of("http://localhost:3000","https://dev.adenali.com","http://adenali.com","http://10.0.0.167:3000"));
                        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                        config.setAllowedHeaders(List.of("*"));
                        config.setAllowCredentials(true);
                        return config;
                    }
                }))
                .csrf(hcsrf -> hcsrf.disable())
                .authorizeHttpRequests(authorize -> authorize
//                                .requestMatchers("api/users/salary").hasRole("ADMIN")
                                .requestMatchers("/api/expensesrv/user/signup").permitAll()
                                .requestMatchers("/api/expensesrv/user/login").permitAll()
                                .anyRequest().authenticated()
                )
                .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
                .formLogin(flc-> flc.disable())
                .httpBasic(withDefaults());
        return http.build();
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/api/expensesrv/user/**","/error","/api/expensesrv/api/auth/login","/login").permitAll()
//                        .anyRequest().authenticated()
//                )
////                formLogin(flc->flc.loginProcessingUrl("/api/expensesrv/api/auth/login")
////                        .successHandler((request, response, authentication) -> {
////                            response.setStatus(HttpServletResponse.SC_OK);
////                        }).failureHandler((request, response, exception) -> {
////                            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
////                        }).permitAll())
////                .sessionManagement(sess -> sess
////                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
////                )
//                .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
//                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
//                .formLogin(withDefaults())
//                .httpBasic(withDefaults());;
//
//        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}