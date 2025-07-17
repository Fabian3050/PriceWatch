package com.example.PriceWatch.repositories;

import com.example.PriceWatch.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    @Override
    Optional<ProductEntity> findById(Long id);
    
    Optional<ProductEntity> findByNameProduct(String nameProduct);

    List<ProductEntity> findAllByNameProduct(String name);

    List<ProductEntity> findDistinctProductsByStoreId(@Param("storeId") Long storeId);
}
