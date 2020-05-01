package com.capgemini.god.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.god.entity.Product;
import com.capgemini.god.exceptions.IdNotFoundException;
import com.capgemini.god.service.ProductService;
import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {
	@Autowired
	ProductService serviceobj;
	// Create Product
		@PostMapping("/ProductCreation")
		public ResponseEntity<String> ProductCreation(@RequestBody Product pro) {
			Product p = serviceobj.productCreation(pro);
			if (p!= null) {
				return new ResponseEntity<String>("Id already exists", new HttpHeaders(), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Product created successfully", new HttpHeaders(), HttpStatus.OK);
			}
		}
		// Get Particular Product Data
		@GetMapping("/GetProduct/{id}")
		private ResponseEntity<Product> getProduct(@PathVariable("id") int id) {
			Product p = serviceobj.getProductById(id);
			if (p == null) {
				throw new IdNotFoundException("Id does not exist,so we couldn't fetch details");
			} else {
				return new ResponseEntity<Product>(p, new HttpHeaders(), HttpStatus.OK);
			}
		}

		// Get All Products Data
		@GetMapping("/GetAllProducts")
		private ResponseEntity<List<Product>> getAllProduct() {
			List<Product> prolist = serviceobj.getAllProduct();
			return new ResponseEntity<List<Product>>(prolist, new HttpHeaders(), HttpStatus.OK);

		}

		// Updating Product data
		@PutMapping("/UpdateProduct")
		public ResponseEntity<String> updateProduct(@RequestBody Product pro) {
			Product p = serviceobj.updateProduct(pro);
			if (p == null) {
				return new ResponseEntity<String>("Updated operation unsuccessful", new HttpHeaders(), HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Product updated successfully", new HttpHeaders(), HttpStatus.OK);
			}
		}

		// Deleting Product
		@DeleteMapping("/DeleteProduct/{id}")
		private ResponseEntity<String> delpro(@PathVariable("id") int id) {
			Product p = serviceobj.delete(id);
			if (p == null) {
				throw new IdNotFoundException("Delete Operation Unsuccessful,Provided Id does not exist");
			} else {
				return new ResponseEntity<String>("Product deleted successfully", new HttpHeaders(), HttpStatus.OK);
			}
		}
		
		@GetMapping("/GetFilteredData/{productName}/{price1}/{price2}")
		private ResponseEntity<List<Product>> getFilteredData(@PathVariable("productName") String productName,@PathVariable("price1") int price1,@PathVariable("price2") int price2) {
			List<Product> filterdata = serviceobj.getFilteredData(productName,price1,price2);
			return new ResponseEntity<List<Product>>(filterdata, new HttpHeaders(), HttpStatus.OK);

		}

		@ExceptionHandler(IdNotFoundException.class)
		public ResponseEntity<String> userNotFound(IdNotFoundException p) {
			return new ResponseEntity<String>(p.getMessage(), HttpStatus.NOT_FOUND);
		}
	}


