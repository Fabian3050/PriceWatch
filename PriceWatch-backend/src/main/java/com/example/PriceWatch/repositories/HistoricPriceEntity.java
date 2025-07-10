package com.example.PriceWatch.repositories;

import com.example.PriceWatch.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HistoricPriceEntity extends JpaRepository<HistoricPriceEntity, Long> {
}
