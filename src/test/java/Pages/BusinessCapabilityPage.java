package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BusinessCapabilityPage {

    public WebDriver driver;
    public WebDriverWait wait;
    public static final String BASE_URL = "https://development.insighttwin.com/";
    public static final String EMAIL = "kishorekumar.b+admin@spritle.com";
    public static final String PASSWORD = "@Kichu010104";
    public static final long IMPLICIT_WAIT_SECONDS = 10;
    public static final long EXPLICIT_WAIT_SECONDS = 10;

    public BusinessCapabilityPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_SECONDS));
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_SECONDS));
    }

    public WebDriverWait getWait() {
        return this.wait;
    }

    public void navigateToLogin() {
        driver.get(BASE_URL);
    }

    public void performLogin() {
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.cssSelector("input[placeholder='Email Address*']")
        ));
        emailField.click();
        emailField.sendKeys(EMAIL);

        WebElement passwordField = driver.findElement(By.cssSelector("input[placeholder='Password*']"));
        passwordField.sendKeys(PASSWORD);

        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/form/div[3]/button"));
        loginButton.click();
    }

    public void selectDTOModelingTile() {
        WebElement dtoModelingTile = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//*[@id=\"root\"]/div/main/div/div/div[2]/div[1]/div/div")
        ));
        if (dtoModelingTile.isDisplayed()) {
            dtoModelingTile.click();
        }
    }

    public void clickAddCapabilityButton() {
        WebElement businessCapabilityMenu = driver.findElement(By.xpath("//div[contains(@class,'MuiListItemButton-root') and .//p[normalize-space()='Business Capability']]"));
        businessCapabilityMenu.click();
        WebElement addCapabilityButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(normalize-space(),'Add New Business Capability')]")));
        addCapabilityButton.click();
    }

    public void clickThreeDotMenuForLastCapability() throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        List<WebElement> openMenus = driver.findElements(
                By.xpath("//ul[contains(@class,'MuiMenu-list')]"));

        if (!openMenus.isEmpty()) {
            driver.findElement(By.tagName("body")).click();
            Thread.sleep(500);
        }

        WebElement scrollContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'css-xdo965')]")));

        js.executeScript(
                "arguments[0].scrollTop = arguments[0].scrollHeight;",
                scrollContainer
        );

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@data-node-id]")));

        List<WebElement> rows = driver.findElements(By.xpath("//div[@data-node-id]"));

        if (rows.isEmpty()) {
            throw new RuntimeException("No capability rows found!");
        }

        WebElement lastRow = rows.get(rows.size() - 1);
        WebElement threeDotBtn = lastRow.findElement(By.xpath(".//button[contains(@class,'MuiIconButton-root')]"));

        wait.until(ExpectedConditions.elementToBeClickable(threeDotBtn)).click();
    }

    public void fillCapabilityDetails() {
        WebElement capabilityName = driver.findElement(By.name("name"));
        String capabilityNameValue = "Capability_" + System.currentTimeMillis();
        capabilityName.sendKeys(capabilityNameValue);

        WebElement capabilityOwner = driver.findElement(By.xpath("(//div[@class='MuiSelect-select MuiSelect-outlined MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-16qqw7w'])[1]"));
        capabilityOwner.click();
        driver.findElement(By.xpath("(//li[@role='menuitem'])[1]")).click();

        WebElement descriptionField = driver.findElement(By.name("description"));
        descriptionField.sendKeys("The world is full of red waves and roses.");

        WebElement lifecycleStageField = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/div[1]/div/form/div/div[4]/div/div/div"));
        lifecycleStageField.click();
        driver.findElement(By.xpath("(//div[@class='MuiBox-root css-1u4f6x1'])[1]")).click();
    }

    public void updateLastCapability() {
        WebElement editOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@class,'MuiButtonBase-root MuiMenuItem-root MuiMenuItem-gutters MuiMenuItem-root MuiMenuItem-gutters css-1btjosd')][2]")));
        editOption.click();

        WebElement descriptionField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("description")));
        descriptionField.clear();
        descriptionField.sendKeys("The world is full of dinosaurs.");

        WebElement updateButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'css-1qc5q63')]")));
        updateButton.click();
    }

    public void deleteLastCapability() {
        try {
            clickThreeDotMenuForLastCapability();

            WebElement deleteOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[contains(@class,'MuiButtonBase-root MuiMenuItem-root MuiMenuItem-gutters MuiMenuItem-root MuiMenuItem-gutters css-p2riym')]")));            
            if (deleteOption.isEnabled()) {
                deleteOption.click();
            } else {
                return;
            }

            WebElement confirmDeleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'MuiButton-colorError css-4gs67b')]")));
            confirmDeleteButton.click();
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
            return alertText.contains("updated successfully");
        } catch (Exception e) {
            System.err.println("Error verifying success message: " + e.getMessage());
            return false;
        }
    }
}
