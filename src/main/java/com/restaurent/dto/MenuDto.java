package com.restaurent.dto;

import com.restaurent.dto.request.MenuRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MenuDto extends MenuRequest {

	private Long id;
	private DishDto dish;
	private FoodCategoryDto foodCategory;
}
