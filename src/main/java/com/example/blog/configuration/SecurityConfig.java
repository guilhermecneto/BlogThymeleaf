package com.example.blog.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
    private static final String[] AUTH_LIST = {
            "/",
            "/index",
            "/posts",
            "/posts/{id}",
            "/postDetails/{id}"
        };

	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        //configs
	        http.csrf().disable().authorizeRequests()
            .antMatchers(AUTH_LIST).permitAll()
            .anyRequest().authenticated()
            .and().formLogin().permitAll()
            .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	    	
	        return http.build();
	    }

	    @Bean
	    public InMemoryUserDetailsManager userDetailsService() {
	        UserDetails user = User.withDefaultPasswordEncoder()
	            .username("user")
	            .password("senha")
	            .roles("USER")
	            .build();
	        return new InMemoryUserDetailsManager(user);
	    }

	   
	    @Bean
	    public WebSecurityCustomizer webSecurityCustomizer() {
	        return (web) -> web.ignoring().antMatchers("/images/**");
	    }
	    
	   //public void configure(WebSecurity web) throws Exception{
	     //  web.ignoring().antMatchers("/resources/**");
	       //web.ignoring().antMatchers("/bootstrap/**", "/style/**");
	    //}
}


