package com.restaurent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restaurent.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
