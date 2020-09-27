package org.api.challanges.sapient.service;

import java.util.List;

import org.api.challanges.sapient.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

	public List<Product> getAllProducts();

	List<Product> getAllProductsByBrand(String brand);
}
