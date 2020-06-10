package org.openclassroom.project.librarywebapp.security.config;

import org.openclassroom.project.librarywebapp.security.authentication.CustomAuthenticationProvider;
import org.openclassroom.project.librarywebapp.security.handlers.CustomAuthenticationSuccessHandler;
import org.openclassroom.project.librarywebapp.security.handlers.CustomLogoutSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Spring Security configuration class allowing to define access restrictions, login and logout parameters, the {@link AuthenticationProvider} and the {@link PasswordEncoder}.
 * */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * The Spring Security configuration method allowing to define access restrictions, login and logout parameters via the {@link HttpSecurity} configuration.
     *
     * @param http - {@link HttpSecurity} object providing its configuration methods.
     * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                    .frameOptions().sameOrigin()
                    .and()
                .authorizeRequests()
                    .antMatchers("/user/parameter**").authenticated()
                    .antMatchers("/", "/search", "/user/**", "/book-detail/**", "/css/**", "/js/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .successHandler(new CustomAuthenticationSuccessHandler())
                    .failureUrl("/usager/failure-login")
                    .permitAll()
                    .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessHandler(new CustomLogoutSuccessHandler())
                    .invalidateHttpSession(true)
                    .and()
                .csrf().disable();
    }

    /**
     * Allows to configure the {@link PasswordEncoder} using one of its implementations, here the {@link BCryptPasswordEncoder} instance.
     * It allows you to encode a password to protect it.
     *
     * @return An instance of {@link BCryptPasswordEncoder}.
     * */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the application via the {@link AuthenticationManagerBuilder} to use a custom class of the {@link AuthenticationProvider}.
     *
     * @param auth - The {@link AuthenticationManagerBuilder} to configure a part of Spring Security.
     * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(new CustomAuthenticationProvider());
    }

}
