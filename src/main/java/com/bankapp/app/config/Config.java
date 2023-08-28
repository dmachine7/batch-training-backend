package com.bankapp.app.config;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.bankapp.app.model.Account;
import com.bankapp.app.repository.AccountRepository;

@Configuration
public class Config {
	
	private static final Logger logger = LoggerFactory.getLogger(Config.class);

	/**@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user1 = User.builder().username("atul").password(passwordEncoder().encode("atul1234")).roles("ADMIN").build();
		UserDetails user2 = User.builder().username("devang").password(passwordEncoder().encode("devang1234")).roles("ADMIN").build();
		UserDetails user3 = User.builder().username("rahul").password(passwordEncoder().encode("rahul1234")).roles("ADMIN").build();
		UserDetails user4 = User.builder().username("sahil").password(passwordEncoder().encode("sahil1234")).roles("ADMIN").build();
		UserDetails user5 = User.builder().username("rohan").password(passwordEncoder().encode("rohan1234")).roles("ADMIN").build();
		return new InMemoryUserDetailsManager(user1, user2, user3, user4, user5);
	}
	*/
	
	@Autowired
	private AccountRepository userRepository;
	
	public Config(AccountRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	@Bean
	public UserDetailsService userDetailsService() {
	    return new UserDetailsService() {
	        @Override
	        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {    
	            Optional<Account> accountOptional = userRepository.findByEmail(username);
	            
	            if (accountOptional.isPresent()) {
	                Account account = accountOptional.get();
	                System.out.println("my message " + account.getEmail());
	                return User.builder()
	                        .username(account.getEmail())
	                        .password(passwordEncoder().encode(account.getLog_pass()))
	                        .roles("ADMIN") 
	                        .build();
	            } else {
	                throw new UsernameNotFoundException("User not found with username: " + username);
	            }
	        }
	    };
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
