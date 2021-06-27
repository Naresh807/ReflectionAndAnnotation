package com.training.practice;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.training.annotation.Column;
import com.training.annotation.PrimaryKey;

public class Hibernate<T> {
	
	private Connection con;
	private AtomicLong pkeyvalue = new AtomicLong(0);
	
	public static <T> Hibernate<T> getConnection() throws SQLException{
		return new Hibernate<T>();
	}
	
	private Hibernate() throws SQLException{
		this.con = DriverManager.getConnection("jdbc:h2:E:/eclipse-workspace/spring-suite/workspace/Naresh1/ReflectionAndAnnotation/Database/practice1","sa","");
	}

	public void write(T t) throws Exception {
		Class<? extends Object> clss = t.getClass();
		
		Field[] fields = clss.getDeclaredFields();
		Field pkey = null;
		List<Field> columns = new ArrayList<>();
		StringJoiner joiner = new StringJoiner(",");
		
		
		for(Field field : fields) {
			 if(field.isAnnotationPresent(PrimaryKey.class)) {
				 pkey = field;
				// System.out.println("Primary key is "+field.getName()+" Value="+field.get(t));
			 }else if (field.isAnnotationPresent(Column.class)) {
				 joiner.add(field.getName());
				 columns.add(field);
				// System.out.println("Column :"+field.getName()+" Value="+field.get(t));
				 
			 }
		}
		int number = columns.size() + 1;
				
		String qMarks = IntStream.range(0, number)
						.mapToObj(e -> "?")
						.collect(Collectors.joining(","));
     
	 	String sql = "insert into "+clss.getSimpleName()+"("+pkey.getName()+","+joiner.toString()+")"+ "values("+ qMarks+")";
	 	PreparedStatement prepareStatement = con.prepareStatement(sql);
	 	if (pkey.getType() == long.class) {
	 		prepareStatement.setLong(1, pkeyvalue.incrementAndGet());
	 	}
	 	
	 	int index = 2;
	 	for (Field field : columns) {
	 		
	 		field.setAccessible(true);
	 		
	 		if(field.getType() == int.class) {
	 			prepareStatement.setInt(index++, (int)field.get(t));
	 		} else if(field.getType() == String.class) {
	 			prepareStatement.setString(index++, (String)field.get(t));
	 		} else if(field.getType() == double.class) {
	 			prepareStatement.setDouble(index++, (double)field.get(t));
	 		}
	 		
		}
	 	System.out.println(sql);
	 	prepareStatement.executeUpdate();
 	
		
	}

	public  T read(Class<T> clss, long l) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		Field[] fields = clss.getDeclaredFields();
		Field pkey = null;
		for(Field field : fields) {
			 if(field.isAnnotationPresent(PrimaryKey.class)) {
				 pkey = field;
				 break;
			 }
		}	 
		
		String sql = "Select * from "+clss.getSimpleName() + " where "+ pkey.getName() +" = "+ l ;
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		rs.next();
		
		T t = clss.getConstructor().newInstance();
		
		long transactionId = rs.getInt(pkey.getName());
		pkey.setAccessible(true);
		pkey.set(t, transactionId);
		for(Field field : fields) {
			field.setAccessible(true);
			 if(field.isAnnotationPresent(Column.class)) {
				 if(field.getType() == int.class) {
					 int columnValue = rs.getInt(field.getName());
					 field.set(t, columnValue);
				 } else if(field.getType() == String.class){
					 String columnValue = rs.getString(field.getName());
					 field.set(t, columnValue);
				 }
			 }
		}	
		
		return t;
	}

}
