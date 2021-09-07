import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class WaitsForUserTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    public static void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @AfterEach
    public void afterTest() {
        driver.quit();
    }

    @Test
    @Tag("Wait_For_User")
    @DisplayName("Wait for user test")
    void waitForUserTest()  {
        driver.get("https://www.seleniumeasy.com/test/dynamic-data-loading-demo.html");
        WebElement getNewUserButton = driver.findElement(By.id("save"));
        getNewUserButton.click();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='loading']/img")));
        wait.until(ExpectedConditions.and(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='loading']/img")),
                ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//div[@id='loading']")),"First Name :"),
                ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//div[@id='loading']")),"Last Name :")));
    }
}


