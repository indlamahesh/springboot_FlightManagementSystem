package com.cg.flight.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cg.flight.exception.ImageException;
import com.cg.flight.exception.LoginException;
import com.cg.flight.exception.ScheduledFlightNotFoundException;
import com.cg.flight.exception.UserIdException;
import com.cg.flight.util.FlightConstants;

@ControllerAdvice
public class FlightExceptionAdvice {
	
	Logger logger = LoggerFactory.getLogger(FlightExceptionAdvice.class);
	
	@ExceptionHandler(value = {ScheduledFlightNotFoundException.class})
	@ResponseStatus(code= HttpStatus.NOT_FOUND,reason=FlightConstants.SCH_NOT_FOUND)
	@ResponseBody
	public void handlerException1(Exception ex) {
		logger.error(ex.getMessage());
	}
	
	@ExceptionHandler(value = {UserIdException.class})
	@ResponseStatus(code= HttpStatus.BAD_REQUEST,reason=FlightConstants.USER_ID_ALREADY_EXISTS)
	@ResponseBody
	public void handlerException2(Exception ex) {
		logger.error(ex.getMessage());
	}
	
	@ExceptionHandler(value = {LoginException.class})
	@ResponseStatus(code= HttpStatus.NOT_FOUND,reason=FlightConstants.USER_NOT_AVAILABLE)
	@ResponseBody
	public void handlerException3(Exception ex) {
		logger.error(ex.getMessage());
	}
	@ExceptionHandler(value = {ImageException.class})
	@ResponseStatus(code= HttpStatus.BAD_REQUEST,reason="image should not larger than 1mb")
	@ResponseBody
	public void handlerException4(Exception ex) {
		logger.error(ex.getMessage());
	}
	
	@ExceptionHandler(value = {HttpMessageNotReadableException.class})
	@ResponseBody
	public ErrorInfo HandleException2(Exception ex) {
		logger.error(ex.getMessage());
		if(ex.getMessage().contains("doj"))
			return new ErrorInfo("Date must have pattern yyyy-M-d");
		return new ErrorInfo(ex.getMessage());
	}

}