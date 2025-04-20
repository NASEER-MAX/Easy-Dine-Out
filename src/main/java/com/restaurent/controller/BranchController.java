package com.restaurent.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurent.dto.BranchDto;
import com.restaurent.service.interfaces.BranchService;

import lombok.AllArgsConstructor;

@RequestMapping("/api/v1/restaurants")
@AllArgsConstructor
@RestController
public class BranchController {

	BranchService branchService;

	@GetMapping("/{restaurant_id}/branches")
	ResponseEntity<List<BranchDto>> getBranchesByRestaurantId(@PathVariable("restaurant_id") long restaurantId) {
		return ResponseEntity.ok(branchService.getBranchesByRestaurantId(restaurantId));
	}

	@GetMapping("/branches/{city_id}")
	ResponseEntity<List<BranchDto>> getRestaurantsByCityId(@PathVariable("city_id") long cityId) {
		return ResponseEntity.ok(branchService.getRestaurantsByCityId(cityId));
	}

	@PreAuthorize("hasPermission(#restaurantId, 'restaurant')")
	@PostMapping("/{restaurant_id}/branches/{city_id}")
	ResponseEntity<BranchDto> createBranch(@PathVariable("restaurant_id") long restaurantId,
			@PathVariable("city_id") long cityId) {
		return ResponseEntity.ok(branchService.createBranch(restaurantId, cityId));
	}

	@PreAuthorize("hasPermission(#branchId, 'branch')")
	@DeleteMapping("/branches/{branch_id}")
	ResponseEntity<?> deleteBranch(@PathVariable("branch_id") long branchId) {
		branchService.deleteBranch(branchId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
