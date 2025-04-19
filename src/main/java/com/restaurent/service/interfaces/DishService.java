package com.restaurent.service.interfaces;

import java.util.List;

import com.restaurent.dto.DishDto;
import com.restaurent.dto.request.DishRequest;

public interface DishService {

	List<DishDto> getAllDishes();

	DishDto getDishById(long id);

	DishDto createDish(DishRequest dish);

	DishDto updateDish(long id, DishRequest dish);

	void deleteDish(long id);

}
