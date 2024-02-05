@tag
Feature: Purchase the order
  I want to purchase order from Ecommerce website

Background:
Given I landed on Ecommerce website

  @Regression
  Scenario Outline: Positive Test of Submitting the Order
    Given Logged in with username <username> and password <password>
    When I add product <productName> to cart
    And Checkout <productName> and submit order
    Then "THANKYOU FOR THE ORDER." is displayed on confirmation page
    Examples: 
      | username 					 | password 	| productName |
      | jeffbeck@gmail.com |Welcome@123 | ZARA COAT 3	|