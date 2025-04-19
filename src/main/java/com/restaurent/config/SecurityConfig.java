package com.restaurent.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import com.restaurent.enums.UserRole;

import lombok.AllArgsConstructor;

@EnableMethodSecurity
@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class SecurityConfig {

	JwtAuthenticationFilter jwtAuthenticationFilter;
	AuthenticationProvider authenticationProvider;
	FilterChainExceptionHandler filterChainExceptionHandler;

//To disable secuity
//	@Bean
//	public SecurityFilterChain securityFilterChainDisable(HttpSecurity http) throws Exception {
//		http
//				.authorizeHttpRequests(auth -> auth
//						.anyRequest().permitAll()
//				)
//				.csrf(csrf -> csrf.disable()); // Modern way to disable CSRF
//		return http.build();
//	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		String[] swagger_url = {
				"/v3/api-docs/**",
				"/swagger-ui/**",
				"/v2/api-docs/**",
				"/swagger-resources/**",
				"/actuator/**"
		};

		String[] management_url = {
				"/api/v1/cities/**",
				"/api/v1/dishes/**",
				"/api/v1/table-types/**",
				"/api/v1/food-categories/**"
		};

		String[] owner_url = {
				"/api/v1/restaurants/**",
		};

		String[] customer_url = {
				"/api/v1/bookings/**",
		};

		String[] booking_mangement_url = {
				"/api/v1/bookings/restaurants/**",
		};
		http.csrf(csrf -> csrf.disable());

		http.authorizeHttpRequests(auth -> auth
				.requestMatchers("/api/v1/auth/**")
				.permitAll()
				.requestMatchers("/error")
				.permitAll()
				.requestMatchers(swagger_url)
				.permitAll()
				.requestMatchers(HttpMethod.POST, management_url).hasRole(UserRole.ADMIN.name())
				.requestMatchers(HttpMethod.PUT, management_url).hasRole(UserRole.ADMIN.name())
				.requestMatchers(HttpMethod.DELETE, management_url).hasRole(UserRole.ADMIN.name())
				.requestMatchers(HttpMethod.POST, owner_url).hasRole(UserRole.OWNER.name())
				.requestMatchers(HttpMethod.PUT, owner_url).hasRole(UserRole.OWNER.name())
				.requestMatchers(HttpMethod.DELETE, owner_url).hasRole(UserRole.OWNER.name())
				.requestMatchers(HttpMethod.POST, customer_url).hasRole(UserRole.CUSTOMER.name())
				.requestMatchers(HttpMethod.DELETE, customer_url).hasRole(UserRole.CUSTOMER.name())
				.requestMatchers(HttpMethod.GET, booking_mangement_url)
				.hasAnyRole(UserRole.OWNER.name(), UserRole.ADMIN.name())
				.anyRequest()
				.authenticated());

		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.authenticationProvider(authenticationProvider);
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(filterChainExceptionHandler, LogoutFilter.class);

		return http.build();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

}
