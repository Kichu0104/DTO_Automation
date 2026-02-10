package stepdefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import pages.AOrganizationManagementPage;
import org.openqa.selenium.WebDriver;
import hooks.Hooks;

public class AOrganizationManagementSteps {

    WebDriver driver;
    AOrganizationManagementPage orgPage;

    public AOrganizationManagementSteps() {
        this.driver = Hooks.getDriver();
        orgPage = new AOrganizationManagementPage(driver);
    }

    // ===================== BACKGROUND =====================
    @Then("I am on the Dashboard") public void i_am_on_the_dashboard() { System.out.println("Dashboard page verified"); }
    @When("I click on Administrator and then click Continue") public void i_click_on_administrator_and_then_click_continue() {
        orgPage.clickAdministrator(); orgPage.clickContinue();
    }
    @When("I navigate to Data Management and click on View MetaData Libraries") public void i_navigate_to_data_management_and_click_on_view_metadata_libraries() {
        orgPage.navigateToDataManagement(); orgPage.clickViewMetadataLibraries();
    }
    @When("I select Organization from the side menu") public void i_select_organization_from_the_side_menu() {
        orgPage.selectOrganizationFromSideMenu();
    }

    // ===================== CREATE ORGANIZATION =====================
    @When("I click on Add Organization")
    public void i_click_on_add_organization() {
        orgPage.clickAddOrganization();
    }
    @When("I enter the organization name {string}")
    public void i_enter_the_organization_name(String name) {

        orgPage.enterOrganizationName(name);
    }
    @When("I enter the organization alias name {string}")
    public void i_enter_the_organization_alias_name(String alias) {

        orgPage.enterOrganizationAlias(alias);
    }
    @When("I enter the organization description {string}")
    public void i_enter_the_organization_description(String desc)
    {
        orgPage.enterOrganizationDescription(desc);
    }
    @When("I select the organization lifecycle {string}")
    public void i_select_the_organization_lifecycle(String lifecycle) {
        orgPage.selectOrganizationLifecycle(lifecycle);
    }
    @When("I enter the organization location {string}")
    public void i_enter_the_organization_location(String location) {

        orgPage.enterOrganizationLocation(location);
    }
    @When("I click on the Save Organization button")
    public void i_click_on_the_save_organization_button()
    {
        orgPage.clickSaveOrganization();
    }
    @Then("the organization {string} should be created successfully")
    public void the_organization_should_be_created_successfully(String orgName)
    {
        Assert.assertTrue(orgPage.isOrganizationCreatedToastDisplayed(orgName));
    }


    //
//    @When("I click the add child button in organization details page")
//    public void i_click_the_add_child_button_in_organization_details_page() {
//        orgPage.clickAddChildButtonInDetailsPage();
//    }
//===================== ADD CHILD =====================
    @When("I click the add child icon of organization {string}")
    public void i_click_the_add_child_icon_of_organization(String orgName) {
        orgPage.searchOrganization(orgName);
        orgPage.clickAddChildIcon(orgName);
    }
    @And("I enter the child organization name {string}")
    public void i_enter_the_child_organization_name(String childFullName) {
        orgPage.enterChildOrganizationName(childFullName);
    }


    @When("I enter the child organization alias name {string}")
    public void i_enter_the_child_organization_alias_name(String alias) {
        orgPage.enterChildOrganizationAlias(alias);
    }
    @When("I enter the child organization description {string}")
    public void i_enter_the_child_organization_description(String desc) {
        orgPage.enterChildOrganizationDescription(desc);
    }
    @When("I select the child organization lifecycle {string}")
    public void i_select_the_child_organization_lifecycle(String lifecycle) {
        // Directly call the page method
        orgPage.selectChildOrganizationLifecycle(lifecycle);

        // Optional: Log for debug
        System.out.println("Selected child organization lifecycle: " + lifecycle);
    }


    @When("I enter the child organization location {string}")
    public void i_enter_the_child_organization_location(String location) {
        orgPage.enterChildOrganizationLocation(location);
    }
    @When("I click on the Save Child Organization button")
    public void i_click_on_the_save_child_organization_button() {
        orgPage.clickSaveChildOrganization(); }

    @Then("the child organization {string} should be created successfully")
    public void the_child_organization_should_be_created_successfully(String childName) {
        Assert.assertTrue("Child organization not created!",
                orgPage.isChildOrganizationCreatedToastDisplayed(childName));
    }


