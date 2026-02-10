Feature: System Management

  Background:
    Given I opened the Application
    When I enter valid username "karthikeyan.s@spritle.com" and password "Password@1"
    And I click on login button
    Then dashboard should be visible
    And I navigate to System Management page

  @add
  Scenario: Add a new system successfully
    When I click on Add System
    Then the Add System popup should be displayed
    When I enter system full name "SYSTEM001"
    And I enter alias name "System One"
    And I enter the description "Test system description"
    And I select the system owner "User 11"
    And I select the system life cycle "Active"
    And I select the Shadow IT "Yes"
    And I click on the Create button
    Then the system should be created successfully

  @view
  Scenario: View system details
    When I click on the view icon
    Then system details should be displayed

  @search
  Scenario Outline: Search system using supported fields
    When I search system with keyword "<keyword>"
    Then search result should be "<result>"

    Examples:
      | keyword        | result            |
      | Sys 1          | matching          |
      | System Name 11 | matching          |
      | Alias 12       | matching          |
      | adc@test1      | No Records found  |

  @pagination
  Scenario: Verify pagination in System list
    When I navigate to the next page using pagination
    Then the system list should display the next set of records
    And the current page indicator should be updated
