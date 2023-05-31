package com.agriculturalvehicle.repo;

import com.agriculturalvehicle.model.CarsDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarsDescriptionRepo extends JpaRepository<CarsDescription, Long> {
}
