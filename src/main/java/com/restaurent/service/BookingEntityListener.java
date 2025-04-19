package com.restaurent.service;


import com.restaurent.entity.Booking;
import com.restaurent.service.interfaces.NotificationService;


import com.restaurent.util.BeanContextUtil;
import jakarta.persistence.PostRemove;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
public class BookingEntityListener {

	@PostRemove
	public void preRemove(Booking booking) {
		NotificationService notificationService = BeanContextUtil.getBean(NotificationService.class);
		notificationService.notify(booking);

	}

}