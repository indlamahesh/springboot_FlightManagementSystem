package com.cg.flight.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.flight.dao.FlightDao;
import com.cg.flight.entity.Booking;
import com.cg.flight.entity.Passenger;
import com.cg.flight.exception.InvalidBookingIdException;
import com.cg.flight.exception.InvalidContactException;
import com.cg.flight.util.FlightConstants;
@Service
@Transactional
public class FlightListBookingServiceImpl implements FlightListBookingService{

	@Autowired
	private FlightDao dao;
	
	
	@Override
	public List<Passenger> viewPassengers(String bookId) throws InvalidBookingIdException {
		List<Passenger> plist = dao.viewPassengers(bookId);
		if(plist.isEmpty())
			throw new InvalidBookingIdException(FlightConstants.INVALID_BOOKINGID);
		
		plist.sort((p1,p2)->Integer.valueOf(p2.getAge()).compareTo(p1.getAge()));
		return plist;
	}

	@Override
	public List<Booking> viewBooking(String contactNo) throws InvalidContactException {
		List<Booking> bookList = dao.viewBookingByContact(contactNo);
		if(bookList.isEmpty())
			throw new InvalidContactException(FlightConstants.INVALID_CONTACT);
		bookList.sort((b1, b2)->b2.getBookingDate().compareTo(b1.getBookingDate()));
		return bookList;
	}


}
