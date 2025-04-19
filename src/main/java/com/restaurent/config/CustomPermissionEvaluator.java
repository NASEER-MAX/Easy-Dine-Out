package com.restaurent.config;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.restaurent.entity.User;
import com.restaurent.service.interfaces.BookingService;
import com.restaurent.service.interfaces.BranchService;
import com.restaurent.service.interfaces.BranchTableService;
import com.restaurent.service.interfaces.MenuService;
import com.restaurent.service.interfaces.RestaurantService;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@NoArgsConstructor
public class CustomPermissionEvaluator implements PermissionEvaluator {

	@Autowired
	RestaurantService restaurantService;

	@Autowired
	MenuService menuService;

	@Autowired
	BranchService branchService;

	@Autowired
	BranchTableService branchTableService;

	@Autowired
	BookingService bookingService;

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		if ((authentication == null) || (targetDomainObject == null) || !(permission instanceof String)) {
			return false;
		}

		User authUser = (User) authentication.getPrincipal();

		String permssionString = (String) permission;

		switch (permssionString) {

		case "restaurant":
			Long restaurantId = (Long) targetDomainObject;
			return restaurantService.isOwner(restaurantId, authUser);

		case "menu":
			Long menuId = (Long) targetDomainObject;
			return menuService.isOwner(menuId, authUser);

		case "branch":
			Long branchId = (Long) targetDomainObject;
			return branchService.isOwner(branchId, authUser);

		case "branchTable":
			Long branchTableId = (Long) targetDomainObject;
			return branchTableService.isOwner(branchTableId, authUser);

		case "booking":
			Long bookingId = (Long) targetDomainObject;
			return bookingService.isCustomer(bookingId, authUser);

		default:
			return false;
		}

	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		// TODO Auto-generated method stub
		return false;
	}

}
