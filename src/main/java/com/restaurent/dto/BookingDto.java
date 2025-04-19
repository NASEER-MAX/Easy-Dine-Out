package com.restaurent.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.restaurent.dto.request.BookingRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingDto extends BookingRequest {

	private Long id;
	private Long branchTableId;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm", timezone = "Asia/Kolkata")
	private Date endDateTime;
}
