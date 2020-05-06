package com.cg.flight.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class ScheduleForm {

	private int seats;
	@DateTimeFormat(pattern="yyyy-M-d H:m")
	private LocalDateTime departureTime;
	private int minutes;
	private double fare;
	private String flightCode;
	private String srcAirportCode;
	private String destAirportCode;
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public LocalDateTime getDepartureTime() {
		return departureTime;
	}
	public void setDepartureTime(LocalDateTime departureTime) {
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//		this.departureTime = LocalDateTime.parse(dept, formatter);
//		
		this.departureTime=departureTime;

	}
	public int getMinutes() {
		return minutes;
	}
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	public String getFlightCode() {
		return flightCode;
	}
	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}
	public String getSrcAirportCode() {
		return srcAirportCode;
	}
	public void setSrcAirportCode(String srcAirportCode) {
		this.srcAirportCode = srcAirportCode;
	}
	public String getDestAirportCode() {
		return destAirportCode;
	}
	public void setDestAirportCode(String destAirportCode) {
		this.destAirportCode = destAirportCode;
	}
	
	
	
}
