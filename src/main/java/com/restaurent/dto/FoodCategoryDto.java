package com.restaurent.dto;

import com.restaurent.dto.request.FoodCategoryRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FoodCategoryDto extends FoodCategoryRequest {

	private Long id;
}
