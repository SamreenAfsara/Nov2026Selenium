package com.swaglabs.utils;

import java.io.FileInputStream;
import java.util.Properties;

;

public class ReadLocator {

	
	private Properties pro;
	public ReadLocator(String filename)
	{
		String filepath="src/test/resources/locators/"+filename+".properties";
		try {
			FileInputStream instream = new FileInputStream(filepath);
			
			 pro = new Properties();
			pro.load(instream);
		
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	
	public String getLocatorValue(String key)
	{
		return pro.getProperty(key);
	}
	
	public static void main(String[] args) {
		ReadLocator obj = new ReadLocator("loginpage");
		String value=obj.getLocatorValue("username");
		System.out.println(value);
	}
	
}
