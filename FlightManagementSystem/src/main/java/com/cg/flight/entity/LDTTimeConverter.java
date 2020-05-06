package com.cg.flight.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LDTTimeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime ldt) {
		if (ldt != null)
			return Timestamp.valueOf(ldt);//convert LocalDate into java.sql.Date
		return null;
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp sqltime) {
		if (sqltime != null)
			return sqltime.toLocalDateTime();
		return null;
	}


}
