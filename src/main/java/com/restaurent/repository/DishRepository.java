package com.restaurent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurent.entity.Dish;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {

}
