Feature: Organization Management

  Background:
    Given I opened the Application
    When I enter valid username "karthikeyan.s@spritle.com" and password "Password@1"
    And I click on login button
    Then dashboard should be visible
    When I click on Administrator and then click Continue
    And I navigate to Data Management and click on View MetaData Libraries
    And I select Organization from the side menu
#    Then Organization page should be displayed


  # ===================== CREATE ORGANIZATION =====================
  Scenario: Create Organization
    When I click on Add Organization
    And I enter the organization name "Automation Org75"
    And I enter the organization alias name "Org575"
    And I enter the organization description "Automation Org Description"
    And I select the organization lifecycle "Active"
    And I enter the organization location "Chennai"
    And I click on the Save Organization button
    Then the organization "Automation Org75" should be created successfully


  # ===================== ADD CHILD FROM LIST =====================
  Scenario: Add Child Organization from List
    When I click the add child icon of organization "Automation Org73"
    And I enter the child organization name "Child Org73"
    And I enter the child organization alias name "ChildOrg73"
    And I enter the child organization description "Child Org Description"
    And I select the child organization lifecycle "Active"
    And I enter the child organization location "Bangalore"
    And I click on the Save Child Organization button
    Then the child organization "Child Org73" should be created successfully


  # ===================== VIEW & ADD CHILD =====================
  Scenario: View Organization and add child organization
    When I click the view icon of organization "Automation Org73"
    Then the organization details page should be displayed
#    And the organization name should be "Automation Org73"

    When I click the view add child button in organization details page
    And I enter the view add child organization name "View Child Org73"
    And I enter the view add child organization alias name "ViewChildOrg73"
    And I enter the view add child organization description "Child Org Desc"
    And I select the view add child organization lifecycle "Active"
    And I enter the view add child organization location "Bangalore"
    And I click on the Save view add child Organization button
    Then the view add child organization "View Child Org73" should be created successfully


  # ===================== ADD ROLE =====================
  Scenario: Add Role
    When I click the role icon of organization "Automation Org75"
    And I click on the Add Role button to open popup
    And I enter the role name "Automation Admin75"
    And I enter the role lifecycle "Active"
    And I click on the Add Role submit button
    Then the role "Automation Admin75" should be created successfully

  @smoke
    # ===================== ADD USER =====================
  Scenario: Add User
    When I click the add user icon of organization "Automation Org75"
    And I click on the Add User button
    And I enter the username "vigneswariQA"
    And I select the first role in the list
    And I check the system user
    And I enter the email "vigneswari.s+dto@spritle.com"
    And I enter the password "Password@123"
    And I select the system role "Admin"
    And I click on the Add User submit button
    Then the user "vigneswariQA" should be created successfully
