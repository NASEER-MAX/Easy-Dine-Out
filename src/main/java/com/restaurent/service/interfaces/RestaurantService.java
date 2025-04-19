package com.restaurent.service.interfaces;

import java.util.List;

import com.restaurent.dto.RestaurantDto;
import com.restaurent.dto.request.RestaurantRequest;
import com.restaurent.entity.User;

public interface RestaurantService {

	List<RestaurantDto> getAllRestaurants();

	RestaurantDto getRestaurantById(long id);

	RestaurantDto createRestaurant(RestaurantRequest restaurant, User owner);

	RestaurantDto updateRestaurant(long id, RestaurantRequest restaurant);

	void deleteRestaurant(long id);

	List<RestaurantDto> getRestaurantByName(String name);

	boolean isOwner(Long restaurantId, User authUser);

}
