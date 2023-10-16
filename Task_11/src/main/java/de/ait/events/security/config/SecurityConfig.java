package de.ait.events.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.ait.events.dto.StandardResponseDto;
import de.ait.events.security.details.AuthenticatedUser;
import de.ait.events.security.details.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, proxyTargetClass = true)
public class SecurityConfig {
    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
        httpSecurity
                .authorizeRequests()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/users/register/**").permitAll()
                .antMatchers("/api/**").authenticated();
        httpSecurity
                .exceptionHandling()
                .defaultAuthenticationEntryPointFor(((request, response, authException) -> {
                    fillResponse(response, HttpStatus.UNAUTHORIZED, "User unauthorized");
                }), new AntPathRequestMatcher("/api/**"))
                .and()
                .formLogin()
                .loginProcessingUrl("/api/login")
                .successHandler(
                        ((request, response, authentication) ->
                                fillResponse(response, HttpStatus.OK, "Login successful")))
                .failureHandler(
                        ((request, response, exception) ->
                                fillResponse(response, HttpStatus.UNAUTHORIZED,
                                        "Incorrect password and username")))
                .and()
                .logout()
                .logoutUrl("/api/logout")
                .logoutSuccessHandler((request, response, authentication) ->
                        fillResponse(response, HttpStatus.OK, "Logout successful"));

        return httpSecurity.build();
    }

    @Autowired
    public void bindUserDetailsServiceAndPasswordEncoder(UserDetailsService userDetailsService,
                                                         PasswordEncoder passwordEncoder,
                                                         AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    private void fillResponse(HttpServletResponse response, HttpStatus status, String message) {
        try {
            response.setStatus(status.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            StandardResponseDto responseDto = StandardResponseDto.builder()
                    .message(message)
                    .build();
            String body = objectMapper.writeValueAsString(responseDto);

            response.getWriter().write(body);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

    }
}

