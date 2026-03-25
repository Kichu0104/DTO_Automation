package stepdefinition;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import Pages.ProcessInventoryPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step definitions for Process Inventory feature
 * Contains all Given, When, Then steps for process area and process management
 * scenarios
 */
public class ProcessInventory {

    private WebDriver driver;
    private ProcessInventoryPage inventoryPage;
    private static final long EXPLICIT_WAIT_SECONDS = 10;

    /**
     * Initialize WebDriver and wait
     */
    public ProcessInventory() {
        this.driver = Hooks.driver;
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_SECONDS));
        this.inventoryPage = new ProcessInventoryPage();
    }

    @Given("I navigate to the process inventory page")
    public void i_navigate_to_process_inventory_page() {
        System.out.println("Navigating to process inventory page");
        inventoryPage.selectDTOModelingTile();
        System.out.println("Successfully navigated to process inventory page");
    }

    @When("I create a new process area with name {string}")
    public void i_create_new_process_area_with_name(String areaName) throws InterruptedException {
        inventoryPage.clickThreeDotForFirstProcessArea();
        inventoryPage.clickAddProcessAreaButton();
        inventoryPage.createProcessArea();
    }

    @And("I edit the process description to {string}")
    public void i_edit_the_process_description_to(String processDescription) throws InterruptedException {
        inventoryPage.threeDotOfLastProcess();
        inventoryPage.editProcessArea();
    }

    @Then("the process should be deleted successfully")
    public void the_process_should_be_deleted_successfully() throws InterruptedException {
        inventoryPage.threeDotOfLastProcess();
        inventoryPage.deleteProcessArea();
    }
}