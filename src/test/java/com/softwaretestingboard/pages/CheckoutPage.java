package com.softwaretestingboard.pages;

import java.io.File;
import java.io.IOException;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CheckoutPage {
	private WebDriver driver;
	private boolean isCheckoutPage = false;
	private String userflag= "";
	protected WebDriverWait wait; 
	
	
	public CheckoutPage(WebDriver driver) {
		try {
			this.driver = driver;
			if (driver.getCurrentUrl().toLowerCase().contains("https://cart4.barnesandnoble.com") ){
				isCheckoutPage = true;
			}
			this.wait = new WebDriverWait(driver,10);
        } catch (RuntimeException e) {
        	throw new IllegalStateException("CheckoutPage: This is not barnesandnoble.com" + driver.getCurrentUrl());
        }
		
	}
	
	//PROPERTIES
	public boolean isCheckoutPage(){
		return isCheckoutPage;
	}
	
	public static WebElement getEnterShippingAddress(WebDriver driver){
		return driver.findElement(By.id("opShippingAddress"));
	}
	
	public static WebElement getAddressChangeButton(WebDriver driver){
		List<WebElement> panels = driver.findElements(By.className("panelMini_Header"));
		if(panels.size() >= 0) {
			return panels.get(0).findElement(By.tagName("input"));
		}
		return null;
	}
	
	public static WebElement getShipToNewAddress(WebDriver driver){
		return driver.findElement(By.linkText("Ship to a New Address"));
	}
	
	public static WebElement getFirstName(WebDriver driver){
		return driver.findElement(By.id("id4"));
	}
	public static WebElement getLastName(WebDriver driver){
		return driver.findElement(By.id("id5"));
	}
	public static WebElement getAddress(WebDriver driver){
		return driver.findElement(By.id("id6"));
	}
	public static WebElement getCity(WebDriver driver){
		return driver.findElement(By.id("id7"));
	}
	public static WebElement getState(WebDriver driver){
		return driver.findElement(By.id("id8"));
	}
	public static WebElement getShippingAdddressDiv(WebDriver driver){
		return driver.findElement(By.id("opShippingAddress"));
	}
	public static List<WebElement> getLis(WebDriver driver){
		return getShippingAdddressDiv(driver).findElements(By.tagName("li"));
	}
	public static WebElement getZip(WebDriver driver){
		List<WebElement> lis = getLis(driver);
		if(lis.size() >= 0) return lis.get(8).findElement(By.tagName("input"));
		return null;
	}
	public static WebElement getPhoneNumber(WebDriver driver){
		List<WebElement> lis = getLis(driver);
		return lis.get(10).findElement(By.tagName("input"));
	}
	public static WebElement getSameAddressCheckBox(WebDriver driver){
		List<WebElement> lis = getLis(driver);
		if(lis.size() >= 0) return lis.get(11).findElement(By.tagName("input"));
		return null;
	}
	public static WebElement getCounry(WebDriver driver){
		return driver.findElement(By.id("id9"));
	}
	public static WebElement getContinueButton(WebDriver driver){
		return driver.findElement(By.className("continue"));
	}
	
	public static WebElement getPayByPhoneMethod(WebDriver driver){
		return driver.findElements(By.className("selectPayMethods")).get(2);
	}
	
	public static WebElement getbillingAddress(WebDriver driver){
		return driver.findElement(By.id("billingAddress"));
	}
	public static List<WebElement> getAddresses(WebDriver driver){
		return getbillingAddress(driver).findElements(By.className("address"));
	}
	
	public static WebElement emailAddressConfirm(WebDriver driver){
		return driver.findElement(By.name("emailAddressConfirm"));
	}
	
	public static WebElement bnMemberShipNo(WebDriver driver){
		return driver.findElement(By.name("advantageNumber"));
	}
	
	public static WebElement getcontinueWithout(WebDriver driver){
		return driver.findElement(By.id("continueWithout"));
	}
	public static WebElement getContiuneWithoutButton(WebDriver driver){
		WebElement byName = getcontinueWithout(driver).findElement(By.name("UIAction_skipCreateAccount"));
		WebElement byTagName = getcontinueWithout(driver).findElement(By.tagName("input"));
		if(byTagName !=null) return byTagName;
		return byName;
	}
	public static WebElement getopReviewInfo_Header(WebDriver driver){
		return driver.findElement(By.id("opReviewInfo_Header"));
	}
	public static WebElement getLimitedChangesLink(WebDriver driver){
		return getopReviewInfo_Header(driver).findElement(By.tagName("a"));
	}
	public static WebElement getPopup(WebDriver driver){
		return driver.findElement(By.className("overlay"));
	}
	
	
		//ACTIONS
		public void EditShippingAddress(MyHashMap myMap) throws InterruptedException {
		try {
			
			userflag = myMap.getValue("userflag").toString();
			
			if (userflag.equalsIgnoreCase("Registered")) {
				wait.until(elementIsDiaplayed(CheckoutPage.getEnterShippingAddress(driver)));
				if (CheckoutPage.getEnterShippingAddress(driver).isDisplayed()) {
					CheckoutPage.getAddressChangeButton(driver).click();
					CheckoutPage.getShipToNewAddress(driver).click();
				}
			}
			
			CheckoutPage.getFirstName(driver).clear();
			CheckoutPage.getFirstName(driver).sendKeys(myMap.getValue("firstname"));
			CheckoutPage.getLastName(driver).clear();
			CheckoutPage.getLastName(driver).sendKeys(myMap.getValue("lastname"));
			CheckoutPage.getAddress(driver).clear();
			CheckoutPage.getAddress(driver).sendKeys(myMap.getValue("address"));
			CheckoutPage.getCity(driver).clear();
			CheckoutPage.getCity(driver).sendKeys(myMap.getValue("city"));
			{
				Select states = new Select(CheckoutPage.getState(driver));
				if(!states.getFirstSelectedOption().getText().toLowerCase().equals(myMap.getValue("state").toLowerCase()))
				states.selectByVisibleText(myMap.getValue("state"));
			}
			CheckoutPage.getZip(driver).clear();
			CheckoutPage.getZip(driver).sendKeys(myMap.getValue("zip"));
			{
				if(!myMap.getValue("Country").equals("United State"))
				{
				Select countries = new Select(CheckoutPage.getCounry(driver));
				if(!countries.getFirstSelectedOption().getText().toLowerCase().equals(myMap.getValue("country").toLowerCase()))
				countries.selectByVisibleText(myMap.getValue("country"));
				}
			}
			CheckoutPage.getPhoneNumber(driver).clear();
			CheckoutPage.getPhoneNumber(driver).sendKeys(myMap.getValue("phonenumber"));
			CheckoutPage.getSameAddressCheckBox(driver).click();
			
			if (userflag.equalsIgnoreCase("Guest")) {	
				CheckoutPage.emailAddressConfirm(driver).clear();
				CheckoutPage.emailAddressConfirm(driver).sendKeys(myMap.getValue("email"));
				
			}
			CheckoutPage.getContinueButton(driver).click();
        	
        } catch (RuntimeException e) {
            takeScreenShot(e, "searchError");
            //System.out.println("Error: continueCheckout");
        	//e.printStackTrace();
        }
	}
		
		public void continueCheckout() {
	        try {
	        	
	        	{
					//wait.until(elementIsDiaplayed(CheckoutPage.getContinueButton(driver)));
				}
	        	//this.wait = new WebDriverWait(driver,10);
	        	CheckoutPage.bnMemberShipNo(driver).click();
	        	CheckoutPage.bnMemberShipNo(driver).clear();
				CheckoutPage.bnMemberShipNo(driver).sendKeys("12345678");
				CheckoutPage.bnMemberShipNo(driver).clear();
	        	CheckoutPage.getContinueButton(driver).click();
	        	
	        } catch (RuntimeException e) {
	            takeScreenShot(e, "searchError");
	        	//System.out.println("Error: continueCheckout");
	        	//e.printStackTrace();
	        }
		}
		
		public void payByPhoneAndBilling() {
	        try {
	        	
	        	CheckoutPage.getPayByPhoneMethod(driver).click();
	        	{
					List<WebElement> addresses = CheckoutPage.getAddresses(driver);
					do{
						addresses = CheckoutPage.getAddresses(driver);
						if(addresses == null) continue;
						wait.until(elementIsDiaplayed(addresses.get(0)));
					}while(addresses == null);
				}
	        	CheckoutPage.getContinueButton(driver).click();
	        	
	        } catch (RuntimeException e) {
	            takeScreenShot(e, "searchError");
	            //System.out.println("Error: payByPhoneAndBilling");
	            //e.printStackTrace();
	        }
		}
		
		public void continueWithoutAccount() {
	        try {
	        	
	        	wait.until(elementIsDiaplayed(CheckoutPage.getContiuneWithoutButton(driver)));
				CheckoutPage.getContiuneWithoutButton(driver).click();
	        	
	        } catch (RuntimeException e) {
	            takeScreenShot(e, "searchError");
	            //System.out.println("Error: continueWithoutAccount");
	            //e.printStackTrace();
	        }
		}
		

		public void checkLimitedChangesOptions() {
	        try {
	        	
	        	wait.until(elementIsDiaplayed(CheckoutPage.getLimitedChangesLink(driver)));
	    		CheckoutPage.getLimitedChangesLink(driver).click();
	    		wait.until(elementIsDiaplayed(CheckoutPage.getPopup(driver)));
	    		//Assert.assertTrue(BNCheckoutPage.getPopup(driver).isDisplayed());
	        	
	        } catch (RuntimeException e) {
	            takeScreenShot(e, "searchError");
	            //System.out.println("Error: continueWithoutAccount");
	            //e.printStackTrace();
	        }
		}
		
		
		private void takeScreenShot(RuntimeException e, String fileName) {
	        File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        try {
	            FileUtils.copyFile(screenShot, new File(fileName + ".png"));
	        } catch (IOException ioe) {
	            throw new RuntimeException(ioe.getMessage(), ioe);
	        }
	        throw e;
	    }
		
		public ExpectedCondition<WebElement> elementIsDiaplayed(final WebElement element){
			try{
				return new ExpectedCondition<WebElement>(){
					public WebElement apply(WebDriver driver) {
						if(!element.isDisplayed()) return null;
						return element;
					}
				};
				}catch(Exception e){
					e.printStackTrace();
				}
			return null;
		}
}
