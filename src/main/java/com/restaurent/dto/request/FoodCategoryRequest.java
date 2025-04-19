package com.restaurent.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FoodCategoryRequest {

	@NotBlank(message = "Name shoud be not blank")
	private String name;
	private String image;
}
