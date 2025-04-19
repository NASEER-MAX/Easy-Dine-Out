package com.restaurent.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurent.adapter.FoodCategoryAdapter;
import com.restaurent.dto.FoodCategoryDto;
import com.restaurent.dto.request.FoodCategoryRequest;
import com.restaurent.entity.FoodCategory;
import com.restaurent.repository.FoodCategoryRepository;
import com.restaurent.service.interfaces.FoodCategoryService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Service
public class FoodCategoryServiceImpl implements FoodCategoryService {

	FoodCategoryRepository foodCategoryRepository;
	FoodCategoryAdapter foodCategoryAdapter;

	@Override
	public List<FoodCategoryDto> getAllFoodCategories() {
		return foodCategoryAdapter.toDto(foodCategoryRepository.findAll());
	}

	@Override
	public FoodCategoryDto getFoodCategoryById(long id) {
		Optional<FoodCategory> foodCategory = foodCategoryRepository.findById(id);
		if (foodCategory.isEmpty()) {
			throw new EntityNotFoundException("FoodCategory not found with ID: " + id);
		}
		return foodCategoryAdapter.toDto(foodCategory.get());
	}

	@Override
	public FoodCategoryDto createFoodCategory(FoodCategoryRequest foodCategoryRequest) {
		FoodCategory foodCategory = new FoodCategory();
		foodCategory.setImage(foodCategoryRequest.getImage());
		foodCategory.setName(foodCategoryRequest.getName());

		foodCategoryRepository.save(foodCategory);
		return foodCategoryAdapter.toDto(foodCategory);
	}

	@Transactional
	@Override
	public FoodCategoryDto updateFoodCategory(long id, FoodCategoryRequest foodCategoryRequest) {

		FoodCategory foodCategory = foodCategoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
				"FoodCategory not found with ID: " + id));

		foodCategory.setImage(foodCategoryRequest.getImage());
		foodCategory.setName(foodCategoryRequest.getName());

		foodCategoryRepository.save(foodCategory);

		return foodCategoryAdapter.toDto(foodCategory);

	}

	@Transactional
	@Override
	public void deleteFoodCategory(long id) {
		if (!foodCategoryRepository.existsById(id)) {
			throw new EntityNotFoundException("FoodCategory not found with ID: " + id);
		}
		// cascades to dishes
		foodCategoryRepository.deleteById(id);

	}
}
