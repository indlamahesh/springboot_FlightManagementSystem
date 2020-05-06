package com.cg.flight.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="lpu_flight_passenger")
public class Passenger {

	@Id
	@Column(name="pid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="passenger_name", length=25)
	private String passengerName;
	
	@Column(name="passenger_age")
	private int age;
	
	@Column(name="gender", length=10)
	private String gender;
	
	@ManyToOne
	@JoinColumn(name="booking_id", referencedColumnName = "booking_id")
	private Booking booking = new Booking();
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
	
}
