package com.cg.flight.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "lpu_flight_booking")
public class Booking {
	@Id
	@Column(name = "booking_id", length = 10)
	private String bookingId;
	
	@Column(name = "no_of_seats")
	private int noOfSeats;
	
	@Column(name = "booking_date")
	private LocalDate bookingDate;
	
	@Column(name = "fare")
	private double fare;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "contact_no")
	private User user = new User();
	
	@ManyToOne
	@JoinColumn(name = "schedule_id", referencedColumnName = "schedule_id")
	private ScheduledFlight schedule = new ScheduledFlight();

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public int getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ScheduledFlight getSchedule() {
		return schedule;
	}

	public void setSchedule(ScheduledFlight schedule) {
		this.schedule = schedule;
	}

}
