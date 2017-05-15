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
		return driver.findElement(By.xpath("//*[@id=\"skuSelection\"]/input[3]"));
	}

	public static WebElement getTheFirstItem(WebDriver driver){
		return driver.findElement(By.xpath("//*[@id=\"gridView\"]/li/ul[1]/li[1]/div[2]/a"));
	}
	
	public static WebElement getShoppingTotal(WebDriver driver){
		return driver.findElement(By.className("total-price"));
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
	public static WebElement getViewShoppingBagButton(WebDriver driver){
		return driver.findElement(By.id("viewShoppingBag"));
	}
	public ShoppingBagPage AddProductToShoppingBag() throws InterruptedException {
        try {
        	DepartmentPage.getTheFirstItem(driver).click();
        	DepartmentPage.getAddToCartButton(driver).click();
        	DepartmentPage.getViewShoppingBagButton(driver).click();
        	if (driver.getCurrentUrl().toLowerCase().contains("https://www.barnesandnoble.com/checkout/")){
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
