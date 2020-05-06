package com.cg.flight.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cg.flight.dao.FlightDao;
import com.cg.flight.dto.BookingForm;
import com.cg.flight.entity.Booking;
import com.cg.flight.entity.Passenger;
import com.cg.flight.entity.ScheduledFlight;
import com.cg.flight.entity.User;
import com.cg.flight.exception.FlightBookingException;
import com.cg.flight.util.FlightConstants;


@Service
@Transactional
public class FlightBookingServiceImpl implements FlightBookingService {

	@Autowired
	private FlightDao dao;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public String addBooking(BookingForm bookingForm) throws FlightBookingException {
		System.out.println(bookingForm.getScheduleFlightId());
       ScheduledFlight schflight =  dao.viewFlightSchedule(bookingForm.getScheduleFlightId());
       System.out.println("service " + schflight);
       System.out.println("bookingForm " + bookingForm);
       User user = dao.viewUser(bookingForm.getContactNo());
       Booking booking = null;
       String bookingId="";
       if(schflight.getAvailableSeats() >= bookingForm.getTkts()) {
    	   booking = new Booking();
    	   booking.setBookingDate(LocalDate.now());
    	   bookingId = generateBookingId(bookingForm.getScheduleFlightId());
    	   booking.setBookingId(bookingId);
    	   booking.setFare(schflight.getDynamicPrice()* bookingForm.getTkts());
    	   booking.setNoOfSeats(bookingForm.getTkts());
    	   booking.setSchedule(schflight);
    	   booking.setUser(user);
    	   dao.addBooking(booking);
    	   schflight.setAvailableSeats(schflight.getAvailableSeats() - bookingForm.getTkts());
    	   dao.editFlightSchedule(schflight);
    	   addPassenger(bookingForm.getPassengers(),booking);
    	   return bookingId;
       }
		throw new FlightBookingException(FlightConstants.BOOKING_NOT_AVAILABLE);
		
	}

	
	public boolean addPassenger(List<Passenger> passengers, Booking booking) {
      passengers.forEach(passenger->{passenger.setBooking(booking);dao.addPassenger(passenger);});
		
		return true;
	}

	public String generateBookingId(int schFlightId) {
		long count = dao.countBookingForSchedule(schFlightId)+1;
		String bookingId = dao.viewFlightSchedule(schFlightId).getFlight().getFlightCode() + schFlightId + count;
		System.out.println("Booking ID " + bookingId);
		return bookingId;
	}


	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public String cancelBooking(String bookingID) throws FlightBookingException {
		Booking booking = dao.viewBooking(bookingID);
		ScheduledFlight schedule = booking.getSchedule();
		LocalDate journeyDate =schedule.getDepartureTime().toLocalDate();
		if (journeyDate.isBefore(LocalDate.now()) || journeyDate.isEqual((LocalDate.now()))) 
				throw new FlightBookingException(FlightConstants.NOT_CANCELLED);
		schedule.setAvailableSeats(schedule.getAvailableSeats() + booking.getNoOfSeats());
		dao.editFlightSchedule(schedule);
		dao.deletePassenger(bookingID);
		dao.deleteBooking(booking);
		
		return FlightConstants.CANCELLED;
	}


	
	
}
