package org.api.challanges.sapient.repository;

import java.util.List;

import org.api.challanges.sapient.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByBrand(String brand);
	void deleteByBrand(String  brand);
}
