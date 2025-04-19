package com.restaurent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurent.entity.City;

public interface CityRepository extends JpaRepository<City, Long> {

}
