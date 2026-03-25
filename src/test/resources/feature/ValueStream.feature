@ValueStream
Feature: Value Stream Management
    As a user
    I want to manage value streams
    So that I can organize and maintain my inventory

    Scenario: Create and edit a value stream
        Given I navigate to the value stream page
        When I create a new value stream with name "Value Stream"
      And I edit the value stream description to "Updated Description"
      And I add a value stream
        Then the value stream should be deleted successfully
