package com.restaurent.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurent.adapter.RestaurantAdapter;
import com.restaurent.dto.RestaurantDto;
import com.restaurent.dto.request.RestaurantRequest;
import com.restaurent.entity.Restaurant;
import com.restaurent.entity.User;
import com.restaurent.repository.RestaurantRepository;
import com.restaurent.service.interfaces.RestaurantService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Service
public class RestaurantServiceImpl implements RestaurantService {

	RestaurantRepository restaurantRepository;

	RestaurantAdapter restaurantAdapter;

	@Override
	public List<RestaurantDto> getAllRestaurants() {
		return restaurantAdapter.toDto(restaurantRepository.findAll());
	}

	@Override
	public RestaurantDto getRestaurantById(long id) {
		Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
				"Restaurant not found with ID: " + id));

		return restaurantAdapter.toDto(restaurant);
	}

	@Override
	public List<RestaurantDto> getRestaurantByName(String name) {
		List<Restaurant> restaurants = restaurantRepository.findByNameContaining(name);
		return restaurantAdapter.toDto(restaurants);
	}

	@Override
	public RestaurantDto createRestaurant(RestaurantRequest restaurantRequest, User owner) {

		Restaurant restaurant = new Restaurant();
		restaurant.setName(restaurantRequest.getName());
		restaurant.setImage(restaurantRequest.getImage());
		restaurant.setOwner(owner);

		restaurantRepository.save(restaurant);

		return restaurantAdapter.toDto(restaurant);
	}

	@Override
	@Transactional
	public RestaurantDto updateRestaurant(long id, RestaurantRequest restaurantRequest) {

		Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
				"Restaurant not found with ID: " + id));

		restaurant.setName(restaurantRequest.getName());
		restaurant.setImage(restaurantRequest.getImage());

		restaurant = restaurantRepository.save(restaurant);

		return restaurantAdapter.toDto(restaurant);

	}

	@Transactional
	@Override
	public void deleteRestaurant(long id) {
		if (!restaurantRepository.existsById(id)) {
			throw new EntityNotFoundException("Restaurant not found with ID: " + id);
		}

		restaurantRepository.deleteById(id);

	}

	@Override
	public boolean isOwner(Long restaurantId, User authUser) {
		Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
				() -> new EntityNotFoundException(
						"Restaurant not found with ID: " + restaurantId));

		Long ownerId = restaurant.getOwner().getId();
		return ownerId.equals(authUser.getId());
	}

}
