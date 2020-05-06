package com.cg.flight.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.flight.entity.Airport;
import com.cg.flight.entity.ScheduledFlight;
import com.cg.flight.exception.DateException;
import com.cg.flight.exception.ScheduledFlightIdException;
import com.cg.flight.exception.ScheduledFlightNotFoundException;

public interface ViewScheduledService {

	
	public List<ScheduledFlight> getFlightSchedule(String src, String dest, LocalDate doj)throws DateException,
	ScheduledFlightNotFoundException;
	
	public List<Airport> getAirprots();
	
	public ScheduledFlight viewFlightSchedule(int scheduleId)throws ScheduledFlightIdException;
	
}
