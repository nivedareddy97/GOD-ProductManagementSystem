package com.capgemini.god.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.god.dao.ProductDao;
import com.capgemini.god.entity.Product;

@Service
@Transactional

public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao dao;

	@Override
	public Product productCreation(Product pro) {
		return dao.productCreation(pro);
	}

	@Override
	public Product getProductById(int id) {
		return dao.getProductById(id);
	}

	@Override
	public List<Product> getAllProduct() {
		return dao.getAllProduct();
	}

	@Override
	public Product delete(int id) {
		return dao.deleteById(id);
	}

	@Override
	public Product updateProduct(Product prod) {
		return dao.updateProduct(prod);
	}

	@Override
	public List<Product> getFilteredData(String productName, int price1, int price2) {
		return dao.getFilteredData(productName, price1, price2);
	}

}
