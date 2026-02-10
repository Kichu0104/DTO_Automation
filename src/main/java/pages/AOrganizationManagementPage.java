package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import utils.WaitUtil;

import java.time.Duration;
import java.util.List;

public class AOrganizationManagementPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private WaitUtil waitUtil;

    public AOrganizationManagementPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.waitUtil = new WaitUtil(driver);
        PageFactory.initElements(driver, this);
    }

    // ====================== NAVIGATION ======================
    @FindBy(xpath = "//*[normalize-space()='Administrator']")
    private WebElement administratorMenu;

    @FindBy(xpath = "//button[normalize-space()='Continue']")
    private WebElement continueBtn;

    @FindBy(xpath = "//div[normalize-space()='Data Management']")
    private WebElement dataManagementMenu;

    @FindBy(xpath = "//button[normalize-space()='View MetaData Libraries']")
    private WebElement viewMetadataBtn;

    @FindBy(xpath = "//span[normalize-space()='Organization']")
    private WebElement organizationMenu;

    public void clickAdministrator() {
        waitUtil.jsClick(administratorMenu);
    }

    public void clickContinue() {
        waitUtil.jsClick(continueBtn);
    }

    public void navigateToDataManagement() {
        waitUtil.jsClick(dataManagementMenu);
    }

    public void clickViewMetadataLibraries() {
        waitUtil.jsClick(viewMetadataBtn);
    }

    public void selectOrganizationFromSideMenu() {
        waitUtil.jsClick(organizationMenu);
    }

    // ===================== CREATE ORGANIZATION =====================
    @FindBy(xpath = "//button[contains(., 'Add Organization')]")
    private WebElement addOrganizationButton;

    @FindBy(name = "full_name")
    private WebElement orgNameInput;

    @FindBy(name = "alias_name")
    private WebElement orgAliasInput;

    @FindBy(name = "description")
    private WebElement orgDescriptionInput;

    @FindBy(xpath = "(//div[@role='combobox'][@aria-haspopup='listbox'])[2]")
    private WebElement lifecycleDropdown;

    @FindBy(name = "location")
    private WebElement orgLocationInput;

    @FindBy(xpath = "//button[normalize-space()='Create']")
    private WebElement saveOrgBtn;

    private final By successToast = By.xpath("//div[contains(@class,'MuiAlert-root')]");

    public void clickAddOrganization() {
        waitUtil.jsClick(addOrganizationButton);
    }

    public void enterOrganizationName(String orgName) {
        wait.until(ExpectedConditions.visibilityOf(orgNameInput)).sendKeys(orgName);
    }

    public void enterOrganizationAlias(String alias) {
        orgAliasInput.sendKeys(alias);
    }

    public void enterOrganizationDescription(String desc) {
        orgDescriptionInput.sendKeys(desc);
    }

    public void selectOrganizationLifecycle(String lifecycle) {
        wait.until(ExpectedConditions.elementToBeClickable(lifecycleDropdown)).click();
        By option = By.xpath("//li[normalize-space()='" + lifecycle + "']");
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }

    public void enterOrganizationLocation(String location) {
        orgLocationInput.sendKeys(location);
    }

    public void clickSaveOrganization() {
        waitUtil.jsClick(saveOrgBtn);
    }

    public boolean isOrganizationCreatedToastDisplayed(String orgName) {
        try {
            By toast = By.xpath("//div[contains(@class,'MuiAlert-message') and contains(text(),'\"" + orgName + "\"')]");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(toast)).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }

    //--------------------ORGANIZATION TABLE  SEARCH ----------------------------------
    @FindBy(xpath = "//input[contains(@placeholder,'Search') and not(@disabled)]")
    private WebElement searchInput;

    private By orgRowLocator(String orgName) {
        return By.xpath("//table//tbody//tr[td[normalize-space(text())='Automation org52']]");
    }
//
//    public void searchOrganization(String orgName) {
//        try {
//            // 1. Enter orgName in search
//            waitUtil.waitForVisibleAndSendKeys(searchInput, orgName+Keys.ENTER);
//
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to search for organization: " + orgName, e);
//        }
//    }

    public void searchOrganization(String orgName) {

        By searchBox = By.xpath("//input[contains(@placeholder,'Search') and not(@disabled)]");

        WebElement searchInput = wait.until(
                ExpectedConditions.elementToBeClickable(searchBox)
        );

        searchInput.click();
        searchInput.sendKeys(Keys.CONTROL + "a");
        searchInput.sendKeys(Keys.DELETE);
        searchInput.sendKeys(orgName);

        // wait for table refresh
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                By.xpath("//tbody/tr"), 0
        ));
    }



    private WebElement getOrganizationRowByName(String orgName) {
        List<WebElement> rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//tbody//tr")
        ));

        for (WebElement row : rows) {
            if (row.getText().toLowerCase().contains(orgName.toLowerCase())) {
                return row;
            }
        }
        throw new NoSuchElementException("Organization row not found: " + orgName);
    }

