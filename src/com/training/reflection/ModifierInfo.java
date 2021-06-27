package com.training.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ModifierInfo {

	public static void main(String[] args) throws Exception {
		Entity e = new Entity(1, "id");
		Class<? extends Entity> clss = e.getClass();
		
		int modifiers = clss.getModifiers();
		int i = modifiers & Modifier.PUBLIC;
		System.out.println(modifiers);
		boolean public1 = Modifier.isPublic(modifiers);
		System.out.println(public1);
		
		Method method = clss.getDeclaredMethod("getVal");
		method.setAccessible(true);
		int modifiers2 = method.getModifiers();
		int j = modifiers2 & Modifier.PUBLIC;
		System.out.println(j);

	}

}
