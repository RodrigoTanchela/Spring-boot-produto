package com.example.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot.model.vo.v1.security.AccountCredentialsVO;
import com.example.springboot.service.AuthServices;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Authentication")
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	AuthServices services;
	
	@SuppressWarnings("rawtypes")
	@Operation(summary = "Authenticates a user and returns a token")
	@PostMapping(value = "/signin")
	public ResponseEntity signin(@RequestBody AccountCredentialsVO data) {
		try {
			if (checkIfParamsIsNotNull(data))
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
			var token = services.signin(data);
			if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
			return token;
		} catch(Exception e) {
		    System.out.println("Erro ao efetuar login: " +data.getUsername() + data.getPassword());
		    e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
	}
	
	@SuppressWarnings("rawtypes")
	@Operation(summary = "Refresh token for authenticated user and returns a token")
	@PutMapping(value = "/refresh/{username}")
	public ResponseEntity refreshToken(@PathVariable("username") String username,
			@RequestHeader("Authorization") String refreshToken) {
		if (checkIfParamsIsNotNull(username, refreshToken))
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
		var token = services.refreshToken(username, refreshToken);
		if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
		return token;
	}

	private boolean checkIfParamsIsNotNull(String username, String refreshToken) {
		return refreshToken == null || refreshToken.isBlank() ||
				username == null || username.isBlank();
	}

	private boolean checkIfParamsIsNotNull(AccountCredentialsVO data) {
		return data == null || data.getUsername() == null || data.getUsername().isBlank()
				 || data.getPassword() == null || data.getPassword().isBlank();
	}
}
