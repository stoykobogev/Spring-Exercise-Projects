package org.softuni.eventures.config;

import org.softuni.eventures.entities.User;
import org.softuni.eventures.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import java.util.UUID;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Autowired
    public ApplicationSecurityConfiguration(UserService userService) {
        this.userService = userService;
    }

    private CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();

        repository.setSessionAttributeName("_csrf");

        return repository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf()
//                    .disable()
                    .csrfTokenRepository(csrfTokenRepository())
                .and()
                .authorizeRequests()
                    .antMatchers("/", "/login", "/register").permitAll()
                    .antMatchers("/events/all", "/events/mine", "/home", "/orders/send")
                        .hasAnyAuthority("USER", "ADMIN")
                    .antMatchers("/events/create").hasAuthority("ADMIN")
                    .antMatchers("/css/**", "/js/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .usernameParameter("usernameOrEmail")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/home")
                .and()
                .rememberMe()
                    .rememberMeParameter("rememberMe")
                    .key(UUID.randomUUID().toString())
                    .userDetailsService(this.userService)
                    .rememberMeCookieName("remember")
                    .tokenValiditySeconds(1200)
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                .and()
                .exceptionHandling()
                    .accessDeniedPage("/unauthorized");
    }
}
