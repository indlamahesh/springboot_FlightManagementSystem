package com.cg.flight.service;

import java.util.List;

import com.cg.flight.entity.Booking;
import com.cg.flight.entity.Passenger;
import com.cg.flight.exception.InvalidBookingIdException;
import com.cg.flight.exception.InvalidContactException;

public interface FlightListBookingService {
	
	
	public List<Passenger> viewPassengers(String bookId) throws InvalidBookingIdException;
	public List<Booking> viewBooking(String  contactNo) throws InvalidContactException;


}
