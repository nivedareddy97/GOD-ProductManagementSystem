package com.capgemini.god.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.capgemini.god.entity.Product;

@Repository

public class ProductDaoImpl implements ProductDao {
	@PersistenceContext
	EntityManager pd;

	@Override
	public Product productCreation(Product pro) {
		// TODO Auto-generated method stub
		Product p = pd.find(Product.class, pro.getId());
		if(p==null)
		pd.merge(pro);
		return p;
	}

	@Override
	public Product getProductById(int id) {

		return pd.find(Product.class, id);
	}

	@Override
	public List<Product> getAllProduct() {
		Query q = pd.createQuery("select p from Product p");
		List<Product> prolist = q.getResultList();
		return prolist;
	}

	@Override
	public Product updateProduct(Product pro) {
		Product p = pd.find(Product.class, pro.getId());
		if (p != null) {
			p.setPrize(pro.getPrize());
			p.setColour(pro.getColour());
			p.setDimensions(pro.getDimensions());
			p.setSpecifications(pro.getSpecifications());
			p.setManufacture(pro.getManufacture());
			p.setQuantity(pro.getQuantity());
			p.setProductCatogery(pro.getProductCatogery());
			p.setProductName(pro.getProductName());
			p.setId(pro.getId());
		}
		return p;

	}

	@Override
	public Product deleteById(int id) {
		Product p = pd.find(Product.class, id);
		if (p != null) {
			pd.remove(p);
		}
		return p;
	}

	public List<Product> getFilteredData(String productName, int price1, int price2) {
		Query q = pd.createQuery("select p from Product p where p.productName=?1 and p.prize>=?2 and p.prize<=?3");
		List<Product> flist = q.getResultList();
		return flist;
	}
}
