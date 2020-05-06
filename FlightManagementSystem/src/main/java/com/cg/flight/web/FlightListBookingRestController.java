package com.cg.flight.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cg.flight.entity.Booking;
import com.cg.flight.entity.Passenger;
import com.cg.flight.exception.InvalidBookingIdException;
import com.cg.flight.exception.InvalidContactException;
import com.cg.flight.exception.LoginException;
import com.cg.flight.service.FlightListBookingService;

@RestController
public class FlightListBookingRestController {
	
	@Autowired
	private FlightListBookingService service;
	
	@CrossOrigin(origins = {"http://localhost:4200"})
	@GetMapping("/viewbooking/{contact}")
	public  List<Booking> viewBooking(@PathVariable("contact")String contactNo,HttpServletRequest req) throws InvalidContactException, LoginException{
		 if((boolean)req.getAttribute("authFlag"))
		return service.viewBooking(contactNo);
		 throw new LoginException();
		 
	}
	@CrossOrigin(origins = {"http://localhost:4200"})
	@GetMapping("/viewpassenger/{bookId}")
	public List<Passenger> viewPassengers(@PathVariable("bookId")String bookId,HttpServletRequest req) throws InvalidBookingIdException,
	LoginException{
		if((boolean)req.getAttribute("authFlag"))
		return service.viewPassengers(bookId);
		throw new LoginException();
	}

}
