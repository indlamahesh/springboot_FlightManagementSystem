package com.cg.flight.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.flight.entity.User;
import com.cg.flight.exception.LoginException;
import com.cg.flight.service.LoginService;
import com.cg.flight.util.FlightConstants;

@RestController
public class LoginRestController {

	
	@Autowired
	private LoginService ser;
	
	@Autowired
	@Qualifier("authenticatemap")
	private Map<String, User> authMap;
	
	@CrossOrigin(origins = {"http://localhost:4200"})
	@RequestMapping(value="/login", method =RequestMethod.POST)
	public String getLogin(@RequestParam("userId")String userId, @RequestParam("password")String password) throws LoginException{
	
		try {	
		   User user = ser.doLogin(userId, password);
			String token = ser.encryptUser(user);
			authMap.put(token, user);
		     return token;
			}catch(Exception ex) { 
			throw new LoginException(FlightConstants.USER_NOT_AVAILABLE);}
	}
	
	
	
	@CrossOrigin(origins = {"http://localhost:4200"})
	@GetMapping("/logout")
	public String logout(@RequestHeader("userId")String token, HttpServletRequest req) {
		authMap.remove(token);
		return FlightConstants.LOGGED_OUT;

	}
	
	
}
