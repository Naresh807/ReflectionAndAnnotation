package com.training.reflection;

import java.lang.reflect.Method;

public class MethodInfo {
	
	public static void main(String[] args) throws Exception{
		
		Entity e = new Entity(1, "id");
		Class<? extends Entity> clss = e.getClass();
		
		Method[] methods = clss.getMethods();//get all public methods of this class and it's superclass
		for(Method method : methods) {
			System.out.println(method.getName());
		}
		System.out.println("*****************************************");
        Method[] declareMethods = clss.getDeclaredMethods();//get all methods of this class
		for(Method method : declareMethods) {
			System.out.println(method.getName());
		}
		
		//to get details of a method
		Method method = clss.getMethod("setVal", int.class);
		method.invoke(e, 10);
		System.out.println(e.getVal());
	}

}
