package com.softwaretestingboard.pages;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {
	
	private WebDriver driver;
	private String pageHeader;
	private boolean isSignInBagPage = false;
	protected WebDriverWait wait; 
	
	public SignInPage(WebDriver driver) {
        this.driver = driver;
        try {
			this.driver = driver;
			this.wait = new WebDriverWait(driver,10);
			pageHeader = SignInPage.PageHeader(driver).findElement(By.tagName("p")).toString();
			if (pageHeader.toLowerCase().contains("Sign in or Proceed as Guest")){
				isSignInBagPage = true;
			}
        } catch (RuntimeException e) {
        	throw new IllegalStateException("SignInPage: This is not barnesandnoble.com" + driver.getCurrentUrl());
        }
    }
	
	//Properties
	public static WebElement PageHeader(WebDriver driver){
		return driver.findElement(By.id("opSignInHeader"));
	}
	public boolean isSignInPage(){
		return isSignInBagPage;
	}
	public static WebElement enterEmailAddress(WebDriver driver){
		return driver.findElement(By.id("id1"));
	}
	public static WebElement enterPassword(WebDriver driver){
		return driver.findElement(By.name("acctPassword"));
	}
	public static WebElement SignInButton(WebDriver driver){
		return driver.findElement(By.name("SignInClicked"));
	}
	
	public static WebElement enterGuestEmail(WebDriver driver){
		return driver.findElement(By.id("id2"));
	}
	
	public static WebElement guestCheckoutClicked(WebDriver driver){
		return driver.findElement(By.name("GuestCheckoutClicked"));
	}
	
	public static WebElement getContinueButton(WebDriver driver){
		return driver.findElement(By.className("continue"));
	}
	
	//actions
	public void EnterEmail(String email) throws InterruptedException {
        try {
        	SignInPage.enterGuestEmail(driver).clear();
        	SignInPage.enterGuestEmail(driver).sendKeys(email);
        } catch (RuntimeException e) {
            takeScreenShot(e, "searchError");
        }
    }
	
	public CheckoutPage clickBeginGuestCheckout() throws InterruptedException {
        try {
        	SignInPage.guestCheckoutClicked(driver).click();
        	if (driver.getCurrentUrl().toLowerCase().contains("https://cart4.barnesandnoble.com")){
        		try{
        			// need attention 
        			//DepartmentPage.getShoppingTotal(driver).isDisplayed();
        			wait.until(elementIsDiaplayed(CheckoutPage.getContinueButton(driver)));
        			System.out.println("Guest Entered In Checkout Page Successfully");
            	}catch(NoSuchElementException e){
            		System.out.println("No products added to shopping bag");
            	}
        	}	
        } catch (RuntimeException e) {
            //takeScreenShot(e, "searchError");
            System.out.println("Error: continueCheckout");
        	e.printStackTrace();
        }
        return new CheckoutPage(driver);
    }
	
	public CheckoutPage SignIn(String username, String password) throws InterruptedException {
        try {
        	SignInPage.enterEmailAddress(driver).clear();
        	SignInPage.enterEmailAddress(driver).sendKeys(username);
        	SignInPage.enterPassword(driver).clear();
        	SignInPage.enterPassword(driver).sendKeys(password);
        	SignInPage.SignInButton(driver).click();
        	if (driver.getCurrentUrl().toLowerCase().contains("https://cart4.barnesandnoble.com")){
        		try{
        			//DepartmentPage.getShoppingTotal(driver).isDisplayed();
        			//wait.until(elementIsDiaplayed(CheckoutPage.getEnterShippingAddress(driver)));
        			System.out.println("User Signed In To Checkout Page Successfully");
            	}catch(NoSuchElementException e){
            		System.out.println("No products added to shopping bag");
            	}
        	}	
        } catch (RuntimeException e) {
            takeScreenShot(e, "searchError");
        }
		return new CheckoutPage(driver);
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
