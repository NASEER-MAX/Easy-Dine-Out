package com.restaurent.adapter;

import org.springframework.stereotype.Service;

import com.restaurent.adapter.interfaces.AbstractAdapter;
import com.restaurent.dto.DishDto;
import com.restaurent.entity.Dish;

@Service
public class DishAdapter extends AbstractAdapter<Dish, DishDto> {

	@Override
	public Dish toEntity(DishDto dto) {
		return Dish.builder().name(dto.getName()).build();
	}

	@Override
	public DishDto toDto(Dish entity) {
		return DishDto.builder().id(entity.getId()).name(entity.getName()).build();
	}

}
