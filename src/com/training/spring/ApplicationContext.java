package com.training.spring;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.training.annotation.Autowired;
import com.training.annotation.Component;
import com.training.annotation.ComponentScan;
import com.training.annotation.Configuration;

public class ApplicationContext {
	
	private static Map<Class<?>, Object> map = new HashMap<>();
	
	public ApplicationContext(Class<AppConfig> configClass) {
		try {
			Spring.intializeSpringContext(configClass);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	private static class Spring{
		private static void intializeSpringContext(Class<?> configClass) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
			
			if(!configClass.isAnnotationPresent(Configuration.class)) {
				throw new RuntimeException("File is not configuration file");
			} else {
				ComponentScan annotation = configClass.getAnnotation(ComponentScan.class);
				String value = annotation.value();
				
				String packageStructure = "bin/"+value.replace(".", "/");
				
				File[] files = findClasses(new File(packageStructure));
				
				for (File file : files) {
					String className = value + "." +file.getName().replace(".class", "");
					Class<?> clss = Class.forName(className);
					
					if(clss.isAnnotationPresent(Component.class)) {
						Constructor<?> constructor = clss.getConstructor();
						Object newInstance = constructor.newInstance();
						map.put(clss, newInstance);
					}
					
				}
			}
			
		}

		private static File[] findClasses(File file) {
			
			if(!file.exists()) {
				throw new RuntimeException("Package "+file+" does not exists");
			}
				File[] listFiles = file.listFiles(e -> e.getName().endsWith(".class"));
						
			return listFiles;
		}

				
	}

	public <T> T getBean(Class<T> clss) {
		T obj = (T) map.get(clss);
		
		Field[] declaredFields = clss.getDeclaredFields();
		
		try {
			injectBean(obj, declaredFields);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return obj;
	}

	private <T> void injectBean(T obj, Field[] declaredFields) throws IllegalArgumentException, IllegalAccessException {
		
		for (Field field : declaredFields) {
			if(field.isAnnotationPresent(Autowired.class)) {
				field.setAccessible(true);
				Class<?> type = field.getType();
				Object innerObject = map.get(type);
				field.set(obj, innerObject);
				
				
				Field[] declaredFields2 = type.getDeclaredFields();
				injectBean(innerObject, declaredFields2);
			}
			
		}
		
	}

	

}
