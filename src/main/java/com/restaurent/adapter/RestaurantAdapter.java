package com.restaurent.adapter;

import org.springframework.stereotype.Service;

import com.restaurent.adapter.interfaces.AbstractAdapter;
import com.restaurent.dto.RestaurantDto;
import com.restaurent.entity.Restaurant;

@Service
public class RestaurantAdapter extends AbstractAdapter<Restaurant, RestaurantDto> {

	@Override
	public Restaurant toEntity(RestaurantDto dto) {
		return Restaurant.builder().name(dto.getName()).image(dto.getImage()).build();
	}

	@Override
	public RestaurantDto toDto(Restaurant entity) {
		return RestaurantDto.builder().id(entity.getId()).name(entity.getName()).image(entity.getImage()).build();
	}

}
