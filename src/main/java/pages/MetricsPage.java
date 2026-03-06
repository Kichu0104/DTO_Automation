package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import utils.WaitUtil;
import java.util.List;
import java.time.Duration;

public class MetricsPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private WaitUtil waitUtil;

    public MetricsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.waitUtil = new WaitUtil(driver);
        PageFactory.initElements(driver, this);
    }

    // ====================== NAVIGATION ======================

    @FindBy(xpath = "//span[normalize-space()='Metrics']")
    private WebElement metricsMenu;

    public void clickMetricsMenu() {
        waitUtil.jsClick(metricsMenu);
    }

    // ====================== ADD METRIC ======================

    @FindBy(xpath = "//button[contains(.,'Add Metric')]")
    private WebElement addMetricButton;

    @FindBy(name = "metric_name")
    private WebElement metricNameInput;

    @FindBy(xpath = "//div[@role='dialog']//div[@role='combobox'][1]")
    private WebElement metricOwnerDropdown;

    @FindBy(name = "definition")
    private WebElement definitionInput;

    @FindBy(xpath = "(//div[@role='combobox'])[2]")
    private WebElement supportingMetricIdInput;

    @FindBy(name = "target")
    private WebElement targetInput;

    @FindBy(xpath = "//label[normalize-space()='Target Unit of Measure']/following::div[@role='combobox'][1]")
    private WebElement targetUnitDropdown;

    @FindBy(xpath = "(//div[@role='combobox'])[5]")
    private WebElement sourceDropdown;

    @FindBy(xpath = "//label[normalize-space()='Reporting Frequency']/following::div[@role='combobox'][1]")
    private WebElement reportingFrequencyDropdown;

    @FindBy(xpath = "//button[normalize-space()='Create']")
    private WebElement createMetricButton;

    @FindBy(xpath = "//div[@role='dialog']")
    private WebElement addMetricPopup;

    public void clickAddMetricButton() {
        waitUtil.jsClick(addMetricButton);
    }

    public boolean isAddMetricPopupDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(addMetricPopup)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterMetricName(String name) {
        wait.until(ExpectedConditions.visibilityOf(metricNameInput)).clear();
        metricNameInput.sendKeys(name);
    }

    public void selectDropdownValue(By dropdownLocator, String value) {

        // 1️⃣ Click dropdown
        WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(dropdownLocator));
        dropdown.click();

        // 2️⃣ Wait for value to appear (span text)
        By optionLocator = By.xpath("//span[normalize-space()='" + value + "']");

        WebElement option = wait.until(
                ExpectedConditions.elementToBeClickable(optionLocator));

        option.click();
    }

    public void selectMetricOwner(String ownerName) {

        // 1️⃣ Click dropdown
        By dropdownLocator = By.xpath("(//div[@role='combobox'])[1]");
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownLocator));
        dropdown.click();

        // 2️⃣ Search owner
        By searchLocator = By.xpath("//input[@placeholder='Search Owner Name']");
        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(searchLocator));
        searchInput.clear();
        searchInput.sendKeys(ownerName);

        // 3️⃣ Select option based on Name: AUTOMATION
        By optionLocator = By.xpath("//p[contains(normalize-space(.),'Name: " + ownerName + "')]");

        WebElement option = wait.until(
                ExpectedConditions.elementToBeClickable(optionLocator)
        );

        new Actions(driver)
                .moveToElement(option)
                .pause(Duration.ofMillis(300))
                .click()
                .perform();
    }

    public void enterDefinition(String definition) {
        definitionInput.clear();
        definitionInput.sendKeys(definition);
    }

    public void selectFirstMetricId() {

        // 1️⃣ Click dropdown
        By dropdownLocator = By.xpath("(//div[@role='combobox'])[2]");
        WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(dropdownLocator));

        dropdown.click();

        // 2️⃣ Wait for metric ID options (p elements)
        By options = By.xpath("//p[contains(text(),'Id:')]");

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(options, 0));

        // 3️⃣ Click first metric ID
        driver.findElements(options).get(0).click();
    }


    public void enterTarget(String target) {

        By targetInputLocator = By.xpath("//input[@name='target']");
        WebElement targetInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(targetInputLocator));

        targetInput.clear();
        targetInput.sendKeys(target);
    }

    public void selectTargetUnit(String value) {

        By targetUnitDropdown = By.xpath("(//div[@role='combobox'])[3]");
        selectDropdownValue(targetUnitDropdown, value);
    }


    public void selectSource(String value) {
        By sourceLocator = By.xpath("(//div[@role='combobox'])[4]");
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(sourceLocator));
        dropdown.click();
        wait.until(ExpectedConditions.attributeToBe(sourceLocator, "aria-expanded", "true"));
        By option = By.xpath("//ul[@role='listbox']//li[normalize-space()='" + value + "']");
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }

    public void selectReportingFrequency(String value) {
        By reportingDropdown = By.xpath("(//div[@role='combobox'])[5]");
        selectDropdownValue(reportingDropdown, value);
    }

    public void clickCreateMetric() {
        waitUtil.jsClick(createMetricButton);
    }

    // ====================== DROPDOWN HANDLER ======================

    private void selectDropdownValue(WebElement dropdown, String value) {
        wait.until(ExpectedConditions.elementToBeClickable(dropdown)).click();
        By option = By.xpath("//li[@role='option' and normalize-space()='" + value + "']");
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }


    // ====================== SUCCESS TOAST ======================

    public boolean isMetricCreated() {
        try {
            By toast = By.xpath("//div[contains(@class,'MuiAlert-message')]");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(toast)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ====================== SEARCH METRIC ======================

    @FindBy(xpath = "//input[contains(@placeholder,'Search') and not(@disabled)]")
    private WebElement searchBox;

    public void searchMetric(String metricName) {

        WebElement box = wait.until(ExpectedConditions.elementToBeClickable(searchBox));

        box.click();
        box.sendKeys(Keys.CONTROL + "a");
        box.sendKeys(Keys.DELETE);
        box.sendKeys(metricName);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr")));
    }

    public boolean isSearchedMetricDisplayed(String metricName) {
        try {
            By rowLocator = By.xpath(
                    "//tbody/tr[.//td[contains(translate(normalize-space(.), " +
                            "'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), '" +
                            metricName.toLowerCase() + "')]]"
            );
            return wait.until(ExpectedConditions.visibilityOfElementLocated(rowLocator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // ====================== VIEW METRIC VALUES ======================

    @FindBy(xpath = "//button[contains(.,'View All Metrics Values')]")
    private WebElement viewAllMetricsButton;

    @FindBy(xpath = "//h5[contains(.,'Metric Values')]")
    private WebElement metricValuesHeader;

    @FindBy(xpath = "//label[normalize-space()='Metric Code']/following::div[@role='combobox'][1]")
    private WebElement metricCodeDropdown;

    @FindBy(xpath = "//label[normalize-space()='Year']/following::div[@role='combobox'][1]")
    private WebElement yearDropdown;

    public void clickViewAllMetricsValues() {
        waitUtil.jsClick(viewAllMetricsButton);
    }

    public boolean isMetricValuesPageDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(metricValuesHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void selectMetricCode(String value) {

        By dropdown = By.xpath(
                "//label[normalize-space()='Metric Code']" +
                        "/following::div[@role='combobox'][1]");

        wait.until(ExpectedConditions.elementToBeClickable(dropdown)).click();

        By option = By.xpath(
                "//li[@role='menuitem']//span[normalize-space()='" + value.toUpperCase() + "']");

        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }

    public void selectYear(String year) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // 1️⃣ Click Year dropdown using label (NO index)
        By yearDropdown = By.xpath(
                "//label[normalize-space()='Year']/following::div[@role='combobox'][1]");

        wait.until(ExpectedConditions.elementToBeClickable(yearDropdown)).click();

        // 2️⃣ Wait for year option using correct role
        By yearOption = By.xpath(
                "//li[@role='menuitem']//span[normalize-space()='" + year + "']");

        wait.until(ExpectedConditions.elementToBeClickable(yearOption)).click();
    }

    public boolean isMetricValuesGridDisplayed() {
        try {
            By monthHeader = By.xpath("//th[normalize-space()='Jan']");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(monthHeader)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
//    -------------------------PAGINATION______________________________

    @FindBy(xpath = "//button[normalize-space()='Next']")
    private WebElement nextButton;

    public String getFirstRowText() {
        wait.until(driver -> {java.util.List<WebElement> rows = driver.findElements(By.xpath("//table//tbody//tr"));
            return !rows.isEmpty() && rows.get(0).isDisplayed();
        });
        java.util.List<WebElement> rows = driver.findElements(By.xpath("//table//tbody//tr"));
        return rows.isEmpty() ? "" : rows.get(0).getText();
    }

    public void waitForTableDataChange(String oldFirstRowText) {
        wait.until(driver -> {
            try {
                java.util.List<WebElement> rows = driver.findElements(By.xpath("//table//tbody//tr"));
                if (rows.isEmpty()) return false;
                String newText = rows.get(0).getText();
                return !newText.equals(oldFirstRowText);
            }
            catch (StaleElementReferenceException e) {
                return false;
            }
        });
    }

    public void clickNextPage() {

        wait.until(ExpectedConditions.elementToBeClickable(nextButton));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", nextButton);

        nextButton.click();
    }
}

