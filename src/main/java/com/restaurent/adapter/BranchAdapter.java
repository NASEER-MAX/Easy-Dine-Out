package com.restaurent.adapter;

import org.springframework.stereotype.Service;

import com.restaurent.adapter.interfaces.AbstractAdapter;
import com.restaurent.dto.BranchDto;
import com.restaurent.entity.Branch;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BranchAdapter extends AbstractAdapter<Branch, BranchDto> {

	CityAdapter cityAdapter;
	RestaurantAdapter restaurantAdapter;

	@Override
	public Branch toEntity(BranchDto dto) {
		return Branch.builder().build();
	}

	@Override
	public BranchDto toDto(Branch entity) {
		return BranchDto.builder()
				.id(entity.getId())
				.city(cityAdapter.toDto(entity.getCity()))
				.restaurant(restaurantAdapter.toDto(entity.getRestaurant()))
				.build();

	}

}
