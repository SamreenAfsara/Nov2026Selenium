package com.swaglabs.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SmartFunctions {

	private ReadLocator readLocator;
	private WebDriver driver;
	
	public SmartFunctions(String pagename,WebDriver driver)
	{
	
		 readLocator=new ReadLocator(pagename);
		 this.driver=driver;
	}
	
	private By getLocator(String elementname)
	{
		
		String locatorValue=readLocator.getLocatorValue(elementname);
		String locatorValues[]=locatorValue.split(":");
		
		System.out.println(locatorValues[0]+"   "+locatorValues[1]);
		By locator=null;
		
		switch(locatorValues[0].toLowerCase())
		{
		case "id":locator=By.id(locatorValues[1]);
			break;
		case "name":locator=By.name(locatorValues[1]);
			break;
		case "class": locator=By.className(locatorValues[1]);
			break;
		case "xpath":locator=By.xpath(locatorValues[1]);
			break;
		case "css":locator=By.cssSelector(locatorValues[1]);
		}
		
		return  locator;
	}
	
	private WebElement getElement(String elementname)
	{
		return driver.findElement(getLocator(elementname));
	}
	
	private List<WebElement> getElements(String elementname)
	{
		return driver.findElements(getLocator(elementname));
	}
	
	protected void enterText(String elementname,String text)
	{
		getElement(elementname).sendKeys(text);
	}
	
	protected void click(String elementname)
	{
		getElement(elementname).click();
	}
	protected String getText(String elementname)
	{
		return getElement(elementname).getText();
	}
	
	protected boolean isElementVisible(String elementname)
	{
		
		try
		{
			return getElement(elementname).isDisplayed();
		}
		catch(Exception e)
		{
			return false;
		}
		
	}
	
	
	
	
}