//
//    public WebElement getOrganizationRow(String orgName) {
//        return waitUtil.waitForVisibility(orgRowLocator(orgName));
//    }
//public WebElement getOrganizationRow(String orgName) {
//    List<WebElement> rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
//            By.xpath("//tbody//tr")
//    ));
//
//    for (WebElement row : rows) {
//        if (row.getText().toLowerCase().contains(orgName.toLowerCase())) {
//            return row;
//        }
//    }
//    throw new NoSuchElementException("Organization row not found: " + orgName);
//}

    public WebElement getOrganizationRow(String orgName) {

        String rowXpath = "//tbody/tr[td[normalize-space()='" + orgName + "']]";

        return wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath(rowXpath))
        );
    }


    //    public  WebElement addChildBtn = row.findElement(By.xpath(".//button[@title='Add Child']"));
    public void clickAddChildIcon(String orgName) {
        WebElement row = getOrganizationRow(orgName);
        WebElement addChildBtn = row.findElement(By.xpath(".//button[@title='Add Child']"));
//        Scroll to ensure table renders
        JavascriptExecutor js = (JavascriptExecutor)
                driver;js.executeScript("window.scrollBy(1500, 0)");
        waitUtil.jsClick(addChildBtn);
    }

    // ------------------------------------CHILD ORGANIZATION FIELDS-------------------
    @FindBy(name = "full_name")
    private WebElement childFullNameInput;

    @FindBy(name = "alias_name")
    private WebElement childAliasInput;

    @FindBy(name = "description")
    private WebElement childDescriptionInput;

    @FindBy(name = "location")
    private WebElement childLocationInput;

    @FindBy(xpath = "(//div[@role='dialog']//div[@role='combobox' and contains(@class,'MuiSelect-select')])[2]")
    private WebElement childLifecycleDropdown;

//    @FindBy(xpath = "(//div[@role='combobox'])[3]")
//    private WebElement childLifecycleDropdown;

    public void enterChildOrganizationName(String name) {
        WebElement element = waitUtil.waitForVisible(childFullNameInput);
        waitUtil.waitForVisibleAndSendKeys(element, name);
    }

    public void enterChildOrganizationAlias(String alias) {
        WebElement element = waitUtil.waitForVisible(childAliasInput);
        waitUtil.waitForVisibleAndSendKeys(element, alias);
    }

    public void enterChildOrganizationDescription(String desc) {
        WebElement element = waitUtil.waitForVisible(childDescriptionInput);
        waitUtil.waitForVisibleAndSendKeys(element, desc);
    }

    public void selectChildOrganizationLifecycle(String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(childLifecycleDropdown)).click();

        // select option by visible text
        WebElement option = driver.findElement(By.xpath("//li[normalize-space()='" + value + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }

    public void enterChildOrganizationLocation(String location) {
        WebElement element = waitUtil.waitForVisible(childLocationInput);
        element.clear();
        element.sendKeys(location);
    }

    public void clickSaveChildOrganization() {

        By saveChildBtn = By.xpath(
                "//div[@role='dialog']//button[.//span[normalize-space()='Create'] or normalize-space()='Create']"
        );

        WebElement saveBtn = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(saveChildBtn));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBtn);
    }

    public boolean isChildOrganizationCreatedToastDisplayed(String childName) {
        try {
            By toast = By.xpath("//div[contains(@class,'MuiAlert-message') and contains(text(),'" + childName + "')]");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(toast)).isDisplayed();
        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }
//---------------------------VIEW ----------------------------------
public void clickViewIcon(String orgName) {

    searchOrganization(orgName);   // SAME AS ADD CHILD

    WebElement row = getOrganizationRow(orgName);

    WebElement viewBtn = row.findElement(
            By.xpath(".//button[@title='View Details']")
    );

    wait.until(ExpectedConditions.elementToBeClickable(viewBtn));

    ((JavascriptExecutor) driver)
            .executeScript("arguments[0].click();", viewBtn);
}

