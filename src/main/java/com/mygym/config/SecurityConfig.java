package com.mygym.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {
	
	private final MemberDetailService memberSecurityService;
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")	// URL에 따른 권한만 허용
			.antMatchers("/trainer/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_TRAINER')");
		
		http.exceptionHandling().accessDeniedPage("/access-denied");
		
        http.formLogin() 
        	.loginPage("/members/login")
        	.loginProcessingUrl("/members/login")
        	.defaultSuccessUrl("/");
        	
        http.logout()
        	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        	.invalidateHttpSession(true);
     
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(memberSecurityService).passwordEncoder(passwordEncoder());
    }
}