    // ===================== VIEW ORGANIZATION =====================
    @When("I click the view icon of organization {string}")
    public void i_click_the_view_icon_of_organization(String orgName) {
        orgPage.clickViewIcon(orgName);
    }


    @Then("the organization details page should be displayed")
    public void the_organization_details_page_should_be_displayed()
    {
        Assert.assertTrue(orgPage.isOrganizationDetailsPageDisplayed());
    }
    @Then("the organization name should be {string}")
    public void the_organization_name_should_be(String orgName) {
        Assert.assertEquals(orgName, orgPage.getOrganizationNameFromDetails());
    }

    // ===================== ADD CHILD IN DETAILS PAGE =====================
    @When("I click the view add child button in organization details page")
    public void i_click_the_view_add_child_button_in_organization_details_page() {
        orgPage.clickAddChildInDetailsPage();  // reuse method from page object
    }

    @And("I enter the view child organization name {string}")
    public void i_enter_the_view_child_organization_name(String name) {
        orgPage.enterChildOrganizationName(name);  // same input method can be reused
    }

    @And("I enter the view child organization alias name {string}")
    public void i_enter_the_view_child_organization_alias_name(String alias) {
        orgPage.enterChildOrganizationAlias(alias);
    }

    @And("I enter the view child organization description {string}")
    public void i_enter_the_view_child_organization_description(String desc) {
        orgPage.enterChildOrganizationDescription(desc);
    }

    @And("I select the view child organization lifecycle {string}")
    public void i_select_the_view_child_organization_lifecycle(String lifecycle) {
        orgPage.selectChildOrganizationLifecycle(lifecycle);
    }

    @And("I enter the view child organization location {string}")
    public void i_enter_the_view_child_organization_location(String location) {
        orgPage.enterChildOrganizationLocation(location);
    }

    @And("I click on the Save view Child Organization button")
    public void i_click_on_the_save_view_child_organization_button() {
        orgPage.clickSaveChildOrganization();  // reuse save method
    }


    @Then("the view child organization {string} should be created successfully")
    public void the_view_child_organization_should_be_created_successfully(String name) {
        Assert.assertTrue("View Child organization not created!",
                orgPage.isChildOrganizationCreatedToastDisplayed(name));
    }




    // ===================== ADD ROLE =====================
    @When("I click the role icon of organization {string}")
    public void i_click_the_role_icon_of_organization(String orgName) {
        orgPage.clickRoleIcon(orgName);
    }

    @When("I click on the Add Role button to open popup")
    public void i_click_on_the_add_role_button_to_open_popup() {
        orgPage.clickAddRole();
    }

    @When("I enter the role name {string}")
    public void i_enter_the_role_name(String roleName) {
        orgPage.enterRoleName(roleName);
    }

    @When("I enter the role lifecycle {string}")
    public void i_enter_the_role_lifecycle(String lifecycle) {
        orgPage.selectRoleLifecycle(lifecycle);
    }

    @When("I click on the Add Role submit button")
    public void i_click_on_the_add_role_submit_button() {
        orgPage.submitRole();
    }

    @Then("the role {string} should be created successfully")
    public void the_role_should_be_created_successfully(String roleName) {
        Assert.assertTrue("Role not created!", orgPage.isRoleCreated(roleName));
    }

    // ===================== ADD USER =====================
    @When("I click the add user icon of organization {string}")
    public void i_click_the_add_user_icon_of_organization(String orgName) {
        orgPage.clickAddUserIcon(orgName);
    }

    @When("I click on the Add User button")
    public void i_click_on_the_add_user_button() {
        orgPage.clickAddUser();
    }

    @When("I enter the username {string}")
    public void i_enter_the_username(String username) {
        orgPage.enterUsername(username);
    }

    @When("I select the role {string}")
    public void i_select_the_role(String role) {
        orgPage.selectUserRole(role);
    }

    @When("I click on the Add User submit button")
    public void i_click_on_the_add_user_submit_button() {
        orgPage.submitUser();
    }

    @Then("the user {string} should be created successfully")
    public void the_user_should_be_created_successfully(String username) {
        Assert.assertTrue("User not created!", orgPage.isUserCreated(username));
    }

}
