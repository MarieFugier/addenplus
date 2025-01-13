package com.arketeam.addenplus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arketeam.addenplus.models.ProfilData;
import com.arketeam.addenplus.payload.request.LoginRequest;
import com.arketeam.addenplus.payload.request.SignupRequest;
import com.arketeam.addenplus.payload.response.JwtResponse;
import com.arketeam.addenplus.payload.response.MessageResponse;
import com.arketeam.addenplus.repository.ProfilDataRepository;
import com.arketeam.addenplus.security.jwt.JwtUtils;
import com.arketeam.addenplus.services.UserDetailsImpl;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Transactional
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	ProfilDataRepository profilDataRepository;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	PasswordEncoder encoder;
	@PostMapping ("/{pool}/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
	    Authentication authentication = authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    String jwt = jwtUtils.generateJwtToken(authentication);
	    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
	    return ResponseEntity.ok(new JwtResponse(jwt, 
	                         userDetails.getCodeUnique(), 
	                         userDetails.getNom()));
	  }
	  @PostMapping("/{pool}/signup")
	  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
	    if (profilDataRepository.existsByNom(signUpRequest.getUsername())) {
	      return ResponseEntity
	          .badRequest()
	          .body(new MessageResponse("Erreur : Le nom d'utilisateur est déjà pris!"));
	    }
	    ProfilData profilData = new ProfilData(signUpRequest.getUsername(), 
	    		  encoder.encode(signUpRequest.getPassword()));
	    
	    profilDataRepository.save(profilData);
	    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	  }
	}
	
