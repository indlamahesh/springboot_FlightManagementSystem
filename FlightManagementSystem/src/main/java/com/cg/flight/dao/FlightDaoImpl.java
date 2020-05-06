package com.cg.flight.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.flight.entity.Airport;
import com.cg.flight.entity.Booking;
import com.cg.flight.entity.Flight;
import com.cg.flight.entity.Passenger;
import com.cg.flight.entity.ScheduledFlight;
import com.cg.flight.entity.User;
@Repository
public class FlightDaoImpl implements FlightDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public boolean addUser(User user) {
		em.persist(user);
		return true;
	}

	@Override
	public boolean editUser(User user) {
		em.merge(user);
		return true;
	}

	@Override
	public User viewUser(String userId) {
		
		return em.find(User.class, userId);
	}

	@Override
	public boolean addFlightSchedule(ScheduledFlight schedule) {
		em.persist(schedule);
		return true;
	}

	@Override
	public boolean editFlightSchedule(ScheduledFlight schedule) {
		em.merge(schedule);
		return true;
	}

	@Override
	public List<ScheduledFlight> getFlightSchedule(String src, String dest) {
		String jpql ="from ScheduledFlight sf inner join fetch sf.sourceAirport s inner join fetch sf.destinationAirport d inner join fetch sf.flight f "
				+ "where s.airportCode=:srcairport and d.airportCode=:destairport ";
		TypedQuery<ScheduledFlight> query = em.createQuery(jpql, ScheduledFlight.class);
		query.setParameter("srcairport", src);
		query.setParameter("destairport", dest);
		
		return query.getResultList();
	}

	@Override
	public boolean addBooking(Booking booking) {
		em.persist(booking);
		return true;
	}

	@Override
	public Booking viewBooking(String bookId) {
		String jpql ="from Booking b inner join fetch b.schedule sf inner join fetch sf.sourceAirport s "
				+ "inner join fetch sf.destinationAirport s inner join fetch sf.flight f where b.bookingId=:bid";
				
		TypedQuery<Booking> query = em.createQuery(jpql, Booking.class);
		query.setParameter("bid", bookId);
		return query.getSingleResult();
	}

	@Override
	public List<Booking> viewBookingBySchedule(String scheduleId) {
		String jpql ="from Booking b inner join fetch b.schedule sf inner join fetch sf.sourceAirport s "
				+ "inner join fetch sf.destinationAirport s inner join fetch sf.flight f where sf.scheduleId=:schid";
				
		TypedQuery<Booking> query = em.createQuery(jpql, Booking.class);
		query.setParameter("schid", scheduleId);
		return query.getResultList();
	}

	@Override
	public boolean addPassenger(Passenger passenger) {
		em.persist(passenger);
		return true;
	} 

	@Override
	public List<Passenger> viewPassengers(String bookId) {
		String jpql ="from Passenger p inner join fetch p.booking b inner join fetch b.schedule sf inner join fetch sf.sourceAirport s "
				+ "inner join fetch sf.destinationAirport d inner join fetch sf.flight f where b.bookingId=:bid";
				
		TypedQuery<Passenger> query = em.createQuery(jpql, Passenger.class);
		query.setParameter("bid", bookId);
		return query.getResultList();
	}


	@Override
	public boolean deleteBooking(Booking booking) {
		em.remove(booking);
		return true;
	}

	@Override
	public boolean deletePassenger(String bookingId) {
		String jpql ="delete from Passenger p where p.booking.bookingId=:bid";
		
		Query query = em.createQuery(jpql);
		//TypedQuery<Integer> query = em.createQuery(jpql, Integer.class);
		query.setParameter("bid", bookingId);
		int res =query.executeUpdate();
		if(res > 0)
		return true;
		else
			return false;
	}

	@Override
	public List<Booking> viewBookingByContact(String contact) {
		String jpql ="from Booking b inner join fetch b.schedule sf inner join fetch sf.sourceAirport s "
				+ "inner join fetch sf.destinationAirport d inner join fetch sf.flight f  inner join fetch b.user u "
				+ "where u.contactNo=:contact";
				
		TypedQuery<Booking> query = em.createQuery(jpql, Booking.class);
		query.setParameter("contact", contact);
		return query.getResultList();
	}

	@Override
	public ScheduledFlight viewFlightSchedule(int scheduleId) {
		
		return em.find(ScheduledFlight.class, scheduleId);
	}

	@Override
	public long countBookingForSchedule(int scheduleId) {
		String jpql ="select count(b.bookingId) from Booking b  join  b.schedule s where s.scheduledFlightId =:schid";
		TypedQuery<Long> query = em.createQuery(jpql, Long.class);
		query.setParameter("schid", scheduleId);
		return query.getSingleResult();
		
	}

	@Override
	public int countMaxSchedule() {
		String jpql ="select max(scheduledFlightId) from ScheduledFlight";
		TypedQuery<Integer> query = em.createQuery(jpql, Integer.class);
		
		return query.getSingleResult();
	}

	@Override
	public Airport getAirport(String airportCode) {
		
		return em.find(Airport.class, airportCode);
	}

	@Override
	public Flight viewFlight(String flightCode) {
		
		return em.find(Flight.class, flightCode);
	}

	@Override
	public List<Airport> getAirport() {
		String jpql ="from Airport";
		TypedQuery<Airport> query = em.createQuery(jpql, Airport.class);
		
		return query.getResultList();
	}
	@Override
	public List<Flight> getFlight() {
		String jpql ="from Flight";
		TypedQuery<Flight> query = em.createQuery(jpql, Flight.class);
		
		return query.getResultList();
	}


}