//public void clickViewIcon() {
//
//    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
//
//    // 1️⃣ Wait until table row is present (search already applied)
//    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody//tr")));
//
//    // 2️⃣ Locate the View icon EXACTLY as console XPath
//    WebElement viewBtn = wait.until(
//            ExpectedConditions.elementToBeClickable(
//                    By.xpath("//tbody//tr//button[2]")
//            )
//    );
//
//    // 3️⃣ Scroll into view
//    ((JavascriptExecutor) driver)
//            .executeScript("arguments[0].scrollIntoView(true);", viewBtn);
//
//    // 4️⃣ JS click (same as Add Child)
//    ((JavascriptExecutor) driver)
//            .executeScript("arguments[0].click();", viewBtn);
//}
//
//    public void clickViewIcon(String orgName) {
//
//        // 1️⃣ Search organization
//        searchOrganization(orgName);
//
//        // 2️⃣ Wait until exactly ONE visible row is present
//        WebElement visibleRow = new WebDriverWait(driver, Duration.ofSeconds(20))
//                .until(ExpectedConditions.visibilityOfElementLocated(
//                        By.xpath("//div[@role='row' and not(contains(@class,'MuiDataGrid-row--hidden'))]")
//                ));
//
//        // 3️⃣ Validate row really belongs to searched org
//        if (!visibleRow.getText().toLowerCase().contains(orgName.toLowerCase())) {
//            throw new AssertionError("Search did not filter correctly for: " + orgName);
//        }
//
//        // 4️⃣ Click View icon ONLY inside that visible row
//        WebElement viewBtn = visibleRow.findElement(
//                By.xpath(".//button[@title='View Details']")
//        );
//
//        ((JavascriptExecutor) driver)
//                .executeScript("arguments[0].scrollIntoView(true);", viewBtn);
//
//        waitUtil.jsClick(viewBtn);
//    }


    public boolean isOrganizationDetailsPageDisplayed() {
        By header = By.xpath("//h1[@id='organizationName']");
        try {
            return waitUtil.waitForVisible(header).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public String getOrganizationNameFromDetails() {
        By header = By.xpath("//h1[@id='organizationName']");
        return waitUtil.waitForVisible(header).getText();
    }

    public void clickAddChildInDetailsPage() {
        try {
            // Local variable for the Add Child button
            WebElement addChildBtnInDetailsPage = driver.findElement(By.xpath("(//td[last()]//button[@title='View Details'])[1]")); // update xpath if needed

            new WebDriverWait(driver, Duration.ofSeconds(40))
                    .pollingEvery(Duration.ofMillis(500))
                    .until(ExpectedConditions.elementToBeClickable(addChildBtnInDetailsPage))
                    .click();
        } catch (TimeoutException e) {
            throw new RuntimeException("Add Child button in organization details page not found", e);
        }
    }


    // ===================== ROLE MANAGEMENT =====================
    @FindBy(xpath = "//button[@id='addRoleButton']")
    private WebElement addRoleButton;

    @FindBy(xpath = "//input[@id='roleName']")
    private WebElement roleNameInput;

    @FindBy(xpath = "//select[@id='roleLifecycle']")
    private WebElement roleLifecycleDropdown;

    @FindBy(xpath = "//button[@id='submitRole']")
    private WebElement submitRoleButton;

    public void clickRoleIcon(String orgName) {
        try {
            By roleBtn = By.xpath(
                    "//div[normalize-space()='" + orgName + "']" +
                            "/ancestor::div[@role='row']" +
                            "//button[@title='Roles']"
            );

            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(roleBtn));
            waitUtil.jsClick(btn);

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to click Role icon for organization: " + orgName, e
            );
        }
    }


    public void clickAddRole() {
        waitUtil.jsClick(addRoleButton);
    }

    public void enterRoleName(String roleName) {
        waitUtil.waitForVisible(roleNameInput).sendKeys(roleName);
    }

    public void selectRoleLifecycle(String lifecycle) {
        new Select(waitUtil.waitForVisible(roleLifecycleDropdown)).selectByVisibleText(lifecycle);
    }

    public void submitRole() {
        waitUtil.jsClick(submitRoleButton);
    }

    public boolean isRoleCreated(String roleName) {
        try {
            By roleRow = By.xpath("//table//tbody//tr[.//td[normalize-space()='" + roleName + "']]");
            return waitUtil.waitForVisible(roleRow).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    // ===================== USER MANAGEMENT =====================
    @FindBy(xpath = "//button[@id='addUserButton']")
    private WebElement addUserButton;

    @FindBy(xpath = "//input[@id='username']")
    private WebElement usernameInput;

    @FindBy(xpath = "//select[@id='userRole']")
    private WebElement userRoleDropdown;

    @FindBy(xpath = "//button[@id='submitUser']")
    private WebElement submitUserButton;

    public void clickAddUserIcon(String orgName) {
        try {
            By addUserBtn = By.xpath(
                    "//div[normalize-space()='" + orgName + "']" +
                            "/ancestor::div[@role='row']" +
                            "//button[@title='Add User']"
            );

            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(addUserBtn));
            waitUtil.jsClick(btn);

        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to click Add User icon for organization: " + orgName, e
            );
        }
    }


    public void clickAddUser() {
        waitUtil.jsClick(addUserButton);
    }

    public void enterUsername(String username) {
        waitUtil.waitForVisible(usernameInput).sendKeys(username);
    }

    public void selectUserRole(String role) {
        new Select(waitUtil.waitForVisible(userRoleDropdown)).selectByVisibleText(role);
    }

    public void submitUser() {
        waitUtil.jsClick(submitUserButton);
    }

    public boolean isUserCreated(String username) {
        try {
            By userRow = By.xpath("//table//tbody//tr[.//td[normalize-space()='" + username + "']]");
            return waitUtil.waitForVisible(userRow).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
}
