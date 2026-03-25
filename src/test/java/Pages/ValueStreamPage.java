package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import stepdefinition.Hooks;

public class ValueStreamPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private String createdValueStreamName;

    private static final String BASE_URL = "https://development.insighttwin.com/";
    private static final String EMAIL = "kishorekumar.b+admin@spritle.com";
    private static final String PASSWORD = "@Kichu010104";
    private static final long EXPLICIT_WAIT_SECONDS = 30;

    // Locators extracted as constants based on Page Object Model best practices
    private static final By EMAIL_FIELD = By.cssSelector("input[placeholder='Email Address*']");
    private static final By PASSWORD_FIELD = By.cssSelector("input[placeholder='Password*']");
    private static final By LOGIN_BUTTON = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/form/div[3]/button");
    private static final By DTO_MODELING_TILE = By.xpath("//h6[normalize-space()='DTO Modeling']");
    private static final By CONTINUE_BUTTON = By.xpath("//button[normalize-space()='Continue']");

    private static final By VALUE_STREAM_MENU = By
            .xpath("//div[contains(@class,'MuiButtonBase-root') and .//p[normalize-space()='Value Stream']]");
    private static final By FOLDER_NODE = By.xpath("(//div[@data-node-id])[1]");
    private static final By THREE_DOT_SMALL_BTN = By.xpath(
            ".//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeSmall css-1phdkqw']");

    private static final By CATEGORY_NAME_INPUT = By.xpath(".//input[@autocomplete='new-password']");
    private static final By CATEGORY_OWNER_DROPDOWN = By
            .xpath(".//div[@role='combobox']");
    private static final By CATEGORY_OWNER_OPTION = By.xpath("(.//li[@role='menuitem'])[1]");

    private static final By DESCRIPTION_TEXTAREA = By.xpath(".//textarea[@name='description']");

    private static final By ADD_CATEGORY = By.xpath(".//div[@data-node-id='add-new']");
    private static final By ADD_BUTTON = By.xpath(".//button[@type='button' and text()='Add']");

    private static final By OPEN_MENUS_BOX = By.xpath("//div[@class='MuiBox-root css-xdo965']");
    private static final By SCROLL_CONTAINER = By.xpath("//div[contains(@class,'css-xdo965')]");
    private static final By DATA_NODE_ROWS = By.xpath("//div[@data-node-id]");
    public static final By THREE_DOT_BTN_GENERAL = By.xpath(".//button[contains(@class,'MuiIconButton-root')]");
    private static final By EDIT_VALUE_STREAM_OPTION = By.xpath("//li[@role='menuitem' and contains(.,'Edit')]");
    private static final By UPDATE_BUTTON = By.xpath(".//button[@type='button' and text()='Update']");
    private static final By DELETE_VALUE_STREAM_OPTION = By.xpath("//li[@role='menuitem' and contains(.,'Delete')]");
    private static final By CONFIRM_DELETE_BUTTON = By.xpath(".//button[@type='button' and text()='Continue']");
    private static final By VALUE_STREAM_NAME_INPUT = By.xpath("//input[@name='valueStreamName']");
    private static final By VALUE_STREAM_DESCRIPTION_TEXTAREA = By.xpath("//textarea[@name='description']");
    private static final By VALUE_STREAM_OWNER_DROPDOWN = By.xpath("//div[@role='combobox']");
    private static final By VALUE_STREAM_OWNER_OPTION = By.xpath("(.//li[@role='menuitem'])[1]");
    private static final By VALUE_STREAM_ADD_BUTTON = By.xpath(".//button[@type='button' and text()='Add']");

    public ValueStreamPage() {
        this.driver = Hooks.driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_SECONDS));
    }

    public void selectDTOModelingTile() {
        driver.get(BASE_URL);

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(EMAIL_FIELD));
        emailField.click();
        emailField.sendKeys(EMAIL);

        driver.findElement(PASSWORD_FIELD).sendKeys(PASSWORD);
        driver.findElement(LOGIN_BUTTON).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(DTO_MODELING_TILE)).click();
        List<WebElement> continueBtns = driver.findElements(CONTINUE_BUTTON);
        if (!continueBtns.isEmpty() && continueBtns.get(0).isDisplayed()) {
            wait.until(ExpectedConditions.elementToBeClickable(continueBtns.get(0))).click();
        }
    }

    public void clickThreeDotForFirstValueStream() throws InterruptedException {
        System.out.println(driver.getTitle());

        wait.until(ExpectedConditions.presenceOfElementLocated(VALUE_STREAM_MENU)).click();
        System.out.println("Value Stream Menu Clicked");

        WebElement folder = wait.until(ExpectedConditions.presenceOfElementLocated(FOLDER_NODE));
        System.out.println(folder.getText());

        WebElement threeDotBtn = folder.findElement(THREE_DOT_SMALL_BTN);
        wait.until(ExpectedConditions.elementToBeClickable(threeDotBtn)).click();
        System.out.println("Folder Clicked");
    }

    public void clickValueStreamButton() {
        wait.until(ExpectedConditions.elementToBeClickable(VALUE_STREAM_MENU)).click();
        System.out.println("Value Stream Menu Clicked");
        wait.until(ExpectedConditions.visibilityOfElementLocated(ADD_CATEGORY)).click();
        System.out.println("Add Value Stream Button Clicked");
    }

    public void createCategory() {
        WebElement categoryNameField = wait
                .until(ExpectedConditions.visibilityOfElementLocated(CATEGORY_NAME_INPUT));
        this.createdValueStreamName = "Category " + System.currentTimeMillis();
        categoryNameField.sendKeys(this.createdValueStreamName);
        System.out.println("Category Name Entered");

        wait.until(ExpectedConditions.visibilityOfElementLocated(CATEGORY_OWNER_DROPDOWN)).click();
        System.out.println("Category Clicked");

        wait.until(ExpectedConditions.visibilityOfElementLocated(CATEGORY_OWNER_OPTION)).click();
        System.out.println("Category Selected");

        wait.until(ExpectedConditions.visibilityOfElementLocated(DESCRIPTION_TEXTAREA))
                .sendKeys("Category Description");
        System.out.println("Category Description Entered");

        wait.until(ExpectedConditions.elementToBeClickable(ADD_BUTTON)).click();
        System.out.println("Category created successfully");
    }

    public void threeDotOfLastValueStream() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        List<WebElement> openMenus = driver.findElements(OPEN_MENUS_BOX);

        if (!openMenus.isEmpty()) {
            driver.findElement(By.tagName("body")).click();
            Thread.sleep(500);
        }

        WebElement scrollContainer = wait
                .until(ExpectedConditions.visibilityOfElementLocated(SCROLL_CONTAINER));

        js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", scrollContainer);

        wait.until(ExpectedConditions.presenceOfElementLocated(DATA_NODE_ROWS));

        List<WebElement> rows = driver.findElements(DATA_NODE_ROWS);

        if (rows.isEmpty()) {
            throw new RuntimeException("No value stream rows found!");
        }

        WebElement lastRow = rows.get(rows.size() - 1);
        WebElement threeDotBtn = lastRow.findElement(THREE_DOT_BTN_GENERAL);

        wait.until(ExpectedConditions.elementToBeClickable(threeDotBtn)).click();

    }

    public void editValueStream() throws InterruptedException {
        WebElement editValueStreamOption = wait
                .until(ExpectedConditions.visibilityOfElementLocated(EDIT_VALUE_STREAM_OPTION));
        editValueStreamOption.click();
        System.out.println("Edit Value Stream Option Clicked");
        WebElement updateDescription = wait
                .until(ExpectedConditions.visibilityOfElementLocated(DESCRIPTION_TEXTAREA));
        updateDescription.clear();
        updateDescription.sendKeys("Updated Description");
        System.out.println("New Description Entered");

        WebElement updateButton = wait.until(ExpectedConditions.elementToBeClickable(UPDATE_BUTTON));
        updateButton.click();
        System.out.println("Description Updated Successfully");
    }

    public void addValueStream() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        List<WebElement> openMenus = driver.findElements(OPEN_MENUS_BOX);

        if (!openMenus.isEmpty()) {
            driver.findElement(By.tagName("body")).click();
            Thread.sleep(500);
        }

        WebElement scrollContainer = wait
                .until(ExpectedConditions.visibilityOfElementLocated(SCROLL_CONTAINER));

        js.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", scrollContainer);
        Thread.sleep(1000); // Allow time for elements to load/render

        wait.until(ExpectedConditions.presenceOfElementLocated(DATA_NODE_ROWS));

        List<WebElement> rows = driver.findElements(DATA_NODE_ROWS);
        WebElement targetRow = null;

        for (WebElement row : rows) {
            if (row.getText().contains(this.createdValueStreamName)) {
                targetRow = row;
                break;
            }
        }

        if (targetRow == null) {
            throw new RuntimeException("Validation failed: Created category '"
                    + this.createdValueStreamName + "' not found to add value stream!");
        }

        System.out.println("Category '" + this.createdValueStreamName + "' found.");

        WebElement threeDotBtn = targetRow.findElement(THREE_DOT_BTN_GENERAL);

        wait.until(ExpectedConditions.elementToBeClickable(threeDotBtn)).click();
        System.out.println("Three Dot Button Clicked for " + this.createdValueStreamName);

        // Click the first menu item which corresponds to Add Value Stream under the
        // category
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//li[@role='menuitem'])[2]"))).click();
        System.out.println("Add Value Stream Option Clicked");

        WebElement valueStreamNameField = wait
                .until(ExpectedConditions.visibilityOfElementLocated(VALUE_STREAM_NAME_INPUT));
        this.createdValueStreamName = "Value Stream " + System.currentTimeMillis();
        valueStreamNameField.sendKeys(this.createdValueStreamName);
        System.out.println("Value Stream Name Entered");

        wait.until(ExpectedConditions.visibilityOfElementLocated(VALUE_STREAM_OWNER_DROPDOWN)).click();
        System.out.println("Category Owner Clicked");

        wait.until(ExpectedConditions.visibilityOfElementLocated(VALUE_STREAM_OWNER_OPTION)).click();
        System.out.println("Category Owner Selected");

        wait.until(ExpectedConditions.visibilityOfElementLocated(VALUE_STREAM_DESCRIPTION_TEXTAREA))
                .sendKeys("Value Stream Description");
        System.out.println("Value Stream Description Entered");

        wait.until(ExpectedConditions.elementToBeClickable(VALUE_STREAM_ADD_BUTTON)).click();
        System.out.println("Value Stream created successfully");
    }

    public void deleteValueStream() throws InterruptedException {
        try {
            WebElement deleteOption = wait.until(ExpectedConditions.elementToBeClickable(DELETE_VALUE_STREAM_OPTION));
            if (deleteOption.isEnabled()) {
                deleteOption.click();
                System.out.println("Delete Value Stream Option Clicked");
            } else {
                return;
            }

            WebElement confirmDeleteButton = wait.until(ExpectedConditions.elementToBeClickable(CONFIRM_DELETE_BUTTON));
            confirmDeleteButton.click();
            System.out.println("Value Stream Deleted Successfully");
        } catch (Exception e) {
            System.err.println("Error deleting last value stream: " + e.getMessage());
        }
    }
}
