package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementActions {

    private WebDriver driver;
    private WaitUtil waitUtil;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
        this.waitUtil = new WaitUtil(driver);
    }

    public void click(WebElement element) {
        waitUtil.waitForElementToBeClickable(element);
        element.click();
    }

    public void type(WebElement element, String value) {
        waitUtil.waitForVisible(element);
        element.clear();
        element.sendKeys(value);
    }
}
