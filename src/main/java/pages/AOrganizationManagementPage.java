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

        By addOrgBtn = By.xpath("//button[contains(., 'Add Organization')]");
        System.out.println(driver.getPageSource());

        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(addOrgBtn)
        );

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
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
            By toast = By.xpath(
                    "//div[contains(@class,'MuiAlert-message') and contains(., '\"" + orgName + "\"')]"
            );

            return wait.until(ExpectedConditions.visibilityOfElementLocated(toast)).isDisplayed();

        } catch (TimeoutException | NoSuchElementException e) {
            return false;
        }
    }
    //--------------------ORGANIZATION TABLE  SEARCH ----------------------------------

    @FindBy(xpath = "//input[contains(@placeholder,'Search') and not(@disabled)]")
    private WebElement searchInput;

    public WebElement waitForOrganizationRow(String orgName) {
        By rowLocator = By.xpath("//tbody/tr[.//td[contains(translate(normalize-space(.), " +
                "'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), '"
                + orgName.toLowerCase() + "')]]");

        return wait.until(ExpectedConditions.visibilityOfElementLocated(rowLocator));
    }

    public void searchOrganization(String orgName) {

        By searchBox = By.xpath("//input[contains(@placeholder,'Search') and not(@disabled)]");

        WebElement searchInput = wait.until(
                ExpectedConditions.elementToBeClickable(searchBox)
        );

        searchInput.click();
        searchInput.sendKeys(Keys.CONTROL + "a");
        searchInput.sendKeys(Keys.DELETE);
        searchInput.sendKeys(orgName);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//tbody/tr")
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

    public WebElement getOrganizationRow(String orgName) {
        String rowXpath = "//tbody/tr[td[normalize-space()='" + orgName + "']]";
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath(rowXpath)));
    }

    public WebElement waitForOrganizationToAppear(String orgName) {
        By locator = By.xpath("//td[contains(translate(normalize-space(.), " +
                        "'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), " +
                        "'" + orgName.toLowerCase() + "')]"
        );
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    public void clickAddChildIcon(String orgName) {
        By rowLocator = By.xpath(
                "//tr[.//td[contains(translate(normalize-space(.), " +
                        "'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), " +
                        "'" + orgName.toLowerCase() + "')]]");
        WebElement row = wait.until(ExpectedConditions.visibilityOfElementLocated(rowLocator));
        WebElement addChildBtn = row.findElement(By.xpath(".//button[@title='Add Child']"));
        wait.until(ExpectedConditions.elementToBeClickable(addChildBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addChildBtn);
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

        //----------- select option by visible text----------
        WebElement option = driver.findElement(By.xpath("//li[normalize-space()='" + value + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }

    public void enterChildOrganizationLocation(String location) {
        orgLocationInput.sendKeys(location);
    }

    public void clickSaveChildOrganization() {

        By saveChildBtn = By.xpath("//div[@role='dialog']//button[.//span[normalize-space()='Create'] or normalize-space()='Create']");
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
        searchOrganization(orgName);
        WebElement row = waitForOrganizationRow(orgName);
        WebElement viewBtn = row.findElement(By.xpath(".//button[@title='View Details']"));
        wait.until(ExpectedConditions.elementToBeClickable(viewBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewBtn);
    }


    public boolean isOrganizationDetailsPageDisplayed() {
        By header = By.xpath("//h5[normalize-space()='Organization Details']");
        try {
            return waitUtil.waitForVisible(header).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickAddChildInDetailsPage() {
        By addChildBtn = By.xpath("//button[normalize-space()='Add Child']"
        );
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.elementToBeClickable(addChildBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    // --------------- VIEW ADD CHILD ELEMENTS ---------------
    @FindBy(name = "full_name")
    private WebElement viewAddChildFullNameInput;

    @FindBy(name = "alias_name")
    private WebElement viewAddChildAliasInput;

    @FindBy(name = "description")
    private WebElement viewAddChildDescriptionInput;

    @FindBy(name = "location")
    private WebElement viewAddChildLocationInput;

    @FindBy(xpath = "//div[@role='dialog']//div[@role='combobox']")
    private WebElement viewAddChildLifecycleDropdown;

// -------------- VIEW ADD CHILD METHODS----------------------

    private void enterTextField(WebElement element, String value) {
        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(element));
        el.clear();
        el.sendKeys(value);
        // Force React to recognize the change
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input', { bubbles: true }));",
                el, value);
    }

    // -----------Wait for popup to be fully loaded------------
    public void waitForViewAddChildPopupToBeReady() {
        wait.until(ExpectedConditions.visibilityOf(viewAddChildFullNameInput));
        wait.until(ExpectedConditions.elementToBeClickable(viewAddChildFullNameInput));

        wait.until(ExpectedConditions.visibilityOf(viewAddChildAliasInput));
        wait.until(ExpectedConditions.elementToBeClickable(viewAddChildAliasInput));

        wait.until(ExpectedConditions.visibilityOf(viewAddChildDescriptionInput));
        wait.until(ExpectedConditions.elementToBeClickable(viewAddChildDescriptionInput));

        wait.until(ExpectedConditions.visibilityOf(viewAddChildLocationInput));
        wait.until(ExpectedConditions.elementToBeClickable(viewAddChildLocationInput));

        wait.until(ExpectedConditions.elementToBeClickable(viewAddChildLifecycleDropdown));
    }

    // Fill the fields
    public void enterViewAddChildOrganizationName(String name) {
        enterTextField(viewAddChildFullNameInput, name);
    }

    public void enterViewAddChildOrganizationAlias(String alias) {
        enterTextField(viewAddChildAliasInput, alias);
    }

    public void enterViewAddChildOrganizationDescription(String desc) {
        enterTextField(viewAddChildDescriptionInput, desc);
    }

    public void enterViewAddChildOrgLocation(String location) {
        orgLocationInput.sendKeys(location);
    }

    public void selectViewAddChildOrgLifecycle(String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(childLifecycleDropdown)).click();

        // -------select option by visible text------------
        WebElement option = driver.findElement(By.xpath("//li[normalize-space()='" + value + "']"));
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }

    public void clickCreateViewAddChildOrganization() {

        WebElement createBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@role='dialog']//button[normalize-space()='Create']"))
        );

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", createBtn);

        // ✅ WAIT FOR SUCCESS TOAST INSTEAD OF DIALOG INVISIBILITY
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'MuiAlert-message') and contains(.,'created successfully')]")
        ));
    }

    public boolean isViewAddChildOrganizationCreatedToastDisplayed(String name) {
        try {
            By toastMessage = By.xpath("//div[contains(@class,'MuiAlert-message') and contains(., 'created successfully')]");
            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(toastMessage));
            return toast.getText().contains(name);

        } catch (TimeoutException e) {
            return false;
        }
    }
