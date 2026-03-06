package pages;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import utils.WaitUtil;
import java.util.List;
import java.time.Duration;

public class RiskControlPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private WaitUtil waitUtil;


    public RiskControlPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.waitUtil = new WaitUtil(driver);
        PageFactory.initElements(driver, this);
    }

    // Risks & Controls side menu
    @FindBy(xpath = "//span[normalize-space()='Risks & Controls']")
    WebElement riskControlsMenu;

    // View icon
    @FindBy(xpath = "(//button[@title='View Details'])[1]")
    WebElement viewIcon;

    // Popup title
    @FindBy(xpath = "//h5[normalize-space()='Risk Control Details']")
    WebElement riskControlPopup;

    // View Controls button
    @FindBy(xpath = "//button[normalize-space()='View Controls']")
    WebElement viewControlsButton;

    // Monitor icon
    @FindBy(xpath = "(//*[name()='svg' and @width='24'])[1]")
    WebElement monitorIcon;

    // Metric icon
    @FindBy(xpath = "(//*[name()='svg' and @width='24'])[2]")
    WebElement metricIcon;

    @FindBy(xpath = "((//button[@title='View Controls'])[1]")
    WebElement controlViewIcon;

    public void clickRiskControlsMenu() {
        riskControlsMenu.click();
    }

    public void clickViewIcon() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement viewIcon = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("(//button[@title='View Details'])[1]")));
        viewIcon.click();
    }

    public boolean isRiskControlPopupDisplayed() {
        return riskControlPopup.isDisplayed();
    }

    public void clickViewControlsButton() {
        viewControlsButton.click();
    }

    public boolean areControlsDisplayed() {
        return driver.getPageSource().contains("Controls");
    }

    public void clickMonitorIcon() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement monitorBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@role='dialog']//button[@title='View Monitors']")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", monitorBtn);
    }
    public boolean areMonitorsDisplayed() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement monitorTable = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[@role='dialog']//table")
                    )
            );

            return monitorTable.isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }


//public void clickMetricIcon() {
//
//    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//
//    // Wait for monitor popup to fully load
//    wait.until(ExpectedConditions.visibilityOfElementLocated(
//            By.xpath("//div[@role='dialog']")
//    ));
//
//    // Wait for overlay to disappear
//    wait.until(ExpectedConditions.invisibilityOfElementLocated(
//            By.className("MuiBackdrop-root")
//    ));
//
//    WebElement metricBtn = wait.until(
//            ExpectedConditions.elementToBeClickable(
//                    By.xpath("//div[@role='dialog']//button[@title='View Metrics']")));
//    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", metricBtn);
//    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", metricBtn);
//}
//    public void clickMetricIcon() {
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//
//        WebElement metricIcon = wait.until(
//                ExpectedConditions.elementToBeClickable(
//                        By.xpath("(//div[@role='dialog']//button[.//*[name()='svg']])[2]")));
//        metricIcon.click();
//    }

    public void clickMetricIcon() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Wait for monitor table to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='dialog']//table")));

        // Locate metric icon in first row
        WebElement metricIcon = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("(//button[@title='View Monitors Metrics'])[1]")
                )
        );

        // ----Scroll to element----------
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", metricIcon);
        // JavaScript click (bypasses overlay)
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", metricIcon);
    }

    public void waitForPopup() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h5[normalize-space()='Risk Control Details']")));
    }

    public boolean areMetricsDisplayed() {
        return driver.getPageSource().contains("Metric");
    }

    public void clickControlViewIcon() {

        WebElement viewControls = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("(//button[@title='View Controls'])[1]")
                )
        );

        viewControls.click();
    }

    public boolean isControlDetailsPopupDisplayed() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement popup = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='dialog']")
                    ));
            return popup.isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }
}