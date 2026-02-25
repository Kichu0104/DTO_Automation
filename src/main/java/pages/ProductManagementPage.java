package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.JavaScriptHelper;
import utils.WaitUtil;
import utils.ElementActions;
import java.time.Duration;

public class ProductManagementPage {

    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    WaitUtil waitUtil;
    ElementActions elementAction;
    JavaScriptHelper jsHelper;

    public ProductManagementPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.js = (JavascriptExecutor) driver;
        waitUtil = new WaitUtil(driver);
        elementAction = new ElementActions(driver);
        jsHelper = new JavaScriptHelper(driver);

        PageFactory.initElements(driver, this);
    }

    // ================== ADD PRODUCT ==================

    @FindBy(xpath = "//span[normalize-space()='Products']/ancestor::div[@role='button']")
    private WebElement productMenu;

    @FindBy(xpath = "//button[contains(normalize-space(.), 'Add Product')]")
    private WebElement addProductButton;

    @FindBy(xpath = "//h2[normalize-space()='Create New Product']")
    private WebElement createProductHeader;

    @FindBy(xpath = "//button[normalize-space()='Create']")
    private WebElement createButton;

    @FindBy(name = "full_name")
    private WebElement parentProductName;

    @FindBy(name = "alias_name")
    private WebElement parentAliasName;

    @FindBy(name = "description")
    private WebElement parentDescription;

    @FindBy(name = "release_version")
    private WebElement parentReleaseVersion;

    @FindBy(xpath = "//p[contains(normalize-space(),'Product Type')]/ancestor::div[contains(@class,'MuiFormControl-root')]//div[@role='combobox']")
    private WebElement parentProductTypeDropdown;

    @FindBy(xpath = "(//div[contains(@class,'MuiSelect-root')]//div[@role='combobox'])[2]")
    private WebElement parentCategoryDropdown;

    @FindBy(xpath = "//p[contains(normalize-space(),'Product Owner')]/following::div[@role='combobox'][1]")
    private WebElement parentProductOwnerDropdown;

    @FindBy(xpath = "(//div[@role='combobox'])[4]")
    private WebElement parentLifecycleStageDropdown;

    @FindBy(xpath = "(//div[@role='combobox'])[5]")
    private WebElement parentTargetAudienceDropdown;

    // ---------- ADD PRODUCT ACTIONS ----------
    public void selectProductFromSideMenu() {
        productMenu.click();
        // wait for page to load by waiting for ANY stable element
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//h4[contains(.,'Products')]")));
    }
//        public void clickAddProduct() {
//        elementAction.click(addProductButton);
//        waitUtil.waitForVisible(createProductHeader);
//    }

    public void clickAddProduct() {
        productMenu.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(., 'Add Product')]")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(., 'Add Product')]"))).click();
    }

    public void verifyAddProductPopup()
    {
        waitUtil.waitForVisible(createProductHeader);
    }

    public void enterParentProductName(String name) {
        waitUtil.waitForVisibleAndSendKeys(parentProductName, name);
    }

    public void enterParentAliasName(String alias) {

        waitUtil.waitForVisibleAndSendKeys(parentAliasName, alias);
    }

    public void enterParentDescription(String desc) {
        waitUtil.waitForVisibleAndSendKeys(parentDescription, desc);
    }

    public void selectParentProductType(String value) {
        wait.until(ExpectedConditions.elementToBeClickable(parentProductTypeDropdown)).click();
        By option = By.xpath("//li[@role='option']//span[normalize-space()='" + value + "']" +
                " | //li[@role='menuitem']//span[normalize-space()='" + value + "']");
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }

    public void selectParentCategory(String value) {
        wait.until(ExpectedConditions.elementToBeClickable(parentCategoryDropdown)).click();
        By option = By.xpath("//li[@role='option']//*[contains(normalize-space(.), '" + value + "')] " +
                "| //div[@role='option']//*[contains(normalize-space(.), '" + value + "')]");
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }
    public void selectParentProductOwner(String ownerName) {

        By dropdownLocator = By.xpath("(//div[@role='combobox'])[1]");
        // adjust index if needed

        WebElement dropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(dropdownLocator)
        );

        jsHelper.scrollIntoView(dropdown);
        jsHelper.clickElement(dropdown);

        // ✅ CRITICAL CHECK → did dropdown open?
        By listboxLocator = By.xpath("//ul[@role='listbox']");

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(listboxLocator));
        } catch (Exception e) {
            throw new RuntimeException("Dropdown did NOT open — locator or overlay issue");
        }

        // ✅ Only runs if dropdown truly opened
        By searchLocator = By.xpath("//ul[@role='listbox']//input");

        WebElement searchInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchLocator)
        );

        searchInput.clear();
        searchInput.sendKeys(ownerName);

        By optionLocator = By.xpath(
                "//li[@role='option']//p[normalize-space()='" + ownerName + "']"
        );

        WebElement option = wait.until(
                ExpectedConditions.visibilityOfElementLocated(optionLocator)
        );

        jsHelper.scrollIntoView(option);
        jsHelper.clickElement(option);
    }

    public void selectParentLifecycleStage(String value) {
        wait.until(ExpectedConditions.elementToBeClickable(parentLifecycleStageDropdown)).click();
        By option = By.xpath("//*[contains(@role,'option') and contains(normalize-space(.), '" + value + "')]");
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }

    public void enterParentReleaseVersion(String version) {
        waitUtil.waitForVisibleAndSendKeys(parentReleaseVersion, version);
    }

    public void selectParentTargetAudience(String value) {
        wait.until(ExpectedConditions.elementToBeClickable(parentTargetAudienceDropdown)).click();
        By option = By.xpath("//*[contains(@role,'option') and contains(normalize-space(.), '" + value + "')]");
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }

    public void clickCreate() {
        waitUtil.scrollIntoView(createButton);
        elementAction.click(createButton);
    }

    public void verifyProductCreated(String product) {
        By toast = By.xpath("//div[contains(@class,'MuiAlert-message') and contains(text(),'" + product + "')]");
        waitUtil.waitForElement(toast);
    }

    // ================== VIEW PRODUCT ==================

    @FindBy(xpath = "//span[normalize-space()='Products']")
    private WebElement productsMenu;

    @FindBy(xpath = "(//table//tbody//tr)[1]//button[.//*[name()='svg']]")
    private WebElement firstProductViewIcon;

    @FindBy(xpath = "//h6[normalize-space()='Product Details']")
    private WebElement productDetailsTitle;

    public void clickProductsMenu() {
        elementAction.click(productsMenu);
    }

    public void clickFirstProductViewIcon() {
        waitUtil.scrollIntoView(firstProductViewIcon);
        waitUtil.jsClick(firstProductViewIcon);
    }

    public boolean isProductDetailsDisplayed() {
        return productDetailsTitle.isDisplayed();
    }

    // ================== ADD CHILD PRODUCT ==================

    @FindBy(xpath = "//button[contains(.,'Add Child')]")
    private WebElement addChildProductButton;

    @FindBy(xpath = "//h2[contains(text(),'Add Child Product')]")
    private WebElement addChildProductPopupTitle;

    @FindBy(name = "full_name")
    private WebElement childFullName;

    @FindBy(name = "alias_name")
    private WebElement childAliasName;

    @FindBy(name = "description")
    private WebElement childDescription;

    @FindBy(xpath = "//p[contains(text(),'Product Type')]/following::div[@role='combobox'][1]")
    private WebElement childProductTypeDropdown;

    @FindBy(xpath = "//p[contains(text(),'Product Owner')]/following::div[@role='combobox'][1]")
    private WebElement childProductOwnerDropdown;

    @FindBy(name = "release_version")
    private WebElement childReleaseVersion;

    @FindBy(xpath = "//p[contains(text(),'Lifecycle Stage')]/following::div[@role='combobox'][1]")
    private WebElement childLifecycleStageDropdown;

    @FindBy(xpath = "//p[contains(text(),'Target Audience')]/following::div[@role='combobox'][1]")
    private WebElement childTargetAudienceDropdown;

    @FindBy(xpath = "//button[normalize-space()='Save']")
    private WebElement saveChildProductBtn;


    //------------------ADD CHILD ACTIONS-------------------
