package com.restaurent.service.interfaces;

import java.util.List;

import com.restaurent.dto.BookingDto;
import com.restaurent.dto.request.BookingRequest;
import com.restaurent.entity.User;

public interface BookingService {

	BookingDto bookTable(long branchTableId, BookingRequest bookingRequest, User customer);

	void cancelBooking(long bookingId);

	List<BookingDto> getBookings(User customer);

	List<BookingDto> getBookingsByRestaurantId(long restaurantId);

	boolean isCustomer(Long bookingId, User authUser);

}
