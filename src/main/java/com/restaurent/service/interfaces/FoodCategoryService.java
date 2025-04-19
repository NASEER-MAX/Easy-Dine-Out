package com.restaurent.service.interfaces;

import java.util.List;

import com.restaurent.dto.FoodCategoryDto;
import com.restaurent.dto.request.FoodCategoryRequest;

public interface FoodCategoryService {

	List<FoodCategoryDto> getAllFoodCategories();

	FoodCategoryDto getFoodCategoryById(long id);

	FoodCategoryDto createFoodCategory(FoodCategoryRequest foodCategory);

	FoodCategoryDto updateFoodCategory(long id, FoodCategoryRequest foodCategory);

	void deleteFoodCategory(long id);

}
