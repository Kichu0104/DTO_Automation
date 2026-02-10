package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptHelper {

    private WebDriver driver;
    private JavascriptExecutor js;

    public JavaScriptHelper(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    // Scroll element into view
    public void scrollIntoView(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }

    // Click element using JS
    public void clickElement(WebElement element) {
        js.executeScript("arguments[0].click();", element);
    }

    // Optional: set value using JS
    public void setValue(WebElement element, String value) {
        js.executeScript("arguments[0].value='" + value + "';", element);
    }
}
