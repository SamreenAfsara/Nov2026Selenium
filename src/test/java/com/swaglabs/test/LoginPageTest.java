package com.swaglabs.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.swaglabs.pages.InventoryPage;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.utils.Browser;
import com.swaglabs.utils.ConfigData;
import com.swaglabs.utils.ReadData;
//import com.swaglabs.utils.RetryAnalyzer;

public class LoginPageTest  extends Browser{
	
	
	@BeforeMethod(alwaysRun=true)
	@Parameters({"browsername"})
	public void setUp(String bname)
	{
		launchBrowser(bname);
		openURL();
	
	}
	
	
	@AfterMethod(alwaysRun=true)
	public void teardown()
	{
		closeBrowser();
	}
	
	
	@Test(enabled=true,groups="smoke")
	public void verifyLoginBtnFunctionalityWithBlankUsername()
	{
		LoginPage loginpage = new LoginPage("loginpage", driver);
		loginpage.clickLoginBtn();
		
		String actualErrorMsg=loginpage.getErrorMsg();
		String expectedErrorMsg=ConfigData.BLANK_USERNAME;
		
		Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
	}
	
	@Test(enabled=true,groups="smoke")
	public void verifyLoginBtnFunctionalityWithBlankPassword()
	{
		 ReadData readData = new ReadData("loginpage");
		 String uname=readData.getData(1, 0);
		LoginPage loginpage = new LoginPage("loginpage", driver);
		loginpage.enterUsername(uname);
		loginpage.clickLoginBtn();
		
		String actualErrorMsg=loginpage.getErrorMsg();
		String expectedErrorMsg=ConfigData.BLANK_PASSWORD;
		
		Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
	}
	

	@Test(enabled=true,groups="smoke")
	public void verifyLoginBtnFunctionalityWithInvalidUsernameAndInvalidPassword() throws InterruptedException
	{
		 ReadData readData = new ReadData("loginpage");
		 List<String> logindata=readData.getData(10);
		 String uname=logindata.get(0);
	     String pass=logindata.get(1);
		LoginPage loginpage = new LoginPage("loginpage", driver);
		loginpage.enterUsername(uname);
		loginpage.enterPassword(pass);
		loginpage.clickLoginBtn();
		Thread.sleep(3000);
		String actualErrorMsg=loginpage.getErrorMsg();
		String expectedErrorMsg=ConfigData.BLANK_PASSWORD;
		
		Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
	}
	
	@Test(groups="smoke")
	public void verifyLoginBtnFunctionalityWithValidUsernameAndValidPassword() throws InterruptedException
	{
		 ReadData readData = new ReadData("loginpage");
		 List<String> logindata=readData.getData(1);
		 String uname=logindata.get(0);
	     String pass=logindata.get(1);
		LoginPage loginpage = new LoginPage("loginpage", driver);
		loginpage.enterUsername(uname);
		loginpage.enterPassword(pass);
		loginpage.clickLoginBtn();
		Thread.sleep(3000);
		InventoryPage inven= new InventoryPage("inventory", driver);
		
		Assert.assertTrue(inven.isProductTitleVisible(),"Test case failed due to Product title is not visible");
		Assert.assertEquals(getURL(), ConfigData.INVENTORY_URL);
	}
	
	@Test(dataProvider="logindata",groups="smoke"/*,retryAnalyzer=RetryAnalyzer.class */)
	public void verifyLoginBtnFunctionalityWithValidUsernameAndValidPassword(String uname,String pass) throws InterruptedException
	{
		
		
		LoginPage loginpage = new LoginPage("loginpage", driver);
		loginpage.enterUsername(uname);
		loginpage.enterPassword(pass);
		loginpage.clickLoginBtn();
		Thread.sleep(3000);
		InventoryPage inven= new InventoryPage("inventory", driver);
		
		Assert.assertTrue(inven.isProductTitleVisible(),"Test case failed due to Product title is not visible");
		Assert.assertEquals(getURL(), ConfigData.INVENTORY_URL);
	}
	
	
	
	@DataProvider(name="logindata")
	public Object[][] logindata()
	{
		 ReadData readData = new ReadData("loginpage");
		return readData.getData();
	}
	
	

}
