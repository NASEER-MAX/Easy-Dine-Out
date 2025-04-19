package com.restaurent.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurent.adapter.MenuAdapter;
import com.restaurent.adapter.RestaurantAdapter;
import com.restaurent.dto.MenuDto;
import com.restaurent.dto.RestaurantDto;
import com.restaurent.dto.request.MenuRequest;
import com.restaurent.entity.Dish;
import com.restaurent.entity.Menu;
import com.restaurent.entity.Restaurant;
import com.restaurent.entity.User;
import com.restaurent.repository.DishRepository;
import com.restaurent.repository.MenuRepository;
import com.restaurent.repository.RestaurantRepository;
import com.restaurent.service.interfaces.MenuService;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Service
public class MenuServiceImpl implements MenuService {

	RestaurantRepository restaurantRepository;
	DishRepository dishRepository;
	MenuRepository menuRepository;

	RestaurantAdapter restaurantAdapter;
	MenuAdapter menuAdapter;

	@Override
	public List<MenuDto> getDishesByRestaurantId(long restaurantId) {
		if (!restaurantRepository.existsById(restaurantId)) {
			throw new EntityNotFoundException("Restaurant not found with Id: " + restaurantId);
		}
		List<Menu> menu = menuRepository.findByRestaurantId(restaurantId);
		return menuAdapter.toDto(menu);
	}

	@Override
	public List<RestaurantDto> getRestaurantsByDishId(long dishId) {
		List<Menu> menus = menuRepository.findByDishId(dishId);

		List<Restaurant> restaurants = menus.stream()
				.map(Menu::getRestaurant)
				.collect(Collectors.toList());

		return restaurantAdapter.toDto(restaurants);
	}

	@Transactional
	@Override
	public MenuDto createMenuItem(long restaurantId, long dishId, MenuRequest menuRequest) {

		if (menuRepository.existsByRestaurantIdAndDishId(restaurantId, dishId)) {
			throw new EntityExistsException("Menu item already exists with dish Id: " + dishId);
		}

		Restaurant restaurant = restaurantRepository.findById(restaurantId)
				.orElseThrow(() -> new EntityNotFoundException("Restaurant not found with ID: " + restaurantId));

		Dish dish = dishRepository.findById(dishId)
				.orElseThrow(() -> new EntityNotFoundException("Dish not found with ID: " + dishId));

		Menu menu = new Menu();
		menu.setDish(dish);
		menu.setRestaurant(restaurant);
		menu.setPrice(menuRequest.getPrice());

		menuRepository.save(menu);

		return menuAdapter.toDto(menu);
	}

	@Override
	public void deleteMenuItem(long menuId) {
		if (!menuRepository.existsById(menuId)) {
			throw new EntityNotFoundException("Menu item not found with Id: " + menuId);
		}

		menuRepository.deleteById(menuId);

	}

	@Override
	public boolean isOwner(Long menuId, User authUser) {
		Menu menu = menuRepository.findById(menuId).orElseThrow(() -> new EntityNotFoundException(
				"Menu item not found with Id: " + menuId));

		Long ownerId = menu.getRestaurant().getOwner().getId();
		return ownerId.equals(authUser.getId());
	}

}
