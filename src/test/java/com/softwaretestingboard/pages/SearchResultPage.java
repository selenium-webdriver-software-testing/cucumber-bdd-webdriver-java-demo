package com.softwaretestingboard.pages;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultPage {
    public SearchResultPage(WebDriver driver) {
        if (!driver.getTitle().contains("BARNES & NOBLE")) {
            throw new IllegalStateException("This is not barnesandnoble.com" + driver.getCurrentUrl());
        }
    }
    
    //properties
    public static WebElement SearchSummary(WebDriver driver){
		return driver.findElement(By.id("search-summary-1")).findElement(By.tagName("div"));
	}
    
    //actions
    public WebDriver ValidateSearchResults(WebDriver driver, String search_keyword){
		
		try {	        	
			String SearchResults = SearchResultPage.SearchSummary(driver).getText().toLowerCase();
			Assert.assertTrue(SearchResults.contains(search_keyword.toLowerCase()));
        } catch (RuntimeException e) {
            //takeScreenShot(e, "Search Validation Error");
        }
        return driver;
    }
    
}