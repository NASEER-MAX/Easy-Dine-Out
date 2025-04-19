package com.restaurent.repository;

import java.util.Date;
import java.util.List;

import com.restaurent.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface BookingRepository extends JpaRepository<Booking, Long> {

	boolean existsByBranchTableIdAndStartDateTime(Long branchTableId, Date startDateTime);

	List<Booking> findByCustomerId(Long customerId);

	@Query("SELECT b FROM Booking b "
			+ "JOIN b.branchTable bt "
			+ "JOIN bt.branch br "
			+ "JOIN br.restaurant r "
			+ "WHERE r.id = :restaurantId")
	List<Booking> findByRestaurantId(Long restaurantId);
}
