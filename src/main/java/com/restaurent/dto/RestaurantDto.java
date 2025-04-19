package com.restaurent.dto;

import com.restaurent.dto.request.RestaurantRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RestaurantDto extends RestaurantRequest {

	private Long id;

}
