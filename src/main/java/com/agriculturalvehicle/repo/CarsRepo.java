package com.agriculturalvehicle.repo;

import com.agriculturalvehicle.model.Cars;
import com.agriculturalvehicle.model.enums.BodyType;
import com.agriculturalvehicle.model.enums.Fuel;
import com.agriculturalvehicle.model.enums.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarsRepo extends JpaRepository<Cars, Long> {
    List<Cars> findAllByDescription_BodyTypeAndDescription_FuelAndDescription_TransmissionOrderByFreeDesc(BodyType bodyType, Fuel fuel, Transmission transmission);
    List<Cars> findAllByOrderByFreeDesc();
}
