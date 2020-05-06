package com.cg.flight.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.cg.flight.entity.User;

@Component 
public class AdminInterceptor implements HandlerInterceptor{

	@Autowired
	@Qualifier("authenticatemap")
	private Map<String, User> authMap;
	
	private Logger logger = LoggerFactory.getLogger(AdminInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse resp, Object handler)
			throws Exception {
		//HandlerInterceptor.super.preHandle(request, resp, handler);
		String token = request.getHeader("userId");
		User user = (User)authMap.get(token);
		logger.info("received tokenid " + request.getHeader("userId"));
		if(user != null && user.getRole().equals("admin"))
		request.setAttribute("authFlag", true);
		else
			request.setAttribute("authFlag", false);
		 
		return true;
				 
	}

	

	
}
