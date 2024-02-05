
@tag
Feature: Error validation
  Check error validation using Cucumber


  @ErrorValidation
  Scenario Outline: Negative test for Error validation of Ecommerce website
    Given I landed on Ecommerce website
    When Logged in with username <username> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | username 					 | password 	|
      | jeffbeck@gmail.com |Welcome123	|
