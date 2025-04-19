package com.restaurent.adapter;

import org.springframework.stereotype.Service;

import com.restaurent.adapter.interfaces.AbstractAdapter;
import com.restaurent.dto.FoodCategoryDto;
import com.restaurent.entity.FoodCategory;

@Service
public class FoodCategoryAdapter extends AbstractAdapter<FoodCategory, FoodCategoryDto> {

	@Override
	public FoodCategory toEntity(FoodCategoryDto dto) {
		return FoodCategory.builder().name(dto.getName()).image(dto.getImage()).build();
	}

	@Override
	public FoodCategoryDto toDto(FoodCategory entity) {
		return FoodCategoryDto.builder().id(entity.getId()).name(entity.getName()).image(entity.getImage()).build();
	}

}
