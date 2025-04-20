package com.restaurent.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurent.dto.DishDto;
import com.restaurent.dto.request.DishRequest;
import com.restaurent.service.interfaces.DishService;

import lombok.AllArgsConstructor;

@RequestMapping("/api/v1/dishes")
@AllArgsConstructor
@RestController
public class DishController {

	DishService dishService;

	@GetMapping
	ResponseEntity<List<DishDto>> getAllDishes() {
		return ResponseEntity.ok(dishService.getAllDishes());
	}

	@GetMapping("/{id}")
	ResponseEntity<DishDto> getDishById(@PathVariable("id") long id) {
		return ResponseEntity.ok(dishService.getDishById(id));
	}

	@PostMapping
	ResponseEntity<DishDto> createDish(@Validated @RequestBody DishRequest dish) {
		return ResponseEntity.status(HttpStatus.CREATED).body(dishService.createDish(dish));
	}

	@PutMapping("/{id}")
	ResponseEntity<DishDto> updateDish(@PathVariable("id") long id, @Validated @RequestBody DishRequest dish) {
		return ResponseEntity.ok(dishService.updateDish(id, dish));
	}

	@DeleteMapping("/{id}")
	ResponseEntity<?> deleteDish(@PathVariable("id") long id) {
		dishService.deleteDish(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
