package com.restaurent.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurent.dto.MenuDto;
import com.restaurent.dto.RestaurantDto;
import com.restaurent.dto.request.MenuRequest;
import com.restaurent.service.interfaces.MenuService;

import lombok.AllArgsConstructor;

@RequestMapping("/api/v1/restaurants")
@AllArgsConstructor
@RestController
public class MenuController {

	MenuService menuService;

	@GetMapping("/{restaurant_id}/menu")
	ResponseEntity<List<MenuDto>> getDishesByRestaurantId(@PathVariable("restaurant_id") long restaurantId) {
		return ResponseEntity.ok(menuService.getDishesByRestaurantId(restaurantId));
	}

	@GetMapping("/menu/{dish_id}")
	ResponseEntity<List<RestaurantDto>> getRestaurantsByDishId(@PathVariable("dish_id") long dishId) {
		return ResponseEntity.ok(menuService.getRestaurantsByDishId(dishId));
	}

	@PreAuthorize("hasPermission(#restaurantId, 'restaurant')")
	@PostMapping("/{restaurant_id}/menu/{dish_id}")
	ResponseEntity<MenuDto> createMenuItem(@PathVariable("restaurant_id") long restaurantId,
	@PathVariable("dish_id") long dishId, @RequestBody MenuRequest menuRequest) {
		return ResponseEntity.ok(menuService.createMenuItem(restaurantId, dishId, menuRequest));
	}

	@PreAuthorize("hasPermission(#menuId, 'menu')")
	@DeleteMapping("/menu/{menu_id}")
	ResponseEntity<?> deleteMenuItem(@PathVariable("menu_id") long menuId) {
		menuService.deleteMenuItem(menuId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
