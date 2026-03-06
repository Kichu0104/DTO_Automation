Feature: Metrics Management

  Background:
    Given I opened the Application
    When I enter valid username "karthikeyan.s@spritle.com" and password "Password@1"
    And I click on login button
    Then I am on the Dashboard
    When I click on Administrator and then click Continue
    And I navigate to Data Management and click on View MetaData Libraries
    And I select Metrics from the side menu


  @add
  Scenario: Add a new metric successfully
    When I click on Add Metric
    Then the Add Metric popup should be displayed
    When I enter metric name "Automation Metric 01"
    And I select metric owner "AUTOMATION"
    And I enter the metric definition "This metric measures portfolio performance"
    And I select the first supporting metric ID
    And I enter target "10"
    And I select target unit of measure "Dollars"
    And I select source "Import"
    And I select reporting frequency "Monthly"
    And I click on the Create Metric button
    Then the metric should be created successfully


  @view
  Scenario: View metric values for selected metric code and year
    When I click on View All Metrics Values
    Then the Metric Values page should be displayed
    When I select metric code "Met-1"
    And I select year "2025"
    Then the metric values grid should be displayed with months


  @search
  Scenario: Search metric using metric name
    When I search metric using name "Automation Metric 01"
    Then the search result should contain "Automation Metric 01"

#  @smoke
  @pagination
  Scenario: Verify metrics pagination works
    When I navigate to the next metrics page using pagination
    Then the metrics list should display the next set of records