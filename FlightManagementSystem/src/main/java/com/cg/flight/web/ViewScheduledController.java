package com.cg.flight.web;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cg.flight.entity.Airport;
import com.cg.flight.entity.ScheduledFlight;
import com.cg.flight.exception.DateException;
import com.cg.flight.exception.LoginException;
import com.cg.flight.exception.ScheduledFlightIdException;
import com.cg.flight.exception.ScheduledFlightNotFoundException;
import com.cg.flight.service.ViewScheduledService;

@RestController
public class ViewScheduledController {

	@Autowired
	private ViewScheduledService service;
	
	
	@CrossOrigin(origins = {"http://localhost:4200"}) 
	@GetMapping("/viewschedule/{src}/{dest}/{doj}")
	public List<ScheduledFlight> getScheduleFlight(HttpServletRequest req,   @PathVariable("src")String src,
			@PathVariable("dest")String dest,@PathVariable("doj") @DateTimeFormat(pattern="yyyy-M-d") LocalDate doj) throws LoginException,
	ScheduledFlightNotFoundException, DateException{
		
		List<ScheduledFlight> schlist = service.getFlightSchedule(src, dest, doj);
	
		    if((boolean)req.getAttribute("authFlag")) 
		    return schlist;
		    throw new LoginException();
	}
	
	
	@CrossOrigin(origins = {"http://localhost:4200"}) 
	@GetMapping("/viewairport")
	public List<Airport> getAirports(){
		return service.getAirprots();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getschedule/{scheduleid}")
	public ScheduledFlight viewFlightScheduleBy(HttpServletRequest req, @PathVariable("scheduleid") int scheduleId) throws 
	ScheduledFlightIdException {

		ScheduledFlight schedule = service.viewFlightSchedule(scheduleId);
				 return schedule;
	
	}

}

