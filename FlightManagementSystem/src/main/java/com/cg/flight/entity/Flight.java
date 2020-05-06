package com.cg.flight.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/***
 * 
 * @author Nishant Shrivastav
 *
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="lpu_flight")
public class Flight {

	@Id
	@Column(name="flight_code", length=10)
	private String flightCode;
	@Column(name="airways_name", length=25)
	private String flightName;
	@Column(name="seat_capacity", length=25)
	private Integer seatCapacity;
	public String getFlightCode() {
		return flightCode;
	}
	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}
	public String getFlightName() {
		return flightName;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	public Integer getSeatCapacity() {
		return seatCapacity;
	}
	public void setSeatCapacity(Integer seatCapacity) {
		this.seatCapacity = seatCapacity;
	}

	

}
