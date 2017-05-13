package com.softwaretestingboard.step_definations;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.softwaretestingboard.pages.*;

public class SearchStepDefinitions {
	
    private WebDriver driver;
    private HomePage hp;
    private String entered_search_keyword;
    private SearchResultPage searchResultPage;
    @Before
    public void prepare() throws MalformedURLException {
    	
    	String url = "";
    	 
    	/* REMOTE DRIVER CONFIG FOR RUNNING TESTS IN LOCAL GRID AND JENKINS */
    	url = "http://localhost:4444/wd/hub";
        DesiredCapabilities capabillities = DesiredCapabilities.chrome();
        capabillities.setCapability("platform", Platform.WINDOWS);
        capabillities.setCapability("version", "58");
    	this.driver = new RemoteWebDriver(new URL(url), capabillities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	
    	/// REMOTE DRIVER CONFIG FOR RUNNING TESTS IN SAUCE LABS 
    	//String url = "http://mayur_softcrylic:1034911c-db71-4c8e-8e12-7831abf6adf7@ondemand.saucelabs.com:80/wd/hub";
    	//capabillities.setCapability("platform", Platform.MAC);
    	//capabillities.setCapability("name", "Running via Jenkins. Testing on Sauce");
    	//capabillities.setCapability("record-video", false);
    	//if(url.contains("saucelabs")) 
        // capabillities.setCapability("version", "11");
    	// else
    	// capabillities.setCapability("version", "12.0");	
    }

    @After
    public void cleanUp() {
        driver.close();
        try{
        	if(driver!=null) driver.quit();
        }catch(Exception ignore){}
    }

    
    // basic search feature
    @Given("^User is in home page$")
    public void prepareHomePage() {
       hp = new HomePage(driver);
    }

    @When("^Enter \"([^\"]*)\" in search field$")
    public void search1(String search_keyword) {
    	entered_search_keyword = search_keyword;
    	searchResultPage = hp.Search(search_keyword);
    }
    
    @Then("^Search results for entered keyword is displayed$")
    public void assertTheSearchResult1() throws Throwable {
    	driver = searchResultPage.ValidateSearchResults(driver, entered_search_keyword);
    }
    
    @Given("^Select a \"([^\"]*)\" to search$")
    public void Department_Selection(String department) throws Throwable {
    	hp.SelectADepartment(department);
    }
    
    @When("^Click Search$")
    public void Click_Go_button() throws Throwable {
      //implementation is in hp.Search(search_keyword);
    }
    
}