package com.cg.flight.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cg.flight.dao.FlightDao;
import com.cg.flight.dto.ScheduleForm;
import com.cg.flight.entity.Airport;
import com.cg.flight.entity.Flight;
import com.cg.flight.entity.ScheduledFlight;
import com.cg.flight.exception.ScheduledFlightIdException;
import com.cg.flight.exception.SeatException;
import com.cg.flight.util.FlightConstants;
@Repository
@Transactional
public class AddFlightScheduleServiceImpl implements AddFlightScheduleService {

	
	@Autowired
	private FlightDao dao;

	@Override
	public boolean addFlightSchedule(ScheduleForm scheduleForm) throws SeatException,ScheduledFlightIdException  {
		
			Airport sourceAirport = dao.getAirport(scheduleForm.getSrcAirportCode());
			Airport destinationAirport = dao.getAirport(scheduleForm.getDestAirportCode());
			Flight flight = dao.viewFlight(scheduleForm.getFlightCode());
			ScheduledFlight schedule = new ScheduledFlight();
			int schFlightId = dao.countMaxSchedule() + 1;
			schedule.setScheduledFlightId(schFlightId);
			if(scheduleForm.getSeats()>flight.getSeatCapacity())
				throw new SeatException(FlightConstants.INVALID_SEATS); 
			schedule.setAvailableSeats(scheduleForm.getSeats());
			schedule.setDepartureTime(scheduleForm.getDepartureTime());
			schedule.setMinutes(scheduleForm.getMinutes());
			schedule.setArrivalTime(scheduleForm.getDepartureTime().plusMinutes(scheduleForm.getMinutes()));
			schedule.setFare(scheduleForm.getFare());
			schedule.setScheduleStatus(FlightConstants.ESTIMATED);
			schedule.setFlight(flight);
			schedule.setSourceAirport(sourceAirport);
			schedule.setDestinationAirport(destinationAirport);
			dao.addFlightSchedule(schedule);
			
			return true;
	}

	
		@Override
	public boolean editFlightSchedule(ScheduledFlight schedule) {
		dao.editFlightSchedule(schedule);
		return true;
	}

	@Override
	public List<Airport> getAirports() {
		
		return dao.getAirport();
	}

	@Override
	public List<Flight> getFlights() {
		
		return dao.getFlight();
	}

	
	
	
	
	
	
}
