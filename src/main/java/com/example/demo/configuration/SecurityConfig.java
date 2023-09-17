package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            	.authorizeRequests()
            	.requestMatchers("/","/shop/**","/register","/h2-console/**").permitAll()
            	.requestMatchers("/admin/**").hasRole("ADMIN")
            	.anyRequest()
            	.authenticated()
            	.and()
            	.formLogin()
            	.loginPage("/login")
            	.permitAll()
            	.failureUrl("/login?error=true")
            	.defaultSuccessUrl("/")
            	.usernameParameter("email")
            	.passwordParameter("password")
            	.and()
            	.oauth2Login()
            	.loginPage("/login")
            	.successHandler(googleOAuth2SuccessHandler)
            	.and()
            	.logout()
            	.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            	.logoutSuccessUrl("/login")
            	.invalidateHttpSession(true)
            	.deleteCookies("JSESSIONID")
            	.and()
            	.exceptionHandling()
            	.and()
            	.csrf()
            	.disable();
        http.headers().frameOptions().disable();
        return http.build();
    }
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
