package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import hooks.Hooks;
import org.junit.Assert;
import pages.LoginPage;

public class LoginSteps {

    WebDriver driver;
    LoginPage loginPage;

    @Given("I opened the Application")
    public void i_opened_the_application() {
        driver = Hooks.getDriver();
        loginPage = new LoginPage(driver);
        Assert.assertNotNull("Driver is NULL", driver);

    }


    @When("I enter valid username {string} and password {string}")
    public void i_enter_valid_username_and_password(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @And("I click on login button")
    public void i_click_on_login_button() {
        loginPage.clickLogin();
    }

    @Then("dashboard should be visible")
    public void dashboard_should_be_visible() {
        Assert.assertTrue("Dashboard is NOT visible", loginPage.isDashboardVisible());
    }
}
