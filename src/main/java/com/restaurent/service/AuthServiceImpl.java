package com.restaurent.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurent.config.JwtService;
import com.restaurent.dto.LoginResponse;
import com.restaurent.dto.UserDto;
import com.restaurent.dto.request.LoginRequest;
import com.restaurent.dto.request.SignupRequest;
import com.restaurent.entity.Role;
import com.restaurent.entity.User;
import com.restaurent.repository.UserRepository;
import com.restaurent.service.interfaces.AuthService;

import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

	UserRepository userRepository;
	PasswordEncoder passwordEncoder;
	AuthenticationManager authenticationManager;
	JwtService jwtService;
	UserDetailsService userDetailsService;

	@Transactional
	@Override
	public UserDto createUser(SignupRequest request) {

		boolean userExists = userRepository.existsByEmail(request.getEmail());

		if (userExists) {
			throw new EntityExistsException("User already exists with email: " + request.getEmail());
		}

		User user = User.builder().email(request.getEmail()).name(request.getName()).password(request.getPassword())
				.build();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Role role = new Role(request.getRole());
		user.add(role);

		User createdUser = userRepository.save(user);
		UserDto createUserDto = UserDto.builder().email(createdUser.getEmail()).name(createdUser.getName())
				.id(createdUser.getId()).build();
		return createUserDto;
	}

	@Override
	public LoginResponse login(LoginRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		// throws error here if not authenticated

		UserDetails userDetail = userDetailsService.loadUserByUsername(request.getEmail());
		String token = jwtService.generateToken(userDetail);
		return LoginResponse.builder().token(token).build();
	}

}
