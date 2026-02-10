package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.JavaScriptHelper;
import utils.WaitUtil;
import utils.ElementActions;

public class ProductManagementPage {

    private WebDriver driver;
    private WaitUtil waitUtil;
    private ElementActions elementAction;
    private JavaScriptHelper js;
    private Actions actions;

    public ProductManagementPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtil = new WaitUtil(driver);
        this.elementAction = new ElementActions(driver);
        this.js = (JavaScriptHelper) driver;
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    // ================== ADD PRODUCT ==================

    @FindBy(xpath = "//button[normalize-space()='Add Product']")
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

    public void clickAddProduct() {
        elementAction.click(addProductButton);
        waitUtil.waitForVisible(createProductHeader);
    }

    public void verifyAddProductPopup() {
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

    public void enterParentReleaseVersion(String version) {
        waitUtil.waitForVisibleAndSendKeys(parentReleaseVersion, version);
    }

    public void selectParentProductType(String type) {
        selectFromDropdown(parentProductTypeDropdown, type);
    }

    public void selectParentCategory(String category) {
        waitUtil.scrollIntoView(parentCategoryDropdown);
        waitUtil.jsClick(parentCategoryDropdown);

        By option = By.xpath("//li[@role='option' and .//p[normalize-space()='Name: " + category + "']]");
        waitUtil.jsClick(waitUtil.waitForElement(option));
    }

    public void selectParentProductOwner(String owner) {
        waitUtil.scrollIntoView(parentProductOwnerDropdown);
        waitUtil.jsClick(parentProductOwnerDropdown);

        WebElement search =
                waitUtil.waitForElement(By.xpath("//input[contains(@placeholder,'Search')]"));
        search.sendKeys(owner);

        By option = By.xpath("//div[@role='option' and normalize-space()='" + owner + "']");
        waitUtil.jsClick(waitUtil.waitForElement(option));
    }

    public void selectParentLifecycleStage(String stage) {
        selectFromDropdown(parentLifecycleStageDropdown, stage);
    }

    public void selectParentTargetAudience(String audience) {
        selectFromDropdown(parentTargetAudienceDropdown, audience);
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

