package com.training.reflection;

public class GettingClassObject {

	public static void main(String[] args) throws Exception {
		//forName
		Class<?> clss1 = Class.forName("java.lang.String");
		
		//ClassName.class
		Class<?> clss2 = String.class;
		Class<?> clss3 = int.class;
		
		//obj.getClass
		ReflectionDemo m = new ReflectionDemo();
		Class<? extends ReflectionDemo> clss4 = m.getClass();
		
				//super class
				Class<?> superclass = clss4.getSuperclass();
		
		
	}

}
