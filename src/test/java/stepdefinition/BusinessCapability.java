package stepdefinition;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BusinessCapability {
    WebDriver driver;

    @Given("^User lands in creation page$")
    public void User_lands_in_creation_page(){
        driver = Hooks.driver;
        driver.get("http://3.131.133.70:3001/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement email = driver.findElement(By.cssSelector("input[placeholder='Email Address*']"));
        email.click();
        email.sendKeys("kishorekumar.b+admin@spritle.com");
        driver.findElement(By.cssSelector("input[placeholder='Password*']")).sendKeys("@Kichu010104");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div/form/div[3]/button")).click();
        WebElement dtoModelingTile = driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/div/div[2]/div[1]/div/div"));
        if (dtoModelingTile.isDisplayed()) {
            System.out.println("Tile displayed");
            dtoModelingTile.click();
        }
        driver.findElement(By.xpath("//button[normalize-space()='Continue']")).click();
    }

     @When("^User creates a new business capability$")
     public void User_creates_a_new_business_capability(){
        System.out.println(driver.getTitle());
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/div/div[3]/div[1]/div/div[1]/div[2]/div/div/ul/div[3]/div")).click();
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/div/div[3]/div[2]/div/div/div/div/div/button")).click();
        WebElement capabilityName = driver.findElement(By.name("name"));
        String capabilityNameValue = "Capability_Name" + System.currentTimeMillis();
        capabilityName.sendKeys(capabilityNameValue);
        WebElement capabilityOwner = driver.findElement(By.xpath("//div[@role='combobox']"));
        capabilityOwner.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement capabilityOwnerOptioins = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@role='option' and @data-value='29']")));
        capabilityOwnerOptioins.click();
        driver.findElement(By.name("description")).sendKeys("The world is full of red waves and roses.");
        WebElement lifecycleStageField = driver.findElement(By.xpath("(//div[@role='combobox'])[2]"));
        lifecycleStageField.click();
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement lifecycleStageOption = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@role='option']//span[normalize-space()='Active']")));
        lifecycleStageOption.click();
        driver.findElement(By.xpath("//button[normalize-space()='Add']")).click();
     }

     @Then("^User will see a success toaster message for capability creation$")
     public void User_will_see_a_success_toaster_message_for_capability_creation(){
        WebElement creationSuccessMessage = driver.findElement(By.xpath("//div[contains(@class,'MuiAlert-message') and contains(normalize-space(),'created successfully')]"));
        if (creationSuccessMessage.isDisplayed()) {
            System.out.println("Success message displayed");
        }
    }

    @When("^User updates the existing business capability$")
    public void User_updates_the_existing_business_capability(){
        driver.findElements(By.xpath("//"));
    
    }
}

