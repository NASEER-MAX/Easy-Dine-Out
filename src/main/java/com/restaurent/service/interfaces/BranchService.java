package com.restaurent.service.interfaces;

import java.util.List;

import com.restaurent.dto.BranchDto;
import com.restaurent.entity.User;

public interface BranchService {

	List<BranchDto> getBranchesByRestaurantId(long restaurantId);

	BranchDto createBranch(long restaurantId, long cityId);

	void deleteBranch(long restaurantId);

	List<BranchDto> getRestaurantsByCityId(long cityId);

	boolean isOwner(Long branchId, User authUser);

}
