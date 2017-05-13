package com.softwaretestingboard;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty", "html:target/cucumber","json:target/cucumber.json" })
public class SeqExec {
}
