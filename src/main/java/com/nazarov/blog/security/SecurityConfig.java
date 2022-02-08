package com.nazarov.blog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.nazarov.blog.security.UserPermission.*;
import static com.nazarov.blog.security.UserRole.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(USER_ADMIN.name())
                .antMatchers(HttpMethod.POST, "/api/**").hasAuthority(POST_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/api/**").hasAuthority(POST_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE, "/api/**").hasAuthority(POST_WRITE.getPermission())
                .antMatchers(HttpMethod.GET, "/api/**").hasAnyRole(USER_ADMIN.name(), USER_MODERATOR.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {

        UserDetails karlLagerfeldUser = User.builder()
                .username("karl")
                .password(passwordEncoder.encode("123"))
                .roles(USER_USER.name())
//                .authorities(USER.getGrantedAuthorities())
                .build();


        UserDetails jotunUser = User.builder()
                .username("jotun")
                .password(passwordEncoder.encode("123"))
                .roles(USER_ADMIN.name())
//                .authorities(USER_ADMIN.getGrantedAuthorities())
                .build();

        UserDetails lokiUser = User.builder()
                .username("loki")
                .password(passwordEncoder.encode("123"))
                .roles(USER_MODERATOR.name())
//                .authorities(MODERATOR.getGrantedAuthorities())
                .build();


        return new InMemoryUserDetailsManager(
                karlLagerfeldUser,
                jotunUser,
                lokiUser
        );

    }


}
