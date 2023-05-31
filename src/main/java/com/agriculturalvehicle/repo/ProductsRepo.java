package com.agriculturalvehicle.repo;

import com.agriculturalvehicle.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepo extends JpaRepository<Products, Long> {
}
