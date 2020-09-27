package org.api.challanges.sapient.controller;

import java.util.List;

import org.api.challanges.sapient.Product;
import org.api.challanges.sapient.Products;
import org.api.challanges.sapient.exception.ProductNotFoundException;
import org.api.challanges.sapient.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/challanges/sapient")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping(value = "/get-all-products", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Products> getAllProducts() {
		List<Product> productList = productService.getAllProducts();
		if (productList.isEmpty()) {
			throw new ProductNotFoundException("Product Not Found");
		}
		Products products = new Products();
		products.setProducts(productList);
		return ResponseEntity.ok(products);
	}

	@GetMapping(value = "/brand/{brand}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Products> getProductsByBrand(@PathVariable String brand) {
		List<Product> productList = productService.getAllProductsByBrand(brand);
		if (productList.isEmpty()) {
			throw new ProductNotFoundException("Product Not Found");
		}
		Products products = new Products();
		products.setProducts(productList);
		return ResponseEntity.ok(products);
	}

}
