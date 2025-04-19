package com.restaurent.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.restaurent.validation.annotation.WithinTimeRange;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class WithinTimeRangeValidator implements ConstraintValidator<WithinTimeRange, Date> {

	private String startTime;
	private String endTime;

	@Override
	public void initialize(WithinTimeRange constraintAnnotation) {
		startTime = constraintAnnotation.start();
		endTime = constraintAnnotation.end();
	}

	@Override
	public boolean isValid(Date value, ConstraintValidatorContext context) {

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		try {
			Date start = sdf.parse(startTime);
			Date end = sdf.parse(endTime);
			Date timeToValidate = sdf.parse(sdf.format(value));

			boolean isWithinTimeRange = timeToValidate.after(start) && timeToValidate.before(end);
			boolean isHourlyTime = timeToValidate.getMinutes() == 0; // Check if minutes are zero
			return isWithinTimeRange && isHourlyTime;
		} catch (ParseException e) {
			return false;
		}
	}
}