// ----------------- ADD CHILD PRODUCT -----------------
    public void openParentProduct(String productName) {
        By expand = By.xpath("//tr[td[normalize-space()='" + productName + "']]//button[.//*[local-name()='svg']]");
        waitUtil.jsClick(waitUtil.waitForElement(expand));
    }

    public void clickViewIconForProduct(String productCode) {
        By viewIcon = By.xpath("//tr[td[normalize-space()='" + productCode + "']]//button[.//*[contains(@title,'View')]]");
        waitUtil.jsClick(waitUtil.waitForElement(viewIcon));
    }

    public void clickAddChildProduct() {
        elementAction.click(addChildProductButton);
    }

    public boolean isAddChildProductPopupVisible() {
        try {
            waitUtil.waitForVisible(addChildProductPopupTitle);
            return addChildProductPopupTitle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterChildFullName(String name) {
        waitUtil.waitForVisibleAndSendKeys(childFullName, name);
    }

    public void enterChildAliasName(String alias) {
        waitUtil.waitForVisibleAndSendKeys(childAliasName, alias);
    }

    public void enterChildDescription(String desc) {
        waitUtil.waitForVisibleAndSendKeys(childDescription, desc);
    }

    public void selectChildProductType(String type) {
        selectFromDropdown(childProductTypeDropdown, type);
    }

    public void selectChildProductOwner(String owner) {
        selectFromDropdown(childProductOwnerDropdown, owner);
    }

    public void enterChildReleaseVersion(String version) {
        waitUtil.waitForVisibleAndSendKeys(childReleaseVersion, version);
    }

    public void selectChildProductLifecycleStage(String stage) {
        selectFromDropdown(childLifecycleStageDropdown, stage);
    }

    public void selectChildProductTargetAudience(String audience) {
        selectFromDropdown(childTargetAudienceDropdown, audience);
    }

    public void clickSaveChildProduct() {
        waitUtil.scrollIntoView(saveChildProductBtn);
        elementAction.click(saveChildProductBtn);
    }

    public boolean verifyChildProductCreated(String name) {
        try {
            By toast = By.xpath("//div[contains(@class,'MuiAlert-message') and contains(text(),'" + name + "')]");
            waitUtil.waitForElement(toast);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ----------------- COMMON DROPDOWN -----------------
    private void selectFromDropdown(WebElement dropdown, String value) {
        waitUtil.scrollIntoView(dropdown);
        waitUtil.jsClick(dropdown);
        By option = By.xpath("//li[@role='option' and .//span[normalize-space()='" + value + "']]");
        waitUtil.jsClick(waitUtil.waitForElement(option));
    }
}

