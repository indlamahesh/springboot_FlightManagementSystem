package com.cg.flight.service;

import java.util.List;

import com.cg.flight.dto.ScheduleForm;
import com.cg.flight.entity.Airport;
import com.cg.flight.entity.Flight;
import com.cg.flight.entity.ScheduledFlight;
import com.cg.flight.exception.ScheduledFlightIdException;
import com.cg.flight.exception.SeatException;

public interface AddFlightScheduleService {

	
	public boolean addFlightSchedule(ScheduleForm scheduleForm)throws SeatException,ScheduledFlightIdException;
	public boolean editFlightSchedule(ScheduledFlight schedule);
	
	public List<Airport> getAirports();
	
	public List<Flight> getFlights();
}
