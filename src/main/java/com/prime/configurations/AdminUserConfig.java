package com.prime.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.prime.controllers.AdminUserSecurity;
import com.prime.services.AuthenticationSuccessHandlr;


@Configuration
@EnableWebSecurity
public class AdminUserConfig {
	@Autowired
	AdminUserSecurity userDetails;
	
	@Bean
	public PasswordEncoder passwordEncrypter() {
		return new BCryptPasswordEncoder();
	}
	@Bean 
	public UserDetailsService userdet() {
		return userDetails;
		
	}
	@Bean 
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider authprovider =new DaoAuthenticationProvider();
		authprovider.setPasswordEncoder(passwordEncrypter());
		authprovider.setUserDetailsService(userDetails);
		return authprovider;
	}
  @Bean 
  public SecurityFilterChain securityFilterChain(HttpSecurity httpsecurity) throws Exception{
	  return httpsecurity.csrf(httpSecurityCsrfConfigurer->httpSecurityCsrfConfigurer.disable())
			  .authorizeHttpRequests(registry->{
		  registry.requestMatchers("/home").permitAll();
		  registry.requestMatchers("/user/**").hasRole("USER");
		  registry.requestMatchers("/admin/**").hasRole("ADMIN");
		  registry.anyRequest().authenticated();
	  }).formLogin(formLogin->formLogin
			  .loginPage("/login1.html")
			  .permitAll()
			  .successHandler(new AuthenticationSuccessHandlr())
			  .failureUrl("/login1.html")
	  
	  ).build();
	  
  }
  
	
}
