package com.restaurent.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restaurent.dto.BookingDto;
import com.restaurent.dto.request.BookingRequest;
import com.restaurent.entity.User;
import com.restaurent.service.interfaces.BookingService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RequestMapping("/api/v1/bookings")
@AllArgsConstructor
@RestController
public class BookingController {

	BookingService bookingService;


	@GetMapping
	ResponseEntity<List<BookingDto>> getBookings(Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		return ResponseEntity.ok(bookingService.getBookings(user));
	}

	@GetMapping("/restaurants/{restaurant_id}")
	ResponseEntity<List<BookingDto>> getBookingsByRestaurantId(@PathVariable("restaurant_id") long restaurantId) {
		return ResponseEntity.ok(bookingService.getBookingsByRestaurantId(restaurantId));
	}

	@PostMapping("/{branch_table_id}")
	ResponseEntity<BookingDto> bookTable(@PathVariable("branch_table_id") long branchTableId,
			@Valid @RequestBody BookingRequest bookingRequest,
			Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		return ResponseEntity.ok(bookingService.bookTable(branchTableId, bookingRequest, user));
	}

	@PreAuthorize("hasPermission(#bookingId, 'booking')")
	@DeleteMapping("/{booking_id}")
	ResponseEntity<?> cancelBooking(@PathVariable("booking_id") long bookingId) {
		bookingService.cancelBooking(bookingId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
