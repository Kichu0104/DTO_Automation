Feature: Product Management

  Background:
    Given I opened the Application
    When I enter valid username "karthikeyan.s@spritle.com" and password "Password@1"
    And I click on login button
    Then dashboard should be visible
    And I am on the Product Management page

  Scenario: Add a new product successfully
    When I click on Add Product
    Then the Add Product popup should be displayed
    When I enter product name "Automate product-3"
    And I enter product code "P001"
    And I enter product description "Test product description"
    And I select the product type "Product"
    And I select the product parent category "Credit Card"
    And I select the product owner "User 11"
    And I enter the release version "v01"
    And I select the product lifecycle stage "Active"
    And I select the target audience "Internal Employees"
    And I click on the Create product button
    Then the product "Automate product-3" should be created successfully

  Scenario: View product details
    When I click on the view icon for the first product
    Then the product details should be displayed

  Scenario: Add a child product
    When I expand the parent product with ID "ID 1"
    And I click on the view icon for product "PRODUCT001"
    And I click on Add Child Product
    Then the Add Child Product popup should be displayed
    When I enter child product full name "PROD-child-001"
    And I enter child alias "P-child001"
    And I enter child product description "Test child product description"
    And I select the child product type "Product"
    And I select the child product owner "User 11"
    And I enter the child release version "v01"
    And I select the child product lifecycle stage "Active"
    And I select the child target audience "Internal Employees"
    And I click on Save product
    Then the child product "PROD-child-001" should be created successfully
