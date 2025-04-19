package com.restaurent.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurent.adapter.BranchAdapter;
import com.restaurent.dto.BranchDto;
import com.restaurent.entity.Branch;
import com.restaurent.entity.City;
import com.restaurent.entity.Restaurant;
import com.restaurent.entity.User;
import com.restaurent.repository.BranchRepository;
import com.restaurent.repository.CityRepository;
import com.restaurent.repository.RestaurantRepository;
import com.restaurent.service.interfaces.BranchService;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Service
public class BranchServiceImpl implements BranchService {

	RestaurantRepository restaurantRepository;
	CityRepository cityRepository;
	BranchRepository branchRepository;

	BranchAdapter branchAdapter;

	@Override
	public List<BranchDto> getBranchesByRestaurantId(long restaurantId) {
		if (!restaurantRepository.existsById(restaurantId)) {
			throw new EntityNotFoundException("Restaurant not found with ID: " + restaurantId);
		}

		List<Branch> branches = branchRepository.findByRestaurantId(restaurantId);
		return branchAdapter.toDto(branches);
	}

	@Override
	public List<BranchDto> getRestaurantsByCityId(long cityId) {
		if (!cityRepository.existsById(cityId)) {
			throw new EntityNotFoundException("City not found with ID: " + cityId);
		}

		List<Branch> branches = branchRepository.findByCityId(cityId);

		return branchAdapter.toDto(branches);
	}

	@Override
	public BranchDto createBranch(long restaurantId, long cityId) {

		Restaurant restaurant = restaurantRepository.findById(restaurantId)
				.orElseThrow(() -> new EntityNotFoundException("Restaurant not found with ID: " + restaurantId));

		City city = cityRepository.findById(cityId)
				.orElseThrow(() -> new EntityNotFoundException("City not found with ID: " + cityId));

		Branch branch = new Branch();
		branch.setCity(city);
		branch.setRestaurant(restaurant);

		Branch savedBranch = branchRepository.save(branch);

		return branchAdapter.toDto(savedBranch);
	}

	@Override
	@Transactional
	public void deleteBranch(long branchId) {

		if (!branchRepository.existsById(branchId)) {
			throw new EntityNotFoundException("Branch not found with Id: " + branchId);
		}

		branchRepository.deleteById(branchId);
	}

	@Override
	public boolean isOwner(Long branchId, User authUser) {
		Branch branch = branchRepository.findById(branchId).orElseThrow(() -> new EntityNotFoundException(
				"Branch not found with Id: " + branchId));

		Long ownerId = branch.getRestaurant().getOwner().getId();
		return ownerId.equals(authUser.getId());
	}

}
