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

    // ---------- SEND KEYS (SAFE) ----------
//    public void waitForVisibleAndSendKeys(WebElement element, String text) {
//        waitForVisible(element);
//        try {
//            element.clear();
//            element.sendKeys(text);
//        } catch (ElementNotInteractableException e) {
//            jsClickWithoutWait(element);   // SAFE fallback
//            element.clear();
//            element.sendKeys(text);
//        }
//    }
    public void waitForVisibleAndSendKeys(WebElement element, String text) {
        try {
            // Wait for element to be visible and clickable
            waitForVisibility(element);
            waitForClickable(element);

            // Scroll into view just in case
            scrollIntoView(element);

            // Click to focus
            element.click();

            // Clear and type
            element.clear();
            element.sendKeys(text);

        } catch (ElementNotInteractableException e) {
            // JS fallback if normal sendKeys fails
            jsClickWithoutWait(element);
            element.clear();
            element.sendKeys(text);
        }
    }


    public WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public WebElement waitForVisibleAndEnabled(WebElement element, int seconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(seconds))
                .until(driver -> element.isDisplayed() && element.isEnabled() ? element : null);
    }

    // ---------- CLICK ----------
    public void click(WebElement element) {
        waitForVisible(element);
        element.click();
    }

    public void jsClick(WebElement element) {
        waitForVisible(element);
        jsClickWithoutWait(element);
    }

    // ⚠️ INTERNAL SAFE JS CLICK
    private void jsClickWithoutWait(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    // ---------- SCROLL ----------
    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", element);
    }

    // ---------- CLICKABLE ----------
    public WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForElementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    // ---------- VISIBILITY (ALIAS - SAFE) ----------
    public WebElement waitForVisibility(WebElement element) {
        return waitForVisible(element);
    }

    public WebElement waitForVisibility(By locator) {
        return waitForVisible(locator);
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
