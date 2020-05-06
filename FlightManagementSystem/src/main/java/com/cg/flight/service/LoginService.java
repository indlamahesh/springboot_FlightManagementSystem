package com.cg.flight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.flight.dao.FlightDao;
import com.cg.flight.entity.User;
import com.cg.flight.exception.LoginException;

@Service
public class LoginService {

	
	@Autowired
	private FlightDao dao;
	
	
	public User doLogin(String userId,String password) throws LoginException {
		User user = dao.viewUser(userId);
		System.out.println("user="+user.getContactNo());
		if(user!=null && user.getPassword().contentEquals(password)) {
			return user;
		}
		throw new LoginException("You are not authenticated and authorised , Please Login");
	}
	
	public String encryptUser(User user) {
		return encryptString(user.getContactNo())+"-"+encryptString(user.getUserName())+"-"+encryptString(user.getRole());
	}

	private String encryptString(String str) {
		char[] arr = str.toCharArray();
		StringBuffer sb = new StringBuffer();
		int ch;
		for(int idx =0; idx < arr.length; ++idx) {
			ch = arr[idx]+3;
			sb.append((char)ch);
		}
		return sb.toString();
	}
	
	
	
	
	
	
	
}
