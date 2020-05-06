package com.cg.flight.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.flight.entity.User;
import com.cg.flight.exception.LoginException;
import com.cg.flight.exception.UserIdException;
import com.cg.flight.service.UserService;
import com.cg.flight.util.FlightConstants;

@RestController
public class UserController {

	@Autowired
	private UserService service;
	
	
	@CrossOrigin(origins = {"http://localhost:4200"})
	@PostMapping("/adduser")
	public String addUser(@RequestBody User user) throws UserIdException{
		try {
			service.addUser(user);
			return FlightConstants.ADDED;
		}catch(DataIntegrityViolationException ex) {
			throw new UserIdException(FlightConstants.USER_ID_ALREADY_EXISTS);
		}		
	}
	
	@CrossOrigin(origins = {"http://localhost:4200"})
	@GetMapping("/viewuser/{userId}")
	public User viewUser(HttpServletRequest req,@PathVariable("userId")String userId) throws LoginException {
		
		User user = service.viewUser(userId);
		if((boolean)req.getAttribute("authFlag")) 
			 return user;
		 throw new LoginException();

	}
	@CrossOrigin(origins = {"http://localhost:4200"})
	@PutMapping("/edituser")
	public String edituser(HttpServletRequest req, @RequestBody User user) throws LoginException { 
		service.editUser(user);
		return FlightConstants.EDITED ;
	
	}
	
}
