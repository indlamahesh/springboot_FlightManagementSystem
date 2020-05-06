package com.cg.flight.dao;

import java.util.List;

import com.cg.flight.entity.Airport;
import com.cg.flight.entity.Booking;
import com.cg.flight.entity.Flight;
import com.cg.flight.entity.Passenger;
import com.cg.flight.entity.ScheduledFlight;
import com.cg.flight.entity.User;

public interface FlightDao {

	public boolean addUser(User user);
	public boolean editUser(User user);
	public User viewUser(String userId);
	
	public Flight viewFlight(String flightCode);
	public Airport getAirport(String airportCode);
	public List<Airport> getAirport();
	public boolean addFlightSchedule(ScheduledFlight schedule);
	public boolean editFlightSchedule(ScheduledFlight schedule);
	public ScheduledFlight viewFlightSchedule(int scheduleId);
	public List<ScheduledFlight> getFlightSchedule(String src, String dest);
	
	public boolean addBooking(Booking booking);
	public Booking viewBooking(String bookId);
	public List<Booking> viewBookingBySchedule(String scheduleId);
	public List<Booking> viewBookingByContact(String contact);
	
	public boolean addPassenger(Passenger passenger);
	public List<Passenger> viewPassengers(String bookId);
	public boolean deleteBooking(Booking booking);
	public boolean deletePassenger(String bookingId);
	
	public long countBookingForSchedule(int scheduleId);
	public int countMaxSchedule();
	
	public List<Flight> getFlight();
}
