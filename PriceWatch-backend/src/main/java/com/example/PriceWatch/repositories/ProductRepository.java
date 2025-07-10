package com.example.PriceWatch.repositories;

import com.example.PriceWatch.entities.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<NotificationEntity, Long> {
}
