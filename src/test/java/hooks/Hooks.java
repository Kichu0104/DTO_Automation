//package hooks;
//
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import io.github.bonigarcia.wdm.WebDriverManager;
//
//public class Hooks {
//
//    private static WebDriver driver;
//
//    @Before
//    public void setUp() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get("http://3.131.133.70:3001/login");
//    }
//
//    @After
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//
//    public static WebDriver getDriver() {
//        return driver;
//    }
//}

package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {

    private static WebDriver driver;

    // ================== BEFORE HOOK ==================
    @Before(order = 0)
    public void setUp() {
        if (driver == null) { // Ensure driver is created only once
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        driver.get("http://3.131.133.70:3001/login"); // Navigate to login page
    }

    // ================== AFTER HOOK ==================
//    @After(order = 0)
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//            driver = null; // Reset driver so next scenario starts fresh
//        }
//    }

    // ================== GET DRIVER ==================
    public static WebDriver getDriver() {
        if (driver == null) {
            throw new RuntimeException("Driver is not initialized. Did @Before hook run?");
        }
        return driver;
    }
}

