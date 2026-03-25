@ProcessInventory
Feature: Process Inventory Management
    As a user
    I want to manage process areas and processes
    So that I can organize and maintain my inventory

    Scenario: Create and edit a process area
        Given I navigate to the process inventory page
        When I create a new process area with name "Inventory Area"
      And I edit the process description to "Updated Description"
        Then the process should be deleted successfully