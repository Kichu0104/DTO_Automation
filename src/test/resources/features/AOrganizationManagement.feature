Feature: Organization Management

  Background:
    Given I opened the Application
    When I enter valid username "karthikeyan.s@spritle.com" and password "Password@1"
    And I click on login button
    Then dashboard should be visible

    Then I am on the Dashboard
    When I click on Administrator and then click Continue
    And I navigate to Data Management and click on View MetaData Libraries
    And I select Organization from the side menu

  # ===================== CREATE ORGANIZATION =====================
  Scenario: Create Organization
    When I click on Add Organization
    And I enter the organization name "Automation Org52"
    And I enter the organization alias name "Org502"
    And I enter the organization description "Automation Org Description"
    And I select the organization lifecycle "Active"
    And I enter the organization location "Chennai"
    And I click on the Save Organization button
    Then the organization "Automation Org52" should be created successfully

  # ===================== ADD CHILD FROM LIST =====================
  Scenario: Add Child Organization from List
    When I click the add child icon of organization "Automation Org52"
    And I enter the child organization name "Child Org03"
    And I enter the child organization alias name "ChildOrg03"
    And I enter the child organization description "Child Org Description"
    And I select the child organization lifecycle "Active"
    And I enter the child organization location "Bangalore"
    And I click on the Save Child Organization button
    Then the child organization "Child Org03" should be created successfully

@smoke
  # ===================== VIEW & ADD CHILD =====================
  Scenario: View Organization and add child organization
  When I click the view icon of organization "Automation Org52"
  Then the organization details page should be displayed
  And the organization name should be "Automation Org52"


    When I click the view add child button in organization details page
    And I enter the view child organization name "Child Org01"
    And I enter the view child organization alias name "ChildOrg01"
    And I enter the view child organization description "Child Org Desc"
    And I select the view child organization lifecycle "Active"
    And I enter the view child organization location "Bangalore"
    And I click on the Save view Child Organization button
    Then the view child organization "Child Org01" should be created successfully

  # ===================== ADD ROLE =====================
  Scenario: Add Role
    When I click the role icon of organization "Automation Org50"
    And I click on the Add Role button to open popup
    And I enter the role name "Automation Admin"
    And I enter the role lifecycle "Active"
    And I click on the Add Role submit button
    Then the role "Automation Admin" should be created successfully

  # ===================== ADD USER =====================
  Scenario: Add User
    When I click the add user icon of organization "Automation Org50"
    And I click on the Add User button
    And I enter the username "automation.user"
    And I select the role "Automation Admin"
    And I click on the Add User submit button
    Then the user "automation.user" should be created successfully

