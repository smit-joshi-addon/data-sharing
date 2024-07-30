package com.truetrack.sharing.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truetrack.sharing.config.auth.jwt.JwtHelper;
import com.truetrack.sharing.dtos.AuthRequest;
import com.truetrack.sharing.dtos.AuthResponse;
import com.truetrack.sharing.dtos.SignUpRequest;
import com.truetrack.sharing.entity.Users;
import com.truetrack.sharing.service.UserService;
import com.truetrack.sharing.service.impl.UserDetailsServiceImpl;

@RestController
@RequestMapping("/api/v1/auth")
public class ApiAuthController {

	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService;
	private final JwtHelper jwtHelper;
	private final UserService userService;

	public ApiAuthController(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService,
			JwtHelper jwtHelper, UserService userService) {
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
		this.jwtHelper = jwtHelper;
		this.userService = userService;
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> authenticateUser(@RequestBody AuthRequest authRequest) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authRequest.email(), authRequest.password()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.email());

		String token = jwtHelper.generateToken(userDetails);

		return ResponseEntity.ok(new AuthResponse(token, jwtHelper.getExpirationDateFromToken(token)));
	}

	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> registerUser(@RequestBody SignUpRequest signUpRequest) {

		if(userService.createUser(Users.builder().email(signUpRequest.email()).password(signUpRequest.password())
				.name(signUpRequest.name())
				.build()) == null)
		 		    return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);

		UserDetails userDetails = userDetailsService.loadUserByUsername(signUpRequest.email());

		String token = jwtHelper.generateToken(userDetails);

		return ResponseEntity.ok(new AuthResponse(token, jwtHelper.getExpirationDateFromToken(token)));
	}

	@GetMapping("/login")
	public ResponseEntity<String> authenticateUser() {
		return new ResponseEntity<>("Login Using Get Request Is Not Supported", HttpStatus.METHOD_NOT_ALLOWED);
	}

}