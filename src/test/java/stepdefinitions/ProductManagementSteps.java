package stepdefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import hooks.Hooks;
import pages.ProductManagementPage;
import utils.WaitUtil;


public class ProductManagementSteps {

    private WebDriver driver;
    private ProductManagementPage productPage;

    public ProductManagementSteps() {
        this.driver = Hooks.getDriver();
        this.productPage = new ProductManagementPage(driver);
    }

    // ================== ADD PRODUCT ==================
    @When("I click on Add Product")
    public void i_click_on_add_product() {
        productPage.clickAddProduct();
    }

    @Then("the Add Product popup should be displayed")
    public void the_add_product_popup_should_be_displayed() {
        productPage.verifyAddProductPopup();
    }

    @When("I enter product name {string}")
    public void i_enter_product_name(String name) {
        productPage.enterParentProductName(name);
    }

    @When("I enter product code {string}")
    public void i_enter_product_code(String code) {
        productPage.enterParentAliasName(code);
    }

    @When("I enter product description {string}")
    public void i_enter_product_description(String desc) {
        productPage.enterParentDescription(desc);
    }

    @When("I select the product type {string}")
    public void select_product_type(String type) {
        productPage.selectParentProductType(type);
    }

    @When("I select the product parent category {string}")
    public void select_product_parent_category(String category) {
        productPage.selectParentCategory(category);
    }

    @When("I select the product owner {string}")
    public void select_product_owner(String owner) {
        productPage.selectParentProductOwner(owner);
    }

    @When("I enter the release version {string}")
    public void enter_release_version(String version) {
        productPage.enterParentReleaseVersion(version);
    }

    @When("I select the product lifecycle stage {string}")
    public void select_product_lifecycle_stage(String stage) {
        productPage.selectParentLifecycleStage(stage);
    }

    @When("I select the target audience {string}")
    public void select_target_audience(String audience) {
        productPage.selectParentTargetAudience(audience);
    }

    @When("I click on the Create product button")
    public void i_click_on_create_product_button() {
        productPage.clickCreate();
    }

    @Then("the product {string} should be created successfully")
    public void the_product_should_be_created_successfully(String product) {
        productPage.verifyProductCreated(product);
    }

    // ---------------------VIEW PRODUCT----------------
    @When("I select Products from the side menu")
    public void i_select_products_from_side_menu() {
        productPage.clickProductsMenu();
    }

    @And("I click on the view icon for the first product")
    public void i_click_on_the_view_icon_for_the_first_product() {
        productPage.clickFirstProductViewIcon();
    }

    @Then("the product details should be displayed")
    public void product_details_should_be_displayed() {
        Assert.assertTrue(productPage.isProductDetailsDisplayed());
    }

    // ================== ADD CHILD PRODUCT ==================
    @When("I expand the parent product with ID {string}")
    public void i_expand_the_parent_product_with_id(String id) {
        productPage.openParentProduct(id);
    }

    @And("I click on the view icon for product {string}")
    public void i_click_on_the_view_icon_for_product(String productCode) {
        productPage.clickViewIconForProduct(productCode);
    }

    @And("I click on Add Child Product")
    public void i_click_on_add_child_product() {
        productPage.clickAddChildProduct();
    }


    @Then("the Add Child Product popup should be displayed")
    public void the_add_child_product_popup_should_be_displayed() {
        Assert.assertTrue(productPage.isAddChildProductPopupVisible());
    }


    @When("I enter child product full name {string}")
    public void enter_child_full_name(String name) {
        productPage.enterChildFullName(name);
    }

    @And("I enter child alias {string}")
    public void enter_child_alias(String alias) {
        productPage.enterChildAliasName(alias);
    }

    @And("I enter child product description {string}")
    public void enter_child_description(String desc) {
        productPage.enterChildDescription(desc);
    }

    @And("I select the child product type {string}")
    public void select_child_product_type(String type) {
        productPage.selectChildProductType(type);
    }

    @And("I select the child product owner {string}")
    public void select_child_product_owner(String owner) {
        productPage.selectChildProductOwner(owner);
    }

    @And("I enter the child release version {string}")
    public void enter_child_release_version(String version) {
        productPage.enterChildReleaseVersion(version);
    }

    @And("I select the child product lifecycle stage {string}")
    public void select_child_lifecycle_stage(String stage) {
        productPage.selectChildProductLifecycleStage(stage);
    }

    @And("I select the child target audience {string}")
    public void select_child_target_audience(String audience) {

        productPage.selectChildProductTargetAudience(audience);
    }

    @And("I click on Save product")
    public void click_save_child_product() {

        productPage.clickSaveChildProduct();
    }

    @Then("the child product {string} should be created successfully")
    public void verify_child_product_created(String childFullName) {
        if (!productPage.verifyChildProductCreated(childFullName)) {
            throw new AssertionError("Child product not created successfully");
        }
    }

}





