package com.swaglabs.pages;

import org.openqa.selenium.WebDriver;

import com.swaglabs.utils.SmartFunctions;

public class InventoryPage extends SmartFunctions{

	public InventoryPage(String pagename, WebDriver driver) {
		super(pagename, driver);
	}

	
	public boolean isProductTitleVisible()
	{
		return isElementVisible("producttitle");
	}
}
