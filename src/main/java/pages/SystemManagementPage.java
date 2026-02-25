package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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
        PageFactory.initElements(driver, this);
    }




    // ---------- NAVIGATION ---------

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

    // ---------- ADD SYSTEM Locators ----------
    @FindBy(xpath = "//button[normalize-space()='Add System']")
    private WebElement addSystemBtn;

    @FindBy(xpath = "//input[@name='full_name']")
    private WebElement systemFullName;

    @FindBy(xpath = "//input[@name='alias_name']")
    private WebElement systemAliasName;

    @FindBy(xpath = "//textarea[@name='description']")
    private WebElement systemDescription;

    /* ===================== SYSTEM OWNER (SEARCHABLE DROPDOWN) ===================== */

    @FindBy(xpath = "//p[contains(normalize-space(),'System Owner')]/following::div[@role='combobox'][1]")
    private WebElement systemOwnerDropdown;

    /* ===================== OTHER DROPDOWNS ===================== */

    @FindBy(xpath = "//p[contains(normalize-space(),'System Lifecycle')]/following::div[@role='combobox'][1]")
    private WebElement systemLifecycleDropdown;

    @FindBy(xpath = "//*[@id='_r_b2_']/li[2]/div/span[text()='LifecycleValue']")
    private WebElement lifecycleDropdownValue;

    @FindBy(xpath = "(//div[@role='combobox'])[3]")
    private WebElement shadowITDropdown;

    @FindBy(xpath = "//li[@data-value='Yes']/div/span[text()='Yes']")
    private WebElement shadowDropdownValue;


    /* ===================== ACTION BUTTONS ===================== */

    @FindBy(xpath = "//button[contains(normalize-space(),'Create')]")
    private WebElement createBtn;

    @FindBy(xpath = "//div[contains(text(),'successfully')]")
    private WebElement toasterMsg;



    public void clickAddSystem() {
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Add System']")
        )).click();
    }
    public boolean isAddSystemPopupVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[normalize-space()='Create New System']")
        )).isDisplayed();
    }
    public void enterFullName(String name) {
        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("full_name"))
        );
        field.clear();
        field.sendKeys(name);
    }

    public void enterAliasName(String alias) {
        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("alias_name"))
        );
        field.clear();
        field.sendKeys(alias);
    }

    public void enterDescription(String desc) {
        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("description"))
        );
        field.clear();
        field.sendKeys(desc);
    }
//    public void selectSystemOwner(String owner) {
//
//        System.out.println("Selecting owner: " + owner);
//
//        WebElement dropdown = wait.until(
//                ExpectedConditions.elementToBeClickable(systemOwnerDropdown)
//        );
//
//        dropdown.click();
//
//        // ✅ Wait for MUI dropdown container
//        wait.until(ExpectedConditions.visibilityOfElementLocated(
//                By.xpath("//ul[contains(@class,'Mui')]")
//        ));
//
//        // ✅ Optional search field
//        List<WebElement> searchInputs = driver.findElements(
//                By.xpath("//input[contains(@placeholder,'Search')]")
//        );
//
//        if (!searchInputs.isEmpty()) {
//            WebElement search = searchInputs.get(0);
//            search.clear();
//            search.sendKeys(owner);
//        }
//
//        // ✅ Robust option selection
//        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//li[@role='option'][.//text()[normalize-space()='" + owner + "']]")
//        ));
//
//        option.click();
//    }

    public void selectSystemOwner(String owner) {

        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(systemOwnerDropdown));
        dropdown.click();

        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[normalize-space()='" + owner + "']")
        ));

        option.click();
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
        wait.until(ExpectedConditions.elementToBeClickable(createBtn)).click();
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

