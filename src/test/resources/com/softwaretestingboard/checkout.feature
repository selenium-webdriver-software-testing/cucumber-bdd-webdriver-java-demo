@checkout
Feature: Checkout
In order to Place Orders
As Guest and Registered User
I want Checkout Feature

Background:
	Given User added products to shopping bag
	And User is in shopping bag page
	When User click continue checkout button
	Then Sign in page should load
	
Scenario: Checkout as Guest User

	When User entered email "qamate@outlook.com"
	And Click Begin Checkout
	Then Checkout page should be displayed
	Given User entered mandatory Shipping Details
	|	Key	|	value	|
	|	userflag	|	Guest	|
	|	firstname	|	Sheldon	|
	|	lastname	|	Nicolas	|
	|	address	|	600 Alexander Rd Ste 9	|
	|	city	|	Princeton	|
	|	state	|	New Jersey	|
	|	zip	|	08540	|
	|	country	|	United States	|
	|	phonenumber	|	(609) 661 8080	|
	|	email	|	qamate@outlook.com	|
	And Select Pay By Phone and Use my shipping address as my billing address options
	And Continue checkout without entering an account
	Then Check limited changes options should load
		
Scenario: Checkout as Registered User
	
	When User entered
	|	Key	|	value	|
	|	userflag	|	Registered	|
	|	username	|	qamate@outlook.com	|
	|	password	|	automation	|
	And Click on SignIn button
	Then Checkout page should be displayed
	Given User entered mandatory Shipping Details
	|	Key	|	value	|
	|	firstname	|	Sheldon	|
	|	lastname	|	Nicolas	|
	|	address	|	600 Alexander Rd Ste 9	|
	|	city	|	Princeton	|
	|	state	|	New Jersey	|
	|	zip	|	08540	|
	|	country	|	United States	|
	|	phonenumber	|	(609) 661 8080	|
	Then Check limited changes options should load
	

