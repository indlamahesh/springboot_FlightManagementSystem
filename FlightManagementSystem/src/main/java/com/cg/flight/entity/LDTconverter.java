package com.cg.flight.entity;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
@Converter(autoApply = true)
public class LDTconverter implements AttributeConverter<LocalDate, Date>{

	

	@Override
	public Date convertToDatabaseColumn(LocalDate ldt) {
		if(ldt != null)
			return Date.valueOf(ldt); //convert LocalDate into java.sql.Data
		return null;
	}

	@Override
	public LocalDate convertToEntityAttribute(Date sqldt) {
		if(sqldt !=null)
			return sqldt.toLocalDate();
		return null;
	}

}
