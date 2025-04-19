package com.restaurent.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurent.adapter.DishAdapter;
import com.restaurent.dto.DishDto;
import com.restaurent.dto.request.DishRequest;
import com.restaurent.entity.Dish;
import com.restaurent.entity.FoodCategory;
import com.restaurent.repository.DishRepository;
import com.restaurent.repository.FoodCategoryRepository;
import com.restaurent.service.interfaces.DishService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Service
public class DishServiceImpl implements DishService {

	DishRepository dishRepository;
	FoodCategoryRepository foodCategoryRepository;
	DishAdapter dishAdapter;

	@Override
	public List<DishDto> getAllDishes() {
		return dishAdapter.toDto(dishRepository.findAll());
	}

	@Override
	public DishDto getDishById(long id) {
		Optional<Dish> dish = dishRepository.findById(id);
		if (dish.isEmpty()) {
			throw new EntityNotFoundException("Dish not found with ID: " + id);
		}
		return dishAdapter.toDto(dish.get());
	}

	@Override
	public DishDto createDish(DishRequest dishRequest) {

		FoodCategory foodCategory = foodCategoryRepository.findById(dishRequest.getCategoryId()).orElseThrow(
				() -> new EntityNotFoundException(
						"FoodCategory not found with ID: " + dishRequest.getCategoryId()));

		Dish dish = new Dish();
		dish.setCategory(foodCategory);
		dish.setName(dishRequest.getName());

		dishRepository.save(dish);

		return dishAdapter.toDto(dish);
	}

	@Transactional
	@Override
	public DishDto updateDish(long id, DishRequest dishRequest) {

		Dish dish = dishRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(
						"Dish not found with ID: " + id));

		FoodCategory foodCategory = foodCategoryRepository.findById(dishRequest.getCategoryId())
				.orElseThrow(() -> new EntityNotFoundException(
						"FoodCategory not found with ID: " + dishRequest.getCategoryId()));

		dish.setCategory(foodCategory);
		dish.setName(dishRequest.getName());

		dishRepository.save(dish);

		return dishAdapter.toDto(dish);

	}

	@Transactional
	@Override
	public void deleteDish(long id) {
		if (!dishRepository.existsById(id)) {
			throw new EntityNotFoundException("Dish not found with ID: " + id);
		}

		// Cascades to menu
		dishRepository.deleteById(id);

	}
}
