package com.infy.validator;

public class Validator {
	
	public static Boolean validateName(String name) {
		String regex = "[A-Za-z]+(\\s[A-Za-z]+){,100}*";
		if(name.matches(regex)) {
			return true;
		}
		return false;
	}
	public static Boolean validateDescription(String description) {
		String regex = "[A-Za-z]{0,500}";
		if(description.matches(regex)) {
			return true;
		}
		return false;
	}
	public static Boolean validatePrice(int price) {
		if(price>= 200) {
			return true;
		}
		return false;
	}
	
	public static Boolean validateStock(int stock) {
		
		if(stock>=10) {
			return true;
		}
		return false;
	}
	
}
