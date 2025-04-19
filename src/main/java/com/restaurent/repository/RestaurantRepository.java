package com.restaurent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurent.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

	public List<Restaurant> findByNameContaining(String name);

}
