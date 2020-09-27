package org.api.challanges.sapient.service;

import java.util.List;

import org.api.challanges.sapient.Product;
import org.api.challanges.sapient.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;

	@Override
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	@Override
	public List<Product> getAllProductsByBrand(String brand) {
		return productRepo.findByBrand(brand);
	}

}
