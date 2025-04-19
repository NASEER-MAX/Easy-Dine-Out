package com.restaurent.service.interfaces;

import java.util.List;

import com.restaurent.dto.MenuDto;
import com.restaurent.dto.RestaurantDto;
import com.restaurent.dto.request.MenuRequest;
import com.restaurent.entity.User;

public interface MenuService {

	MenuDto createMenuItem(long restaurantId, long dishId, MenuRequest menuRequest);

	List<MenuDto> getDishesByRestaurantId(long restaurantId);

	void deleteMenuItem(long menuId);

	List<RestaurantDto> getRestaurantsByDishId(long dishId);

	boolean isOwner(Long menuId, User authUser);

}
