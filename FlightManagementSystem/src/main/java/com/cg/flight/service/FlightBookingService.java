package com.cg.flight.service;

import com.cg.flight.dto.BookingForm;
import com.cg.flight.exception.FlightBookingException;

public interface FlightBookingService {
	public String addBooking(BookingForm bookingForm)throws FlightBookingException;
	public String cancelBooking(String bookingID)throws FlightBookingException;
	
}
