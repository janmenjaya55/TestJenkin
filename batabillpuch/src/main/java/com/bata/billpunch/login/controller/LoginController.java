package com.bata.billpunch.login.controller;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bata.billpunch.login.bean.LoginRequest;
import com.bata.billpunch.login.bean.LoginResponse;
import com.bata.billpunch.login.bean.TokenRequest;
import com.bata.billpunch.login.bean.TokenResponse;
import com.bata.billpunch.login.config.JwtTokenUtil;
import com.bata.billpunch.login.service.LoginService;
import com.bata.billpunch.login.service.impl.UserDetailsImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;



@Component
@RestController
@CrossOrigin(origins = "*")
public class LoginController {


	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private LoginService loginService;
	
	@Value("${jwt.secret}")
    private String secret;
	
	
	@PostMapping("/batabps/login/authenticateOriginal")
	public ResponseEntity<LoginResponse> createAuthenticationTokenOriginal(@RequestBody LoginRequest authenticationRequest) {	
		
		final UserDetailsImpl userDetails = (UserDetailsImpl)loginService.loadUserByUsername(authenticationRequest.getUsername());
		
		
		if(userDetails.getUsername().contentEquals(authenticationRequest.getUsername()) && new BCryptPasswordEncoder().matches(authenticationRequest.getPassword(), userDetails.getPassword())) {
			final String token = jwtTokenUtil.generateToken(userDetails,userDetails.getFullname(),userDetails.getUserrole());
			return ResponseEntity.ok(new LoginResponse(token,userDetails.getUserrole()));
		}
		else {
		return ResponseEntity.ok(new LoginResponse("INVALID_CREDENTIALS",null));
		}
	}
	
	
	@PostMapping("/batabps/login/authenticate")
	public ResponseEntity<LoginResponse> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest) throws Exception {	
		
		final UserDetailsImpl userDetails = (UserDetailsImpl)loginService.loadUserByUsername(authenticationRequest.getUsername());
		
		
		//if(userDetails.getUsername().contentEquals(authenticationRequest.getUsername()) && new BCryptPasswordEncoder().matches(authenticationRequest.getPassword(), userDetails.getPassword())) {
			if(userDetails.getUsername().contentEquals(authenticationRequest.getUsername()) &&(jwtTokenUtil.getEncriptPassword(authenticationRequest.getPassword()).contentEquals(userDetails.getPassword()))) {
			
			final String token = jwtTokenUtil.generateToken(userDetails,userDetails.getFullname(),userDetails.getUserrole());
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			return ResponseEntity.ok(new LoginResponse(token,userDetails.getUserrole()));
		}
		else {
		return ResponseEntity.ok(new LoginResponse("INVALID_CREDENTIALS",null));
		}
	}
	
	@PostMapping("/batabps/login/validatetoken")
	public ResponseEntity<TokenResponse> firstPage(@RequestBody TokenRequest token) {
		
		Claims claims = Jwts.parser()
	            .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
	            .parseClaimsJws(token.getToken()).getBody();
		//claims.getSubject() means username
		final UserDetailsImpl userDetails = (UserDetailsImpl)loginService.loadUserByUsername(claims.getSubject());
		
		if(claims.getSubject().equals(userDetails.getUsername()) && Boolean.FALSE.equals(jwtTokenUtil.isTkExpired(token.getToken()))){
	return ResponseEntity.ok(new TokenResponse("True",claims.getSubject(),claims.get("Role").toString(),claims.get("FullName").toString()));
			}
		else {
			return ResponseEntity.ok(new TokenResponse("False",null,null,null));
					}		
	}

	//@Scheduled(cron = "0 36 15 19 */4 ?")
	// @Scheduled(cron = "0 0 0 8 4,7,10,1 ?")
	public void runReopen() {
		System.out.println("Schedular startfor qtr reopen email and sms>>>>>>>>>@##################");
	}
}