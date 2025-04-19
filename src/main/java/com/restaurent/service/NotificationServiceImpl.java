package com.restaurent.service;

import com.restaurent.entity.Booking;
import com.restaurent.entity.Notification;
import com.restaurent.repository.NotificationRepository;
import org.springframework.stereotype.Service;


import com.restaurent.service.interfaces.NotificationService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {

	NotificationRepository notificationRepository;

	@Override
	public void notify(Booking booking) {

		Notification notification = new Notification();
		notification.setCustomer(booking.getCustomer());
		notification.setStartDateTime(booking.getStartDateTime());
		notification.setEndDateTime(booking.getEndDateTime());

		notificationRepository.save(notification);
	}

}
