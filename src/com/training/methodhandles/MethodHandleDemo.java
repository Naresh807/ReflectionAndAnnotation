package com.training.methodhandles;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.lang.invoke.VarHandle;

public class MethodHandleDemo {

	public static void main(String[] args) throws Throwable {
		
		Lookup lookup = MethodHandles.lookup();
	    Class<?> clss = lookup.findClass(Student.class.getName());
	    
	    Student s = new Student();
	    s.setCourse("Java");
	    
	    MethodType methodType = MethodType.methodType(String.class);
	    MethodHandle findVirtual = lookup.findVirtual(clss, "getCourse", methodType);
	    System.out.println(findVirtual.invoke(s));
	    
	    MethodType type = MethodType.methodType(void.class);
	    MethodHandle noArgHandle = lookup.findConstructor(clss, type);
	    Student s1 = (Student) noArgHandle.invoke();
	    System.out.println(s1);
	    
	    MethodType type1 = MethodType.methodType(void.class, String.class, String.class);
	    MethodHandle paramConstructor = lookup.findConstructor(clss, type1);
	    Student s2 = (Student) paramConstructor.invoke("Naresh","java");
	    System.out.println(s2);
	    
	    MethodType methodType2 = MethodType.methodType(void.class, String.class);
	    MethodHandle setNameHandle = lookup.findVirtual(clss, "setName", methodType2);
	    setNameHandle.invoke(s1,"Naresh");
	    System.out.println(s1);
	    
	    MethodType methodType3 = MethodType.methodType(void.class, int.class);
	    MethodHandle setNumOfStudentsHandle = lookup.findStatic(clss, "setNumOfStudents", methodType3);
	    setNumOfStudentsHandle.invoke(10);
	    System.out.println(Student.getNumOfStudents());
	    
	    //to access private fields
	    Lookup privateLookupIn = MethodHandles.privateLookupIn(clss, lookup);
	    MethodHandle findGetter = privateLookupIn.findGetter(clss, "name", String.class);
	    MethodHandle findSetter = privateLookupIn.findSetter(clss, "name", String.class);
	    System.out.println(findGetter.invoke(s1));
	    findSetter.invoke(s1,"OM");
	    
	    //Varhandles  used for fields
	    VarHandle courseVarHandle = privateLookupIn.findVarHandle(clss, "course", String.class);
	    courseVarHandle.set(s1,"Reflection");
	    String val = (String) courseVarHandle.get(s1);
	    System.out.println(val);
	    
	    
	    
	    
	    
	}

}
