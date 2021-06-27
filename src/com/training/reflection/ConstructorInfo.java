package com.training.reflection;

import java.lang.reflect.Constructor;

public class ConstructorInfo {
	public static void main(String[] args) throws Exception{
		
		Class<?> clss1 = Class.forName("com.training.reflection.Entity");
		Constructor<?>[] constructors = clss1.getConstructors();//give constructor for current class
		
		for(Constructor constructor :constructors) {
			System.out.println(constructor);
		}
	
		System.out.println();
				
       Constructor<?>[] declaredConstructors = clss1.getDeclaredConstructors();
		
		for(Constructor constructor :declaredConstructors) {
			System.out.println(constructor);
		}
		
		Constructor<?> publicConstructor = clss1.getConstructor(int.class, String.class);
		Entity newInstance = (Entity) publicConstructor.newInstance(2,"Naresh");
		System.out.println(newInstance.getType());
		
		Constructor<?> privateConstructor = clss1.getDeclaredConstructor();
		privateConstructor.setAccessible(true);
		Entity e = (Entity) privateConstructor.newInstance();
		System.out.println(e.getType());
	}
}
