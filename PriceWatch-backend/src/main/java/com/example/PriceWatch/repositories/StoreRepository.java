package com.example.PriceWatch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import javax.management.Notification;

public interface StoreRepository extends JpaRepository<Notification, Long> {
}
