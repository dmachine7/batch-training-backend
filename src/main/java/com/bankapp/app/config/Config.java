package com.bankapp.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class Config {
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user1 = User.builder().username("atul").password(passwordEncoder().encode("atul1234")).roles("ADMIN").build();
		UserDetails user2 = User.builder().username("devang").password(passwordEncoder().encode("devang1234")).roles("ADMIN").build();
		UserDetails user3 = User.builder().username("rahul").password(passwordEncoder().encode("rahul1234")).roles("ADMIN").build();
		UserDetails user4 = User.builder().username("sahil").password(passwordEncoder().encode("sahil1234")).roles("ADMIN").build();
		UserDetails user5 = User.builder().username("rohan").password(passwordEncoder().encode("rohan1234")).roles("ADMIN").build();
		return new InMemoryUserDetailsManager(user1, user2, user3, user4, user5);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean 
	public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
		return builder.getAuthenticationManager();
	}
}
