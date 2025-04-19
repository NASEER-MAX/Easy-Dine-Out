package com.restaurent.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BranchDto {

	private Long id;
	private CityDto city;
	private RestaurantDto restaurant;
}
