package com.restaurent.dto;

import com.restaurent.dto.request.DishRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DishDto extends DishRequest {

	private Long id;
}
