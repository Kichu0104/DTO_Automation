package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtil {

    private WebDriver driver;
    private WebDriverWait wait;

    public WaitUtil(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    // ---------- VISIBILITY ----------

    public WebElement waitForVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // ---------- CLICKABLE (RESTORED FIX) ----------

    public WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // ---------- CLICK ----------

    public void click(WebElement element) {
        waitForElementToBeClickable(element).click();
    }

    public void click(By locator) {
        waitForElementToBeClickable(locator).click();
    }

    public void jsClick(WebElement element) {
        waitForVisible(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    // ---------- TYPE ----------

    public void type(By locator, String text) {
        WebElement element = waitForElementToBeClickable(locator);
        element.clear();
        element.sendKeys(text);
    }

    // ---------- TEXT ----------

    public String getText(By locator) {
        return waitForVisible(locator).getText();
    }

    // ---------- SCROLL ----------

    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    // ---------- CUSTOM WAIT ----------

    public void waitForSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
