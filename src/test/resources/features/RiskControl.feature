Feature: Risc Control Management

  Background:
    Given I opened the Application
    When I enter valid username "karthikeyan.s@spritle.com" and password "Password@1"
    And I click on login button
    Then I am on the Dashboard
    When I click on Administrator and then click Continue
    And I navigate to Data Management and click on View MetaData Libraries
    And I select risk controls from the side menu

@viewdetails
Scenario: View risk details and related monitors and metrics
When I click on the view icon of the risks and controls table
Then the Risk Control Details popup should be displayed
When I click on the View Controls button
Then the Controls should be displayed
When I click on the Monitor icon of the first row
Then the Monitors should be displayed
When I click on the Metric icon of the first row
Then the Metrics should be displayed

@smoke
@viewcontrols
Scenario: View controls details and related monitors and metrics
When I click on the control view icon of the risk and controls table
Then the Control Details popup should be displayed
When I click on the Monitor icon of the first row
Then the Monitors should be displayed
When I click on the Metric icon of the first row
Then the Metrics should be displayed