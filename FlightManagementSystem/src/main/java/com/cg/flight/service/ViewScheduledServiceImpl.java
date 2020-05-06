package com.cg.flight.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cg.flight.dao.FlightDao;
import com.cg.flight.entity.Airport;
import com.cg.flight.entity.ScheduledFlight;
import com.cg.flight.exception.DateException;
import com.cg.flight.exception.ScheduledFlightIdException;
import com.cg.flight.exception.ScheduledFlightNotFoundException;
import com.cg.flight.util.FlightConstants;

@Repository
@Transactional
public class ViewScheduledServiceImpl implements ViewScheduledService {

	
	@Autowired
	private FlightDao dao;
	
	
	
	
	@Override
	@Transactional
	public List<ScheduledFlight> getFlightSchedule(String src, String dest, LocalDate doj) throws DateException,
	ScheduledFlightNotFoundException {
		if(doj.isBefore(LocalDate.now()))
			throw new DateException(FlightConstants.PAST_DATE);
		List<ScheduledFlight> schFlights = dao.getFlightSchedule(src, dest);
		schFlights = schFlights.stream().filter(sf->sf.getDepartureTime().toLocalDate().equals(doj)).collect(Collectors.toList());
		if(schFlights!=null)
        return schFlights;
		throw new ScheduledFlightNotFoundException(FlightConstants.SCH_NOT_FOUND);
	}




	@Override
	public List<Airport> getAirprots() {

		return dao.getAirport();
	}


	@Override
	public ScheduledFlight viewFlightSchedule(int scheduleId) throws ScheduledFlightIdException {
	   ScheduledFlight scheduleFlight = dao.viewFlightSchedule(scheduleId);
	   if(scheduleFlight!=null)
		return dao.viewFlightSchedule(scheduleId);
	   throw new ScheduledFlightIdException(FlightConstants.SCH_ID_NOT_FOUND);
	}

}
