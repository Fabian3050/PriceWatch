package com.example.PriceWatch.repositories;

import com.example.PriceWatch.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    @Override
    Optional<CategoryEntity> findById(Long id);
}