// ----------------- ROLE MANAGEMENT ELEMENTS -------------
    @FindBy(xpath = "//div[@role='dialog']//button[normalize-space()='Add Role']")
    private WebElement addRoleButton;

    @FindBy(xpath = "//input[@name='role_name']")
    private WebElement roleNameInput;

    @FindBy(name = "role_lifecycle")
    private WebElement roleLifecycleInput;

    @FindBy(xpath = "//div[@role='dialog'][.//*[normalize-space()='Add New Role']]//button[normalize-space()='Add Role']")
    private WebElement submitRoleButton;


// -------------- ROLE MANAGEMENT METHODS-------------------

    public void clickRoleIcon(String orgName) {

        searchOrganization(orgName);   //
        WebElement row = waitForOrganizationRow(orgName);
        WebElement roleBtn = row.findElement(By.xpath(".//button[.//*[local-name()='path' and contains(@d,'M4.5 0.75')]]"));
        wait.until(ExpectedConditions.elementToBeClickable(roleBtn));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", roleBtn);
    }

    public void clickAddRoleOpenButton() {
        waitUtil.jsClick(addRoleButton);
    }

    public void enterRoleName(String roleName) {
        waitUtil.waitForVisible(roleNameInput).sendKeys(roleName);
    }

    public void enterRoleLifecycle(String lifecycle) {
        waitUtil.waitForVisible(roleLifecycleInput).sendKeys(lifecycle);
    }

    public void clickAddRoleSubmit() {
        By addRoleSubmit = By.xpath("//div[@role='dialog'][.//*[normalize-space()='Add New Role']]//button[normalize-space()='Add Role']");
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(addRoleSubmit));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        // ✅ CRITICAL WAIT
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@role='dialog'][.//*[normalize-space()='Add New Role']]")));
    }

    private void waitForRoleToast() {
        By toastBy = By.xpath("//div[contains(@class,'MuiAlert-message') and contains(text(),'Role added successfully')]");
        WebElement toast = wait.until(ExpectedConditions.presenceOfElementLocated(toastBy));
        System.out.println("Toast detected: " + toast.getText());
    }

    public boolean isRoleCreated(String roleName) {
        try {
//            By roleRow = By.xpath("//table//tbody//tr[.//td[normalize-space()='" + roleName + "']]");
            By roleRow = By.xpath("//div[contains(@class,'MuiAlert-message') and normalize-space()='Role added successfully!']");
            return wait.until(ExpectedConditions.visibilityOfElementLocated(roleRow)).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }
//-------------ADD USER_______________

    @FindBy(xpath = ".//button[.//*[local-name()='path' and contains(@d,'M7.07027 2.35742')]]")
    private WebElement addUserIcon;

    @FindBy(xpath = "//button[normalize-space()='Add User']")
    private WebElement addUserButton;

    @FindBy(name = "member_name")
    private WebElement usernameInput;

    @FindBy(xpath = "(//div[@role='combobox'])[1]")
    private WebElement roleDropdown;

    @FindBy(xpath = "//ul[@role='listbox']//li[1]")
    private WebElement firstRoleOption;

    @FindBy(xpath = "//label[contains(.,'System User')]")
    private WebElement systemUserLabel;
    private WebElement systemUserCheckbox;

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "(//div[@role='combobox'])[2]")
    private WebElement systemRoleDropdown;

