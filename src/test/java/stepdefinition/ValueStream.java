package stepdefinition;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import Pages.ValueStreamPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step definitions for Value Stream feature
 * Contains all Given, When, Then steps for value stream management
 * scenarios
 */
public class ValueStream {

    private WebDriver driver;
    private ValueStreamPage valueStreamPage;
    private static final long EXPLICIT_WAIT_SECONDS = 10;

    /**
     * Initialize WebDriver and wait
     */
    public ValueStream() {
        this.driver = Hooks.driver;
        new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_SECONDS));
        this.valueStreamPage = new ValueStreamPage();
    }

    @Given("I navigate to the value stream page")
    public void i_navigate_to_value_stream_page() {
        System.out.println("Navigating to value stream page");
        valueStreamPage.selectDTOModelingTile();
        System.out.println("Successfully navigated to value stream page");
    }

    @When("I create a new value stream with name {string}")
    public void i_create_new_value_stream_with_name(String streamName) throws InterruptedException {
        valueStreamPage.clickValueStreamButton();
        valueStreamPage.createCategory();
    }

    @And("I edit the value stream description to {string}")
    public void i_edit_the_value_stream_description_to(String streamDescription) throws InterruptedException {
        valueStreamPage.threeDotOfLastValueStream();
        valueStreamPage.editValueStream();
    }

    @And("I add a value stream")
    public void i_add_a_value_stream() throws InterruptedException {
        valueStreamPage.threeDotOfLastValueStream();
        valueStreamPage.addValueStream();
    }

    @Then("the value stream should be deleted successfully")
    public void the_value_stream_should_be_deleted_successfully() throws InterruptedException {
        // Delete the Value Stream
        valueStreamPage.threeDotOfLastValueStream();
        valueStreamPage.deleteValueStream();

        // Delete the Category
        valueStreamPage.threeDotOfLastValueStream();
        valueStreamPage.deleteValueStream();
    }
}
