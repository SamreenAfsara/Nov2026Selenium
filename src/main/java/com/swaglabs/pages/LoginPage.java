package com.swaglabs.pages;

import org.openqa.selenium.WebDriver;

import com.swaglabs.utils.SmartFunctions;

public class LoginPage extends SmartFunctions {
	
	private WebDriver driver;
	
	public LoginPage(String pagename,WebDriver driver)
	{
		super(pagename,driver);
	}
	
	public void enterUsername(String uname)
	{
		enterText("username", uname);
	}

	
	public void enterPassword(String pass)
	{
		enterText("password", pass);
	}

	
	public void clickLoginBtn()
	{
		click("loginbtn");
	}
	
	public String getErrorMsg()
	{
		return getText("errormsg");
	}

	
}
