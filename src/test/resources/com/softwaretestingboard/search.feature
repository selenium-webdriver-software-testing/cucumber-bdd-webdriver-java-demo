@search
Feature: Search
In order to Find Right Products
As a Guest User
I want Search Functionality


Scenario: Basic Search

    Given User is in home page
    When Enter "Acceptance Testing" in search field
    And Click Search  
    Then Search results for entered keyword is displayed

 
Scenario Outline: Department Search

    Given User is in home page
    And Select a <department> to search
    When Enter <keyword> in search field
    And Click Search  
    Then Search results for entered keyword is displayed
    
	Examples:
	|department	|keyword	|
	|"Books"		|"Agile"	|
	|"DVDs"		|"Batman"	|