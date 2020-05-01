package com.capgemini.god.dao;

import java.util.List;

import com.capgemini.god.entity.Product;

public interface ProductDao {

	Product deleteById(int id);

	List<Product> getAllProduct();

	Product getProductById(int id);

	Product productCreation(Product pro);

	Product updateProduct(Product pro);

	List<Product> getFilteredData(String productName, int price1, int price2);

}
