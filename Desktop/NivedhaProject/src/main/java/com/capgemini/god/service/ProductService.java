package com.capgemini.god.service;

import java.util.List;

import com.capgemini.god.entity.Product;

public interface ProductService {
	Product productCreation(Product pro);

	Product getProductById(int id);

	List<Product> getAllProduct();

	Product delete(int id);

	Product updateProduct(Product prod);

	List<Product> getFilteredData(String productName,int price1,int price2);

}
