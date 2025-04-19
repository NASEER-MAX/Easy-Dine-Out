package com.restaurent.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	final JwtService jwtService;
	final UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String requestUri = request.getRequestURI();

		// Check if the request URI is for signup or login
		if (requestUri.equals("/api/v1/auth/signup") || requestUri.equals("/api/v1/auth/login")) {
			// Allow the filter chain to proceed without custom logic
			filterChain.doFilter(request, response);
			return;
		}

		final String authHeader = request.getHeader("Authorization");

		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
			// In the next security Filter Chain it will be marked Unauthenticated, or
			// /signup ..
			filterChain.doFilter(request, response);
			return;
		}

		final String jwt = authHeader.substring(7);
		final String userEmail = jwtService.extractUserName(jwt);

		if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			// We have to set this Authentication So that in the next filter chain it is
			// marked Authenticated

			UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

			// Mark Authenticated if and only if it valid
			if (jwtService.isTokenValid(jwt, userDetails)) {

				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				// Now we say that this jwt request is Authenticated
				SecurityContextHolder.getContext().setAuthentication(authentication);

			}

		}

		filterChain.doFilter(request, response);
	}

}
