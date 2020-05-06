package com.cg.flight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.flight.dao.FlightDao;
import com.cg.flight.entity.User;

@Repository
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private FlightDao dao;
	
	
	@Override
	@Transactional
	public boolean addUser(User user) {
		
		return dao.addUser(user);
	}

	@Override
	public User viewUser(String userId) {
		
		return dao.viewUser(userId);
		
	}
	@Override
	public boolean editUser(User user) {
		
		return dao.editUser(user);
	}

}
