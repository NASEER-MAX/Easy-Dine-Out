package com.restaurent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurent.entity.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {

	List<Branch> findByCityId(Long cityId);

	List<Branch> findByRestaurantId(Long restaurantId);
}
