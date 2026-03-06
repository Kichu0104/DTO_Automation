package stepdefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import hooks.Hooks;
import pages.LoginPage;
import pages.AOrganizationManagementPage;
import pages.MetricsPage;
import pages.RiskControlPage;

public class RiskControlSteps {

    WebDriver driver;
    LoginPage loginPage;
    AOrganizationManagementPage orgPage;
    RiskControlPage riskControlPage;

    public RiskControlSteps() {
        this.driver = Hooks.getDriver();
        this.loginPage = new LoginPage(driver);
        this.orgPage = new AOrganizationManagementPage(driver);
        riskControlPage = new RiskControlPage(driver);
    }

    @When("I select {string} from the side menu")
    public void i_select_from_the_side_menu(String menu) {
        riskControlPage.clickRiskControlsMenu();
    }

    @When("I click on the view icon of the risks and controls table")
    public void i_click_on_the_view_icon_of_the_risks_and_controls_table() {
        riskControlPage.clickViewIcon();
    }

    @Then("the Risk Control Details popup should be displayed")
    public void the_risk_control_details_popup_should_be_displayed() {
        Assert.assertTrue("Risk Control Details popup not displayed",
                riskControlPage.isRiskControlPopupDisplayed());
    }

    @When("I click on the View Controls button")
    public void i_click_on_the_view_controls_button() {
        riskControlPage.clickViewControlsButton();
    }

    @Then("the Controls should be displayed")
    public void the_controls_should_be_displayed() {
        Assert.assertTrue("Controls section not displayed",
                riskControlPage.areControlsDisplayed());
    }

    @When("I click on the Monitor icon of the first row")
    public void i_click_on_the_monitor_icon_of_the_first_row() {
        riskControlPage.clickMonitorIcon();
    }

    @Then("the Monitors should be displayed")
    public void the_monitors_should_be_displayed() {
        Assert.assertTrue("Monitors not displayed",
                riskControlPage.areMonitorsDisplayed());
    }

    @When("I click on the Metric icon of the first row")
    public void i_click_on_the_metric_icon_of_the_first_row() {
        riskControlPage.clickMetricIcon();
    }

    @Then("the Metrics should be displayed")
    public void the_metrics_should_be_displayed() {
        Assert.assertTrue("Metrics not displayed",
                riskControlPage.areMetricsDisplayed());
    }
    @When("I click on the control view icon of the risk and controls table")
    public void i_click_on_the_control_view_icon_of_the_risk_and_controls_table() {
        riskControlPage.clickControlViewIcon();
    }
    @Then("the Control Details popup should be displayed")
    public void the_control_details_popup_should_be_displayed() {

        Assert.assertTrue("Control Details popup not displayed",
                riskControlPage.isControlDetailsPopupDisplayed());
    }
}