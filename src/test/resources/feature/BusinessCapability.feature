@businessCapability
Feature: Business Capability

@Create
Scenario: Business Capability creation

Given User lands in creation page
When User creates a new business capability
Then User will see a success toaster message for capability creation

Scenario: Business Capability updation

Given User in the Business Capability page
When User updates the existing business capability
Then User will see a success toaster message for capability updation

# Scenario: Child business capability creation

# Given User in the Business Capability page
# When User creates a child business capability
# Then User will see a success toaster message for child capability creation

# @delete
# Scenario: Business Capability deletion

# Given User in the business capability page
# When User deletes an existing business capability
# And User updates the existing business capability