package com.training.reflection;

import java.lang.reflect.Field;

public class FieldInfo {

	public static void main(String[] args) throws Exception{
		Entity e = new Entity(1, "id");
		Class<? extends Entity> clss = e.getClass();
		
		Field[] fields = clss.getFields(); // Give only public fields 
		
		for(Field field : fields) {
			System.out.println(field.getName());
		}
		
		Field[] declaredFields = clss.getDeclaredFields(); //Get all fields
		for(Field field : declaredFields) {
			System.out.println(field.getName() +"::"+field.getType());
		}
		
		//non-declared : all the public elements in that class and it's super class
		//declared : all the elements in that class only.
		
		Field field = clss.getField("type");
		//Field field2 = clss.getField("val"); cann't be used for private variable
		Field field2 = clss.getDeclaredField("val"); 
		field.set(e, "rollno.");
		field2.setAccessible(true);//to access private field we should setAccessible to true
		field2.set(e, 10); //it cannot access private variable
		System.out.println(e.getType());
		System.out.println(e.getVal());
		
	}

}
