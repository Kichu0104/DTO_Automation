package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SystemManagementPage {

    WebDriver driver;
    WebDriverWait wait;

    public SystemManagementPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    // ---------- NAVIGATION ----------
    public void clickAdministrator() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("administratorMenu"))).click();
    }

    public void clickContinue() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("continueBtn"))).click();
    }

    public void clickDataManagement() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("dataManagementMenu"))).click();
    }

    public void clickViewMetadataLibraries() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("viewMetadataLibraries"))).click();
    }

    public boolean isSystemPageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("systemPageTitle"))).isDisplayed();
    }

    // ---------- ADD SYSTEM ----------
    public void clickAddSystem() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("addSystemBtn"))).click();
    }

    public boolean isAddSystemPopupVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("addSystemPopup"))).isDisplayed();
    }

    public void enterFullName(String name) {
        WebElement fullName = wait.until(ExpectedConditions.elementToBeClickable(By.id("systemFullName")));
        fullName.clear();
        fullName.sendKeys(name);
    }

    public void enterAliasName(String alias) {
        WebElement aliasField = wait.until(ExpectedConditions.elementToBeClickable(By.id("systemAlias")));
        aliasField.clear();
        aliasField.sendKeys(alias);
    }

    public void enterDescription(String desc) {
        WebElement descField = wait.until(ExpectedConditions.elementToBeClickable(By.id("systemDescription")));
        descField.clear();
        descField.sendKeys(desc);
    }

    public void selectSystemOwner(String owner) {
        WebElement ownerDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("systemOwner")));
        ownerDropdown.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + owner + "']"))).click();
    }

    public void selectLifeCycle(String lifecycle) {
        WebElement lifecycleDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("systemLifeCycle")));
        lifecycleDropdown.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + lifecycle + "']"))).click();
    }

    public void selectShadowITValue(String value) {
        WebElement shadowITDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("shadowIT")));
        shadowITDropdown.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='" + value + "']"))).click();
    }

    public void clickCreate() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("createSystemBtn"))).click();
    }

    public void verifySuccessMessage() {
        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("successMsg")));
        if (!msg.isDisplayed()) {
            throw new AssertionError("System creation success message not displayed");
        }
    }

    // ---------- VIEW SYSTEM ----------
    public void clickFirstViewIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".viewSystemIcon"))).click();
    }

    public boolean isSystemDetailsPageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("systemDetailsTitle"))).isDisplayed();
    }

    // ---------- SEARCH ----------
    public void searchSystem(String keyword) {
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.id("searchSystem")));
        searchBox.clear();
        searchBox.sendKeys(keyword);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("searchBtn"))).click();
    }

    public boolean areSearchResultsMatching(String keyword) {
        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".systemRow")));
        return rows.stream().allMatch(row -> row.getText().contains(keyword));
    }

    public boolean isNoResultDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("noResultMsg"))).isDisplayed();
    }

    // ---------- PAGINATION ----------
    public int getCurrentPageNumber() {
        WebElement pageIndicator = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("currentPage")));
        return Integer.parseInt(pageIndicator.getText());
    }

    public void clickNextPage() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("nextPageBtn"))).click();
    }

    public void waitForTableDataChange(String oldText) {
        wait.until(driver -> !driver.findElement(By.cssSelector(".systemRow:first-child")).getText().equals(oldText));
    }

    public String getFirstRowText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".systemRow:first-child"))).getText();
    }

    public int getRowCount() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".systemRow"))).size();
    }

    public void verifyCurrentPageUpdated(int previousPage) {
        int currentPage = getCurrentPageNumber();
        if (currentPage == previousPage) {
            throw new AssertionError("Page did not advance as expected");
        }
    }
}
