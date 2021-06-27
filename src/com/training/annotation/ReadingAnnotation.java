package com.training.annotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ReadingAnnotation {

	public static void main(String[] args) throws Exception {
		Class<?> clss = Class.forName("com.training.annotation.Utility");
		Constructor<?> constructor = clss.getConstructor();
		Utility u = (Utility) constructor.newInstance();
		
		Method[] methods = clss.getDeclaredMethods();
		
		for(Method method : methods) {
			//System.out.println("checking method :"+method.getName());
			if(method.isAnnotationPresent(MostUsed.class)) {
				MostUsed annotation = method.getAnnotation(MostUsed.class);
				
				method.invoke(u, annotation.value());
			}
		}
		
		
		

	}

}
