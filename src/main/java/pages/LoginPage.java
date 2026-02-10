package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    // Page Elements
@FindBy(xpath = "//input[@placeholder='email']")
private WebElement emailField;

@FindBy(xpath = "//input[@type='password']")
private WebElement passwordField;

@FindBy(xpath = "//button[text()='Login']")
private WebElement loginButton;

@FindBy(xpath = "//h4[text()='Digital Twin Dashboard']")
private WebElement dashboardHeader;

// Page Actions
public void enterUsername(String username) {
    By emailInput = By.xpath("//input[contains(@placeholder,'Email')]");
    WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
    email.clear();
    email.sendKeys(username);
}


public void enterPassword(String password) {
    wait.until(ExpectedConditions.visibilityOf(passwordField));
    passwordField.sendKeys(password);
}

public void clickLogin() {
    wait.until(ExpectedConditions.elementToBeClickable(loginButton));
    loginButton.click();
}

public boolean isDashboardVisible() {
    return wait.until(ExpectedConditions.visibilityOf(dashboardHeader)).isDisplayed();
}
}
