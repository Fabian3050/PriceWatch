package com.example.PriceWatch.repositories;

import com.example.PriceWatch.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsername(String username);

    @Override
    Optional<UserEntity> findById(Integer id);
}
