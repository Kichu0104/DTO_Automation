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

public class ProcessInventoryPage {
        private WebDriver driver;
        private WebDriverWait wait;
        private String createdProcessAreaName;

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

        private static final By PROCESS_INVENTORY_MENU = By
                        .xpath("//p[contains(@class,'MuiTypography-body1') and text()='Process Inventory']");
        private static final By FOLDER_NODE = By.xpath("(//div[@data-node-id])[1]");
        private static final By THREE_DOT_SMALL_BTN = By.xpath(
                        ".//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeSmall css-1phdkqw']");

        private static final By ANY_MENU = By.xpath("//ul[@role='menu']");
        private static final By ADD_PROCESS_AREA_ITEM = By.xpath("(//li[@role='menuitem'])[1]");

        private static final By PROCESS_AREA_NAME_INPUT = By.xpath(".//input[@name='processAreaName']");
        private static final By PROCESS_AREA_OWNER_DROPDOWN = By
                        .xpath("(//div[@role='combobox' and @aria-haspopup='listbox'])[2]");
        private static final By PROCESS_AREA_OWNER_OPTION = By.xpath("(//div[@role='option'])[2]");

        private static final By DESCRIPTION_TEXTAREA = By.xpath(".//textarea[@name='description']");

        private static final By ORG_UNIT_DROPDOWN = By
                        .xpath("(//div[@role='combobox' and @aria-haspopup='listbox'])[3]");
        private static final By ORG_UNIT_OPTION = By.xpath("(.//li[@role='menuitem'])[1]");

        private static final By ADD_BUTTON = By.xpath(".//button[@type='button' and text()='Add']");

        private static final By OPEN_MENUS_BOX = By.xpath("//div[@class='MuiBox-root css-xdo965']");
        private static final By SCROLL_CONTAINER = By.xpath("//div[contains(@class,'css-xdo965')]");
        private static final By DATA_NODE_ROWS = By.xpath("//div[@data-node-id]");
        public static final By THREE_DOT_BTN_GENERAL = By.xpath(".//button[contains(@class,'MuiIconButton-root')]");
        private static final By EDIT_PROCESS_AREA_OPTION = By.xpath("(//li[@role='menuitem'])[3]");
        private static final By UPDATE_BUTTON = By.xpath(".//button[@type='button' and text()='Update']");
        private static final By DELETE_PROCESS_AREA_OPTION = By.xpath("(.//li[@role='menuitem'])[4]");
        private static final By CONFIRM_DELETE_BUTTON = By.xpath(".//button[@type='button' and text()='Continue']");

        public ProcessInventoryPage() {
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

        public void clickThreeDotForFirstProcessArea() throws InterruptedException {
                System.out.println(driver.getTitle());

                wait.until(ExpectedConditions.presenceOfElementLocated(PROCESS_INVENTORY_MENU)).click();
                System.out.println("Process Inventory Menu Clicked");

                WebElement folder = wait.until(ExpectedConditions.presenceOfElementLocated(FOLDER_NODE));
                System.out.println(folder.getText());

                WebElement threeDotBtn = folder.findElement(THREE_DOT_SMALL_BTN);
                wait.until(ExpectedConditions.elementToBeClickable(threeDotBtn)).click();
                System.out.println("Folder Clicked");
        }

        public void clickAddProcessAreaButton() {
                List<WebElement> openMenus = driver.findElements(ANY_MENU);
                System.out.println(openMenus.size());

                wait.until(ExpectedConditions.elementToBeClickable(ADD_PROCESS_AREA_ITEM)).click();
                System.out.println("Add Process Area Button Clicked");
        }

        public void createProcessArea() {
                WebElement processAreaNameField = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(PROCESS_AREA_NAME_INPUT));
                this.createdProcessAreaName = "Inventory Area " + System.currentTimeMillis();
                processAreaNameField.sendKeys(this.createdProcessAreaName);
                System.out.println("Process Area Name Entered");

                wait.until(ExpectedConditions.visibilityOfElementLocated(PROCESS_AREA_OWNER_DROPDOWN)).click();
                System.out.println("Process Area Owner Clicked");

                wait.until(ExpectedConditions.visibilityOfElementLocated(PROCESS_AREA_OWNER_OPTION)).click();
                System.out.println("Process Area Owner Selected");

                wait.until(ExpectedConditions.visibilityOfElementLocated(DESCRIPTION_TEXTAREA))
                                .sendKeys("Process Description");
                System.out.println("Process Description Entered");

                wait.until(ExpectedConditions.visibilityOfElementLocated(ORG_UNIT_DROPDOWN)).click();
                System.out.println("Organization Unit Clicked");

                wait.until(ExpectedConditions.visibilityOfElementLocated(ORG_UNIT_OPTION)).click();
                System.out.println("Organization Unit Selected");

                wait.until(ExpectedConditions.elementToBeClickable(ADD_BUTTON)).click();
                System.out.println("Process Area created successfully");
        }

        public void threeDotOfLastProcess() throws InterruptedException {
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
                        throw new RuntimeException("No capability rows found!");
                }

                WebElement lastRow = rows.get(rows.size() - 1);
                WebElement threeDotBtn = lastRow.findElement(THREE_DOT_BTN_GENERAL);

                wait.until(ExpectedConditions.elementToBeClickable(threeDotBtn)).click();

        }

        public void editProcessArea() throws InterruptedException {
                WebElement editProcessAreaOption = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(EDIT_PROCESS_AREA_OPTION));
                editProcessAreaOption.click();
                System.out.println("Edit Process Area Option Clicked");
                WebElement updateDescription = wait
                                .until(ExpectedConditions.visibilityOfElementLocated(DESCRIPTION_TEXTAREA));
                updateDescription.clear();
                updateDescription.sendKeys("Updated Description");
                System.out.println("New Description Entered");

                WebElement updateButton = wait.until(ExpectedConditions.elementToBeClickable(UPDATE_BUTTON));
                updateButton.click();
                System.out.println("Description Updated Successfully");
        }

        public void deleteProcessArea() throws InterruptedException {
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
                        if (row.getText().contains(this.createdProcessAreaName)) {
                                targetRow = row;
                                break;
                        }
                }

                if (targetRow == null) {
                        throw new RuntimeException("Validation failed: Created process area '"
                                        + this.createdProcessAreaName + "' not found!");
                }

                System.out.println("Validation passed: Process area '" + this.createdProcessAreaName + "' found.");

                WebElement threeDotBtn = targetRow.findElement(THREE_DOT_BTN_GENERAL);

                wait.until(ExpectedConditions.elementToBeClickable(threeDotBtn)).click();
                System.out.println("Three Dot Button Clicked for " + this.createdProcessAreaName);

                wait.until(ExpectedConditions.visibilityOfElementLocated(DELETE_PROCESS_AREA_OPTION)).click();
                System.out.println("Delete Process Area Option Clicked");
                WebElement confirmDeleteBtn = wait
                                .until(ExpectedConditions.elementToBeClickable(CONFIRM_DELETE_BUTTON));
                confirmDeleteBtn.click();
                System.out.println("Process deleted successfully");
        }
}