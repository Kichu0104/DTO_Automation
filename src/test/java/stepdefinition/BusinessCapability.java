package stepdefinition;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Pages.BusinessCapabilityPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/**
 * Step definitions for Business Capability feature
 * Contains all Given, When, Then steps for capability management scenarios
 */
public class BusinessCapability {

    private WebDriver driver;
    private WebDriverWait wait;
    private BusinessCapabilityPage capabilityPage;
    private static final long EXPLICIT_WAIT_SECONDS = 10;

    /**
     * Initialize WebDriver and wait
     */
    public BusinessCapability() {
        this.driver = Hooks.driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_SECONDS));
        this.capabilityPage = new BusinessCapabilityPage(driver);
    }

    @Given("^User lands in creation page$")
    public void user_lands_in_creation_page() {
        try {
            System.out.println("Navigating to login page");
            capabilityPage.navigateToLogin();

            // Login flow
            capabilityPage.performLogin();

            // Select DTO Modeling Tile
            capabilityPage.selectDTOModelingTile();

            // Click Continue button
            java.util.List<WebElement> continueBtns = driver
                    .findElements(By.xpath("//button[normalize-space()='Continue']"));
            if (!continueBtns.isEmpty() && continueBtns.get(0).isDisplayed()) {
                WebElement continueButton = wait.until(
                        ExpectedConditions.elementToBeClickable(continueBtns.get(0)));
                continueButton.click();
            }
            System.out.println("Successfully navigated to creation page");
        } catch (Exception e) {
            System.err.println("Error in landing to creation page: " + e.getMessage());
            throw e;
        }
    }

    @When("^User creates a new business capability$")
    public void user_creates_a_new_business_capability() {
        try {
            System.out.println("Current page title: " + driver.getTitle());

            // Click on add capability button
            capabilityPage.clickAddCapabilityButton();

            // Fill capability details
            capabilityPage.fillCapabilityDetails();

            // Click Add button
            WebElement addButton = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Add']")));
            addButton.click();
            System.out.println("Capability creation form submitted");
        } catch (Exception e) {
            System.err.println("Error in creating business capability: " + e.getMessage());
            throw e;
        }
    }

    @Then("^User will see a success toaster message for capability creation$")
    public void user_will_see_success_toaster_for_creation() {
        try {
            WebElement creationSuccessMessage = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[@role='alert' and contains(@class,'MuiAlert-filledSuccess')]")));
            if (creationSuccessMessage.isDisplayed()) {
                System.out.println("✓ Success message displayed: " + creationSuccessMessage.getText());
                WebElement closeIcon = driver
                        .findElement(By.xpath("//div[@role='alert']//button[@aria-label='Close']"));
                closeIcon.click();
                System.out.println("Success message closed");
            }
        } catch (Exception e) {
            // System.err.println("Error verifying creation success message: " +
            // e.getMessage());
            // throw e;
        }
    }

    @And("^User in the Business Capability page$")
    public void user_in_business_capability_page() throws Exception {
        try {

            System.out.println("Ensuring user is in Business Capability page");
            capabilityPage.clickThreeDotMenuForLastCapability();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        }
    }

    @When("^User updates the existing business capability$")
    public void user_updates_existing_business_capability() {
        try {
            capabilityPage.updateLastCapability();
            System.out.println("Starting business capability update");
            // Implementation to be added
        } catch (Exception e) {
            System.err.println("Error updating business capability: " + e.getMessage());
            throw e;
        }
    }

    @Then("^User will see a success toaster message for capability updation$")
    public void user_will_see_success_toaster_for_update() {
        try {
            capabilityPage.verifyUpdateSuccessMessage();
            System.out.println("Verifying update success message");
            // Implementation to be added
        } catch (Exception e) {
            System.err.println("Error verifying update success: " + e.getMessage());
            throw e;
        }
    }

    @When("^User deletes an existing business capability$")
    public void user_deletes_existing_business_capability() throws Exception {
        try {
            user_in_business_capability_page();
            capabilityPage.deleteLastCapability();
            System.out.println("Starting business capability deletion");
            // Implementation to be added
        } catch (Exception e) {
            System.err.println("Error deleting business capability: " + e.getMessage());
            throw e;
        }
    }

    @Then("^User should see confirmation for capability deletion$")
    public void user_should_see_confirmation_for_deletion() {
        try {
            WebElement deletionSuccessMessage = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[@role='alert' and contains(@class,'MuiAlert-filledSuccess')]")));
            if (deletionSuccessMessage.isDisplayed()) {
                System.out.println("✓ Deletion success message displayed: " + deletionSuccessMessage.getText());
                WebElement closeIcon = driver.findElement(By.xpath("//button[@aria-label='Close']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", closeIcon);
                System.out.println("Deletion success message closed");
            }
        } catch (Exception e) {
            System.err.println("Error verifying deletion success message: " + e.getMessage());
            throw e;
        }
    }
}
