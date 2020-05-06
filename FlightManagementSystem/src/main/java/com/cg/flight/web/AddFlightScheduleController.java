package com.cg.flight.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.flight.dto.FlightMessage;
import com.cg.flight.dto.ScheduleForm;
import com.cg.flight.entity.Flight;
import com.cg.flight.entity.ScheduledFlight;
import com.cg.flight.exception.LoginException;
import com.cg.flight.exception.ScheduledFlightIdException;
import com.cg.flight.exception.SeatException;
import com.cg.flight.service.AddFlightScheduleService;
import com.cg.flight.util.FlightConstants;

@RestController
public class AddFlightScheduleController {
	
	@Autowired
	private AddFlightScheduleService service;
	
	
	@CrossOrigin(origins = {"http://localhost:4200"}) 
	@PutMapping("/editschedule")
	public FlightMessage editScheduledFlight(HttpServletRequest req,  @RequestBody ScheduledFlight schedule) throws LoginException {
		if((boolean)req.getAttribute("authFlag")) {
		service.editFlightSchedule(schedule);
		FlightMessage msg = new FlightMessage();
		msg.setMessage(FlightConstants.EDITED);
		return msg;}
		throw new LoginException();
	}

	@CrossOrigin(origins = {"http://localhost:4200"}) 
	@PostMapping("/addschedule")
	public FlightMessage addFlightSchedule(@RequestBody ScheduleForm schedule,HttpServletRequest req) throws ScheduledFlightIdException,
	SeatException, LoginException  {
		try {
			if((boolean)req.getAttribute("authFlag")) { 
			service.addFlightSchedule(schedule);
			FlightMessage msg = new FlightMessage();
			msg.setMessage(FlightConstants.ADDED);
			System.out.println("msg"+msg);
			return msg;}
			throw new LoginException();
		} catch (DataIntegrityViolationException ex) {
			throw new ScheduledFlightIdException(FlightConstants.SCH_ID_ALREADY_EXISTS);
		}
	
	}
	
		
	@CrossOrigin(origins = {"http://localhost:4200"}) 
	@GetMapping("/viewflight")
	public List<Flight> flights(){
		return service.getFlights();
	}
	
}

