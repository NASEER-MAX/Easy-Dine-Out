package com.restaurent.dto.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.restaurent.validation.annotation.WithinTimeRange;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingRequest {

	@NotNull(message = "Start date time must be provided")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm", timezone = "Asia/Kolkata")
	@Future
	@WithinTimeRange(start = "08:00", end = "22:00")
	private Date startDateTime;



//	🆚 @Future vs @FutureOrPresent
//
//	Annotation			Allows Today?	Allows Future?
//	@Future				❌ No			✅ Yes
//	@FutureOrPresent	✅ Yes			✅ Yes
}
