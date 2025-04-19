package com.restaurent.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TableTypeRequest {

	@NotBlank(message = "Name shoud be not blank")
	private String name;

	@Min(value = 1, message = "Chairs should be atleast 1")
	private int chairs;
}
