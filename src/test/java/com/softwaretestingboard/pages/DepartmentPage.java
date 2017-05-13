package com.softwaretestingboard.pages;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;


public class DepartmentPage {
	private WebDriver driver;
	
	public DepartmentPage(WebDriver driver) {
        this.driver = driver;
    }
	
	//PROPERTIES
	public static WebElement getAddToCartButton(WebDriver driver){
		return driver.findElement(By.className("btn-add-to-cart"));
	}
	
	public static WebElement getShoppingTotal(WebDriver driver){
		return driver.findElement(By.className("estTotals"));
	}
	
	//ACTIONS
	public WebDriver SelectDeptSubCategory(WebDriver driver,String leftNav_input) throws InterruptedException {
        try {
        	try{
        		driver.findElement(By.linkText(leftNav_input)).click();
        	}catch(NoSuchElementException e){
        		System.out.println("Department subcategory doesn't exsist");
        	}
        } catch (RuntimeException e) {
            takeScreenShot(e, "searchError");
        }
		return driver;
    }
	
	public ShoppingBagPage AddProductToShoppingBag() throws InterruptedException {
        try {
        	DepartmentPage.getAddToCartButton(driver).click();
        	if (driver.getCurrentUrl().toLowerCase().contains("http://cart4.barnesandnoble.com")){
        		try{
        			DepartmentPage.getShoppingTotal(driver).isDisplayed();
        			System.out.println("Products added to shopping bag");
            	}catch(NoSuchElementException e){
            		System.out.println("No products added to shopping bag");
            	}
        	}
        			
        } catch (RuntimeException e) {
            takeScreenShot(e, "searchError");
        }
		return new ShoppingBagPage(driver);
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

}
