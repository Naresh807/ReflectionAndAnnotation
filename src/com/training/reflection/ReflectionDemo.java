package com.training.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class MyClass{
	private MyClass() {
		System.out.print("Creating object");
	}
	
}

public class ReflectionDemo {

	public static void main(String[] args) {
		//MyClass obj = new MyClass();
		try {
		Class<?> classObj =	Class.forName("com.training.reflection.MyClass");
		Constructor<?> cons=  classObj.getDeclaredConstructor();
		cons.setAccessible(true);
		Object obj = cons.newInstance();
		
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
