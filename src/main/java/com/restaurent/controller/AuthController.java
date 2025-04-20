package com.restaurent.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurent.dto.LoginResponse;
import com.restaurent.dto.UserDto;
import com.restaurent.dto.request.LoginRequest;
import com.restaurent.dto.request.SignupRequest;
import com.restaurent.service.interfaces.AuthService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {

	AuthService authService;

	@PostMapping("/signup")
	ResponseEntity<UserDto> signup(@RequestBody SignupRequest request) {
		UserDto createdUser = authService.createUser(request);
		return new ResponseEntity<>(createdUser, HttpStatus.OK);
	}

	@PostMapping("/login")
	ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		LoginResponse response = authService.login(request);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/test")
	public String test() {
		return "testing successfull";
	}

}
