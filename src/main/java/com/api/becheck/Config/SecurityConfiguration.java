package com.api.becheck.Config;
import com.api.becheck.filter.TokenAuthenticationFilter;
import com.api.becheck.repositories.UsuarioRepository;
import com.api.becheck.services.TokenService;
import com.api.becheck.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
/*
The prePostEnabled property enables Spring Security pre/post annotations.
The securedEnabled property determines if the @Secured annotation should be enabled.
The jsr250Enabled property allows us to use the @RoleAllowed annotation.
*/
public class SecurityConfiguration{

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository repository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.POST, "/usuario/**").permitAll()
                .antMatchers(HttpMethod.POST,"/hospital").hasAnyRole()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(new TokenAuthenticationFilter(tokenService, repository), UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }
    @Bean
    public AuthenticationManager authManager(HttpSecurity http, UsuarioService usuarioService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(usuarioService)
                .passwordEncoder(new BCryptPasswordEncoder())
                .and()
                .build();
    }

}