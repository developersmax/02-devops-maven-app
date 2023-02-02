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
   Feature:Customers

    Background: Below are the common steps for each scenario
    Given User Launch Chrome browser
    When User opens URL "https://admin-demo.nopcommerce.com/login?ReturnUrl=%2Fadmin%2F"
    And User enters Email as"" as Password as ""
    And Click on Login
    Then user can view Dashboard


 
  @sanity
  Scenario: Add a new Customer
    When User click on customer Menu
    And click on customer Menu Item
    And click on Add new button
    Then user can view add new customer page
    When user enter customer info 
    And click on save button
    Then User can view confirmation message "The new customer has been added successfully."
    And close browser
   
   @regression
    Scenario: Search customer by Emailid
    When User click on customer Menu
    And click on customer Menu Item
    And Enter customer Email
    When Click on search button
    Then User should found Email in the Search table
    And close browser
   
   
    @regression
    Scenario: Search customer by Name
    When User click on customer Menu
    And click on customer Menu Item
    And Enter customer FirstName
    And Enter customer LastName
    When Click on search button
    Then User should found Name in the Search table
    And close browser
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   


 
