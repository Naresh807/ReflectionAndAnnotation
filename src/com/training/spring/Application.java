package com.training.spring;

import java.util.ArrayList;
import java.util.List;

public class Application {

	public static void main(String[] args) {
	
		ApplicationContext context = new ApplicationContext(AppConfig.class);
        ProductService productService =  context.getBean(ProductService.class);
        
        List<Product> items = new ArrayList<>();
        
        items.add( new Product("Yoga Mat",40));
        items.add( new Product("Mouse ",20));
        
		productService.getFInalPrice(items );
	}

}
