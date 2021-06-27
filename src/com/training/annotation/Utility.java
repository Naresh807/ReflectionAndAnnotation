package com.training.annotation;

@MostUsed
public class Utility {
	
	void doStuff() {
		System.out.println("Doing some stuff");
	}
	
	@MostUsed(value = "Phyton")
	void doStuff(String r) {
		System.out.println("Doing some stuff:"+r);
	}
	
	void doStuff(int a) {
		System.out.println("Doing some stuff:"+a);
	}
	
}

class ChildUtility extends Utility{
	
}
