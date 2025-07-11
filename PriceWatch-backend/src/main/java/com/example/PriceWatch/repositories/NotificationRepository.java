package com.example.PriceWatch.repositories;

import com.example.PriceWatch.entities.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    @Override
    Optional<NotificationEntity> findById(Long id);
}