//    @FindBy(xpath = "//button[contains(.,'Add User') and not(@disabled)]")
//    private WebElement submitUserButtons;

    @FindBy(xpath = "(//button[normalize-space()='Add User'])[2]")
    private List<WebElement> submitUserButton;

    @FindBy(xpath = "//div[contains(@class,'MuiAlert-filledSuccess')]")
    private WebElement successAlert;

    // ------------------- Actions -------------------

    public void clickAddUserIcon(String orgName) {
        searchOrganization(orgName);
        WebElement row = waitForOrganizationRow(orgName);
        By addUserIconLocator = By.xpath(".//button[.//*[local-name()='path' and contains(@d,'M7.07027 2.35742')]]");
        WebElement addUserIcon = new WebDriverWait(driver, Duration.ofSeconds(20))
                .until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(row, addUserIconLocator))
                .get(0);
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(addUserIcon));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addUserIcon);
    }

    public void clickAddUser() {
        waitUtil.waitForClickable(addUserButton).click();
    }

//    public void clickAddUser() {
//        WebElement addUserBtn = wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//div[@role='dialog']//button[normalize-space()='Add User']")
//        ));
//        addUserBtn.click();
//
//        // Wait for the modal to disappear
//        wait.until(ExpectedConditions.invisibilityOf(addUserBtn));
//    }

    public void enterUsername(String username) {
        WebElement element = waitUtil.waitForClickable(usernameInput);
        element.clear();
        element.sendKeys(username);
    }

    public void selectFirstRole() {
        waitUtil.waitForClickable(roleDropdown).click();
        waitUtil.waitForClickable(firstRoleOption).click();
    }

    public void checkSystemUser() {
        By labelBy = By.xpath("//div[@role='dialog']//label[contains(.,'System User')]");
        WebElement label = wait.until(ExpectedConditions.visibilityOfElementLocated(labelBy));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block:'center'});", label);
        wait.until(ExpectedConditions.elementToBeClickable(label));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", label);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
    }

    public void enterEmail(String email) {
        By emailBy = By.name("email");
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(emailBy));
        element.clear();
        element.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement element = waitUtil.waitForClickable(passwordInput);
        element.clear();
        element.sendKeys(password);
    }

    public void selectSystemRole(String roleName) {
        waitUtil.waitForClickable(systemRoleDropdown).click();
        WebElement roleOption = waitUtil.waitForClickable(
                By.xpath("//li[normalize-space()='" + roleName + "']"));
        roleOption.click();
    }

    private void waitForUserToast() {
        By toastBy = By.xpath(
                "//div[contains(@class,'MuiAlert-message') and " +
                        "contains(normalize-space(),'User added successfully')]"
        );

        WebElement toast = wait.until(ExpectedConditions.presenceOfElementLocated(toastBy));
        System.out.println("Toast detected: " + toast.getText());
    }

    public void clickSubmitUser() {
        for (WebElement btn : submitUserButton)
        {
            if (btn.isDisplayed() && btn.isEnabled())
            {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
                return;
            }
        }

        throw new RuntimeException("Visible Add User button not found");
    }
    public boolean isUserCreatedToasterDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            // Adjust class or text based on your app's toaster
            WebElement toaster = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'toast') and contains(text(),'User created successfully')]")
            ));
            return toaster.isDisplayed();
        } catch (TimeoutException e) {
            return false; // toaster didn't appear in time
        }
    }
}

//    public boolean isUserCreated(String username) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        try {
//            // Wait for the user to appear in the table
//            wait.until(ExpectedConditions.visibilityOfElementLocated(
//                    By.xpath("//td[contains(text(),'" + username + "')]")
//            ));
//            return true;
//        } catch (TimeoutException e) {
//            return false; // user not found within timeout
//        }
//    }}

//    public boolean isUserCreated() {
//        try {
//
//            By toastBy = By.xpath(
//                    "//div[contains(@class,'MuiAlert-message') and contains(.,'User')]"
//            );
//
//            WebElement toast = wait.until(
//                    ExpectedConditions.visibilityOfElementLocated(toastBy)
//            );
//
//            System.out.println("Toast message = " + toast.getText());
//
//            return true;
//
//        } catch (TimeoutException e) {
//            return false;
//        }
//    }
//}