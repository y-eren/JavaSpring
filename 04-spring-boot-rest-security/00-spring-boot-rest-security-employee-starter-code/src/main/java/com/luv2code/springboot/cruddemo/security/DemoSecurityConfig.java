package com.luv2code.springboot.cruddemo.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

	//add support for JDBC no more hardcoded users
	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource)
	{
		// bu kod Spring Security'e JDBC authentication'ı bizim data sourcemuz ile kullanmasını söylüyor
		//return new JdbcUserDetailsManager(dataSource);
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		
		// define query to retrieve user by username
		jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id, pw, active from members where user_id=?");
		
		// define query to retrecve the auth/roles by username
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id , role from roles where user_id=?");
		
		return jdbcUserDetailsManager;
	}
//	@Bean
//	public InMemoryUserDetailsManager userDetailManager()
//	{
//		UserDetails john = User.builder()
//		.username("john")
//		.roles("EMPLOYEE")
//		.password("{noop}test123")
//		.build();
//		
//
//		UserDetails mary = User.builder()
//		.username("mary")
//		.roles("EMPLOYEE", "MANAGER")
//		.password("{noop}test123")
//		.build();
//		
//		UserDetails susan = User.builder()
//				.username("susan")
//				.roles("EMPLOYEE", "MANAGER", "ADMIN")
//				.password("{noop}test123")
//				.build();
//		
//		return new InMemoryUserDetailsManager(john,mary, susan);
//		
//	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests( configurer -> 
		configurer
				.requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
				.requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
				.requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
				.requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
				.requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
				);
		// use htttp basic authentication
		http.httpBasic(Customizer.withDefaults());
		//disable csrf
		// in general not required for statleess rest api that use post,put,delete and or patch
		http.csrf(csrf -> csrf.disable());
		return http.build();
	}
}
