package com.training.annotation;

public class Enhancements {

	public static void main(String[] args) {
	  Box<String> box = new @NonEmpty @ReadOnly Box<>(10, "Container");

	}

}
