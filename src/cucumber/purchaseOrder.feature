#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Purchase the order from Ecommerce app
  I want to use this template for my feature file
  
  Background: I landed on Ecommerce Page

  @tag1
  Scenario: Title of your scenario
    Given I want to write a step with precondition
    And some other precondition
    When I complete action
    And some other action
    And yet another action
    Then I validate the outcomes
    And check more outcomes

  @tag2
  Scenario Outline: Positive test of submitting order
    Given Logged in with username <username> and password <password>
    When I add the product <productName> to the cart.
    And Checkout <productName> and submit the order.
    Then "THANKYOU FOR THE ORDER." message is displayed

    Examples: 
      | username          | password      | productName  |
      | supriya@test1.com |     Welcome1@ | ZARA COAT 3 |
      
