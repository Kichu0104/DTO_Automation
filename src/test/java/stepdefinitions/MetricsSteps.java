package stepdefinitions;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import hooks.Hooks;
import pages.LoginPage;
import pages.AOrganizationManagementPage;
import pages.MetricsPage;
public class MetricsSteps {
    WebDriver driver;
    LoginPage loginPage;
    AOrganizationManagementPage orgPage;
    MetricsPage metricsPage;
    private String oldFirstRowText;

    public MetricsSteps() {
        this.driver = Hooks.getDriver();
        this.loginPage = new LoginPage(driver);
        this.orgPage = new AOrganizationManagementPage(driver);
        this.metricsPage = new MetricsPage(driver);
    }

    // ------------------BACKGROUND STEPS------------------

    @And("I select Metrics from the side menu")
    public void i_select_metrics_from_the_side_menu() {
        metricsPage.clickMetricsMenu();
    }

    // ------------------ADD METRIC--------------------------

    @When("I click on Add Metric")
    public void i_click_on_add_metric() {
        metricsPage.clickAddMetricButton();
    }

    @Then("the Add Metric popup should be displayed")
    public void add_metric_popup_should_be_displayed() {
        Assert.assertTrue(metricsPage.isAddMetricPopupDisplayed());
    }

    @When("I enter metric name {string}")
    public void i_enter_metric_name(String name) {
        metricsPage.enterMetricName(name);
    }

    @When("I select metric owner {string}")
    public void i_select_metric_owner(String owner) {
        metricsPage.selectMetricOwner(owner);
    }

    @When("I enter the metric definition {string}")
    public void i_enter_the_definition(String definition) {
        metricsPage.enterDefinition(definition);
    }

    @When("I select the first supporting metric ID")
    public void i_select_the_first_supporting_metric_id() {
        metricsPage.selectFirstMetricId();
    }

    @When("I enter target {string}")
    public void i_enter_target(String target) {
        metricsPage.enterTarget(target);
    }

    @When("I select target unit of measure {string}")
    public void i_select_target_unit_of_measure(String unit) {
        metricsPage.selectTargetUnit(unit);
    }

    @When("I select source {string}")
    public void i_enter_source(String source) {
        metricsPage.selectSource(source);
    }

    @When("I select reporting frequency {string}")
    public void i_select_reporting_frequency(String frequency) {
        metricsPage.selectReportingFrequency(frequency);
    }

    @When("I click on the Create Metric button")
    public void i_click_on_create_metric_button() {
        metricsPage.clickCreateMetric();
    }

    @Then("the metric should be created successfully")
    public void metric_should_be_created_successfully() {
        Assert.assertTrue(metricsPage.isMetricCreated());
    }

    //_____________ VIEW METRIC VALUES__________________________


    @When("I click on View All Metrics Values")
    public void i_click_on_view_all_metrics_values() {
        metricsPage.clickViewAllMetricsValues();
    }

    @Then("the Metric Values page should be displayed")
    public void metric_values_page_should_be_displayed() {
        Assert.assertTrue(metricsPage.isMetricValuesPageDisplayed());
    }

    @When("I select metric code {string}")
    public void i_select_metric_code(String metricCode) {
        metricsPage.selectMetricCode(metricCode);
    }

    @When("I select year {string}")
    public void i_select_year(String year) {
        metricsPage.selectYear(year);
    }

    @Then("the metric values grid should be displayed with months")
    public void metric_values_grid_should_be_displayed_with_months() {
        Assert.assertTrue(metricsPage.isMetricValuesGridDisplayed());
    }

    // ------------------SEARCH METRIC-------------------------

    @When("I search metric using name {string}")
    public void i_search_metric_using_name(String metricName) {
        metricsPage.searchMetric(metricName);
    }

    @Then("the search result should contain {string}")
    public void the_search_result_should_contain(String metricName) {
        Assert.assertTrue(metricsPage.isSearchedMetricDisplayed(metricName));
    }
    // ------------------PAGINATION-------------------------
    @When("I navigate to the next metrics page using pagination")
    public void i_navigate_to_the_next_metrics_page_using_pagination() {
        oldFirstRowText = metricsPage.getFirstRowText();
        metricsPage.clickNextPage();
        metricsPage.waitForTableDataChange(oldFirstRowText);
    }

    @Then("the metrics list should display the next set of records")
    public void the_metrics_list_should_display_the_next_set_of_records() {

        String newFirstRowText = metricsPage.getFirstRowText();

        Assert.assertNotEquals(oldFirstRowText, newFirstRowText);
    }
}

