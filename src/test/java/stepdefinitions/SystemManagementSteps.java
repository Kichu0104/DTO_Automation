package stepdefinitions;

import hooks.Hooks;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.SystemManagementPage;

public class SystemManagementSteps {

    WebDriver driver;
    SystemManagementPage systemPage;
    String searchedKeyword;
    int previousPage;
    String oldFirstRowText;

    @Given("I navigate to System Management page")
    public void navigate_to_system_management() {
        driver = Hooks.getDriver();
        systemPage = new SystemManagementPage(driver);

        systemPage.clickAdministrator();
        systemPage.clickContinue();
        systemPage.clickDataManagement();
        systemPage.clickViewMetadataLibraries();

        Assert.assertTrue(systemPage.isSystemPageDisplayed());
    }

    /* ---------- ADD SYSTEM ---------- */

    @When("I click on Add System")
    public void click_add_system() {
        systemPage.clickAddSystem();
    }

    @Then("the Add System popup should be displayed")
    public void verify_add_popup() {
        Assert.assertTrue(systemPage.isAddSystemPopupVisible());
    }

    @When("I enter system full name {string}")
    public void enter_full_name(String name) {
        systemPage.enterFullName(name);
    }

    @And("I enter alias name {string}")
    public void enter_alias(String alias) {
        systemPage.enterAliasName(alias);
    }

    @And("I enter the description {string}")
    public void enter_description(String desc) {
        systemPage.enterDescription(desc);
    }

    @And("I select the system owner {string}")
    public void select_owner(String owner) {
        systemPage.selectSystemOwner(owner);
    }

    @And("I select the system life cycle {string}")
    public void select_lifecycle(String lifecycle) {
        systemPage.selectLifeCycle(lifecycle);
    }

    @And("I select the Shadow IT {string}")
    public void select_shadow_it(String value) {
        systemPage.selectShadowITValue(value);
    }

    @And("I click on the Create button")
    public void click_create() {
        systemPage.clickCreate();
    }

    @Then("the system should be created successfully")
    public void verify_created() {
        systemPage.verifySuccessMessage();
    }

    /* ---------- VIEW SYSTEM ---------- */

    @When("I click on the view icon")
    public void click_view_icon() {
        systemPage.clickFirstViewIcon();
    }

    @Then("system details should be displayed")
    public void verify_system_details() {
        Assert.assertTrue(systemPage.isSystemDetailsPageDisplayed());
    }

    /* ---------- SEARCH ---------- */

    @When("I search system with keyword {string}")
    public void search_system(String keyword) {
        searchedKeyword = keyword;
        systemPage.searchSystem(keyword);
    }

    @Then("search result should be {string}")
    public void verify_search_result(String result) {
        if (result.equalsIgnoreCase("matching")) {
            Assert.assertTrue(systemPage.areSearchResultsMatching(searchedKeyword));
        } else {
            Assert.assertTrue(systemPage.isNoResultDisplayed());
        }
    }

    /* ---------- PAGINATION ---------- */

    @When("I navigate to the next page using pagination")
    public void go_next_page() {
        oldFirstRowText = systemPage.getFirstRowText();
        previousPage = systemPage.getCurrentPageNumber();
        systemPage.clickNextPage();
        systemPage.waitForTableDataChange(oldFirstRowText);
    }

    @Then("the system list should display the next set of records")
    public void verify_next_page_records() {
        Assert.assertTrue(systemPage.getRowCount() > 0);
    }

    @Then("the current page indicator should be updated")
    public void verify_page_indicator() {
        systemPage.verifyCurrentPageUpdated(previousPage);
    }
}

