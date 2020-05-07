package com.cg.flight.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.flight.dto.BookingForm;
import com.cg.flight.dto.FlightMessage;
import com.cg.flight.exception.FlightBookingException;
import com.cg.flight.exception.LoginException;
import com.cg.flight.service.FlightBookingService;
import com.cg.flight.util.FlightConstants;


@RestController
public class FlightBookingtRestController {

	@Autowired
	private FlightBookingService service;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/addbooking")
	public FlightMessage addBooking(@RequestBody BookingForm bookingForm, HttpServletRequest req
			) throws FlightBookingException, LoginException {

		if((boolean)req.getAttribute("authFlag")) {
		String bookingID =service.addBooking(bookingForm);
		FlightMessage msg = new FlightMessage();
		msg.setMessage("Successfully Booked ! " +bookingID);
		return msg;}
		throw new LoginException(FlightConstants.NOT_AUTHENTICATED);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@DeleteMapping( value="/cancelbooking/{bookingid}")
	public FlightMessage cancelBooking(@PathVariable("bookingid") String bookingId, HttpServletRequest req
			) throws FlightBookingException, LoginException {

		String result=null;
		if((boolean)req.getAttribute("authFlag")) {
		 result =service.cancelBooking(bookingId);
		FlightMessage msg = new FlightMessage();
		msg.setMessage(result);
		return msg;}
		throw new LoginException(FlightConstants.NOT_AUTHENTICATED);
	}
	
}
