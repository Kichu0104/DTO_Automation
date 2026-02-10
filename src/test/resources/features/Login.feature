# Login automation scenarios
Feature: Login functionality

  Scenario: Successful Login with valid credentials
    Given I opened the Application
    When I enter valid username "karthikeyan.s@spritle.com" and password "Password@1"
    And I click on login button
    Then dashboard should be visible
