package com.training.spring;

import java.util.List;

import com.training.annotation.Autowired;
import com.training.annotation.Component;

@Component
public class ProductService {
	
	@Autowired
	private ProductRepository repo;
	
	public List<Product> getFInalPrice(List<Product> items){
		List<Product> list = repo.getPrice(items);
		
		for(Product product : list) {
			product.setPrice(product.getPrice() * (100 - product.getDiscount()/100));
			
			System.out.println("Price of "+product.getName()+" after "+product.getDiscount()+" % discount is "+product.getPrice());
		}
		return list;
	}

	
}
