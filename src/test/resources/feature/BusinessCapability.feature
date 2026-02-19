@businessCapability
Feature: Business Capability
  As a DTO Modeling user
  I want to manage business capabilities
  So that I can define and organize business capabilities effectively

  Background:
    # This runs before each scenario
    # Common setup can be added here if needed

  @Create @Smoke
  Scenario: Business Capability creation
    Given User lands in creation page
    When User creates a new business capability
    Then User will see a success toaster message for capability creation

  @Update @Smoke
  Scenario: Business Capability updation
    And User in the Business Capability page
    When User updates the existing business capability
    Then User will see a success toaster message for capability updation

  # Commented out scenarios for future implementation
  # @ChildCapability
  # Scenario: Child business capability creation
  #   Given User in the Business Capability page
  #   When User creates a child business capability
  #   Then User will see a success toaster message for child capability creation

  @Delete @Regression
   Scenario: Business Capability deletion
     And User in the Business Capability page
     When User deletes an existing business capability
     Then User should see confirmation for capability deletion