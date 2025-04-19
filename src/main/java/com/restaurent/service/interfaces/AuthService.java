package com.restaurent.service.interfaces;

import com.restaurent.dto.LoginResponse;
import com.restaurent.dto.UserDto;
import com.restaurent.dto.request.LoginRequest;
import com.restaurent.dto.request.SignupRequest;

public interface AuthService {

	UserDto createUser(SignupRequest request);

	LoginResponse login(LoginRequest request);

}
