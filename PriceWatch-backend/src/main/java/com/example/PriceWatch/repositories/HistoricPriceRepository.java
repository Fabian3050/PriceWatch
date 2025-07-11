package com.example.PriceWatch.repositories;

import com.example.PriceWatch.entities.HistoricPriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricPriceRepository extends JpaRepository<HistoricPriceEntity, Long> {
}
