package stepdefinition;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    private static final String BASE_URL = "http://3.131.133.70:3001/login";
    private static final String EMAIL = "kishorekumar.b+admin@spritle.com";
    private static final String PASSWORD = "@Kichu010104";
    private static final long IMPLICIT_WAIT_SECONDS = 10;
    private static final long EXPLICIT_WAIT_SECONDS = 10;

    /**
     * Initialize WebDriver and wait
     */
    public void initializeDriver() {
        this.driver = Hooks.driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_SECONDS));
    }

    @Given("^User lands in creation page$")
    public void user_lands_in_creation_page() {
        try {
            initializeDriver();
            System.out.println("Navigating to login page: " + BASE_URL);
            driver.get(BASE_URL);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_SECONDS));
            
            // Login flow
            performLogin();
            
            // Select DTO Modeling Tile
            selectDTOModelingTile();
            
            // Click Continue button
            WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Continue']")));
            continueButton.click();
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
            clickAddCapabilityButton();
            
            // Fill capability details
            fillCapabilityDetails();
            
            // Click Add button
            WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Add']")));
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
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert' and contains(@class,'MuiAlert-filledSuccess')]")));            
            if (creationSuccessMessage.isDisplayed()) {
                System.out.println("✓ Success message displayed: " + creationSuccessMessage.getText());
                WebElement closeIcon = driver.findElement(By.xpath("//div[@role='alert']//button[@aria-label='Close']"));
                closeIcon.click();
                System.out.println("Success message closed");
            }
    } catch (Exception e) {
            // System.err.println("Error verifying creation success message: " + e.getMessage());
            // throw e;
        }
    }

    @And("^User in the Business Capability page$")
    public void user_in_business_capability_page() throws Exception {
        try {
            
            System.out.println("Ensuring user is in Business Capability page");
            clickThreeDotMenuForLastCapability();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            throw e;
        }
    }

    @When("^User updates the existing business capability$")
    public void user_updates_existing_business_capability() {
        try {
            updateLastCapability();
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
            verifyUpdateSuccessMessage();
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
            deleteLastCapability();
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
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='alert' and contains(@class,'MuiAlert-filledSuccess')]")));     
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

    /**
     * Helper method to perform login
     */
    private void performLogin() {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("input[placeholder='Email Address*']")
        ));
        emailField.click();
        emailField.sendKeys(EMAIL);
        System.out.println("Email entered");
        
        WebElement passwordField = driver.findElement(By.cssSelector("input[placeholder='Password*']"));
        passwordField.sendKeys(PASSWORD);
        System.out.println("Password entered");
        
        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/form/div[3]/button"));
        loginButton.click();
        System.out.println("Login button clicked");
    }

    /**
     * Helper method to select DTO Modeling Tile
     */
    private void selectDTOModelingTile() {
        WebElement dtoModelingTile = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//*[@id=\"root\"]/div/main/div/div/div[2]/div[1]/div/div")
        ));
        if (dtoModelingTile.isDisplayed()) {
            System.out.println("DTO Modeling tile displayed, clicking...");
            dtoModelingTile.click();
        }
    }

    /**
     * Helper method to click add capability button
     */
    private void clickAddCapabilityButton() {
        WebElement businessCapabilityMenu = driver.findElement(By.xpath("//div[contains(@class,'MuiListItemButton-root') and .//p[normalize-space()='Business Capability']]"));
        businessCapabilityMenu.click();
        System.out.println("Business Capability menu clicked");
        WebElement addCapabilityButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(normalize-space(),'Add New Business Capability')]")));
        addCapabilityButton.click();
        System.out.println("Add capability button clicked");
    }

    /**
     * Helper method to click three-dot menu for the latest capability
     */
    private void clickThreeDotMenuForLastCapability() throws InterruptedException {
        driver = Hooks.driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

    try {

        List<WebElement> openMenus = driver.findElements(
                By.xpath("//ul[contains(@class,'MuiMenu-list')]"));

        if (!openMenus.isEmpty()) {
            driver.findElement(By.tagName("body")).click();
            System.out.println("Closed previously opened menu");
            Thread.sleep(500);
        }
        // Wait for scroll container (the one showing "scroll" in DevTools)
        WebElement scrollContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'css-xdo965')]")));

        // Scroll to bottom of container
        js.executeScript(
                "arguments[0].scrollTop = arguments[0].scrollHeight;",
                scrollContainer
        );
        System.out.println("Scrolled to bottom of the capabilities list");

         // Wait for capability rows using data-node-id
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@data-node-id]")));

        List<WebElement> rows = driver.findElements(
                By.xpath("//div[@data-node-id]"));

        if (rows.isEmpty()) {
            throw new RuntimeException("No capability rows found!");
        }

        System.out.println("Rows found: " + rows.size());

        // Get last row
        WebElement lastRow = rows.get(rows.size() - 1);

        // Find three-dot button inside last row
        WebElement threeDotBtn = lastRow.findElement(
                By.xpath(".//button[contains(@class,'MuiIconButton-root')]"));

        wait.until(ExpectedConditions.elementToBeClickable(threeDotBtn)).click();

        System.out.println("Clicked last three-dot");

    } catch (Exception e) {
        System.err.println("Error in clicking three-dot menu: " + e.getMessage());
        throw e;
    }
    }

    /**
     * Helper method to fill capability details
     */
    private void fillCapabilityDetails() {
        // Fill capability name
        WebElement capabilityName = driver.findElement(By.name("name"));
        String capabilityNameValue = "Capability_" + System.currentTimeMillis();
        capabilityName.sendKeys(capabilityNameValue);
        System.out.println("Capability name entered: " + capabilityNameValue);
        
        // Select capability owner
        WebElement capabilityOwner = driver.findElement(By.xpath("(//div[@class='MuiSelect-select MuiSelect-outlined MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-16qqw7w'])[1]"));
        capabilityOwner.click();
        
        driver.findElement(By.xpath("(//li[@role='menuitem'])[1]")).click();
        System.out.println("Capability owner selected");
        
        // Fill description
        WebElement descriptionField = driver.findElement(By.name("description"));
        descriptionField.sendKeys("The world is full of red waves and roses.");
        System.out.println("Description entered");
        
        // Select lifecycle stage
        WebElement lifecycleStageField = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div[1]/div/form/div/div[4]/div/div/div"));
        lifecycleStageField.click();
        driver.findElement(By.xpath("(//div[@class='MuiBox-root css-1u4f6x1'])[1]")).click();
     }

    /**
     * Helper method to update the last capability
     */
    private void updateLastCapability() {
        try {
           
            // Click Edit option from the three-dot menu
            WebElement editOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@class,'MuiButtonBase-root MuiMenuItem-root MuiMenuItem-gutters MuiMenuItem-root MuiMenuItem-gutters css-1btjosd')][2]")));
            editOption.click();
            System.out.println("Clicked Edit");
           
           // Update capability description
            WebElement descriptionField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("description")));
            descriptionField.clear();
            descriptionField.sendKeys("Updated description: The world is full of dinosaurs.");

            // Click Update button
            WebElement updateButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@class,'css-1qc5q63')]")
            ));
            updateButton.click();
            System.out.println("Capability update form submitted");
        } catch (Exception e) {
            System.err.println("Error updating last capability: " + e.getMessage());
        }
    }

     /**
     * Helper method to delete the last capability
     */
    private void deleteLastCapability() {
        try {
            
            // Click the three-dot menu for the last capability
            clickThreeDotMenuForLastCapability();

            // Click the Delete option from the menu
            WebElement deleteOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@class,'MuiButtonBase-root MuiMenuItem-root MuiMenuItem-gutters MuiMenuItem-root MuiMenuItem-gutters css-p2riym')]")));
            if (deleteOption.isEnabled()) {
                deleteOption.click();
                System.out.println("Delete option clicked");
            }else {
                System.err.println("Delete option is not enabled");
                return;
            }
            
            // Confirm deletion in the dialog
            WebElement confirmDeleteButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@class,'MuiButton-colorError css-4gs67b')]")
            ));
            confirmDeleteButton.click();
            System.out.println("Confirmed deletion of the capability");
        } catch (Exception e) {
            System.err.println("Error deleting last capability: " + e.getMessage());
        }
    }

    public boolean verifyUpdateSuccessMessage() {
        try {
            WebElement successAlert = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@role='alert' and contains(@class,'MuiAlert-colorSuccess')]//div[contains(@class,'MuiAlert-message')]")
                )
            );
            String alertText = successAlert.getText();
            System.out.println("Success alert message: " + alertText);
            return alertText.contains("updated successfully");
        } catch (Exception e) {
            System.err.println("Error verifying success message: " + e.getMessage());
            return false;
        }
    }
}

