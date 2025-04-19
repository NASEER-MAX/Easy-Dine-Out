package com.restaurent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurent.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {

	boolean existsByRestaurantIdAndDishId(Long restaurantId, Long dishId);

	List<Menu> findByRestaurantId(Long restaurantId);

	void deleteByRestaurantIdAndDishId(Long restaurantId, Long dishId);

	Menu findByRestaurantIdAndDishId(Long restaurantId, Long dishId);

	List<Menu> findByDishId(Long dishId);

}
