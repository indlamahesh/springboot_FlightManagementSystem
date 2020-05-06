package com.cg.flight.dto;

import java.util.ArrayList;
import java.util.List;

import com.cg.flight.entity.Passenger;

public class BookingForm {

	private int tkts;
	private int scheduleFlightId;
	private String contactNo;
	private List<Passenger> passengers = new ArrayList<>();
	
	public int getTkts() {
		return tkts;
	}
	public void setTkts(int tkts) {
		this.tkts = tkts;
	}
	public int getScheduleFlightId() {
		return scheduleFlightId;
	}
	public void setScheduleFlightId(int scheduleFlightId) {
		this.scheduleFlightId = scheduleFlightId;
	}
	
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public List<Passenger> getPassengers() {
		return passengers;
	}
	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}
	
	
}
