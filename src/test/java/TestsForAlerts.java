import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestsForAlerts {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private final String keysForSend = "Hello!!!";


    @BeforeAll
    public static void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://www.seleniumeasy.com/test/javascript-alert-box-demo.html");
        wait = new WebDriverWait(driver, 10);
    }

    @AfterAll
    public static void afterTest() {
        driver.quit();
    }

    @Test
    @Tag("Java_Script_Confirm_Box_Text_And_Approve")
    @DisplayName("Java_Script_Confirm_Box_Text_And_Approve")
    void JavaScriptConfirmBoxTextApproveTest() {
        WebElement buttonConfirm = driver.findElement(By.xpath("//button[@onclick='myConfirmFunction()']"));
        buttonConfirm.click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        alert.accept();

        assertAll("Results",
                () -> assertEquals("You pressed OK!", driver.findElement(By.id("confirm-demo")).getText(),
                        "Alert was not accepted"),
                () -> assertEquals("Press a button!", alertText, "Alert has incorrect text")
        );
    }

    @Test
    @Tag("Java_Script_Confirm_Box_Text_And_Cancel")
    @DisplayName("Java_Script_Confirm_Box_Text_And_Cancel")
    void JavaScriptConfirmBoxTextCancelTest() {
        WebElement buttonCConfirm = driver.findElement(By.xpath("//button[@onclick='myConfirmFunction()']"));
        buttonCConfirm.click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.dismiss();

        assertEquals("You pressed Cancel!", driver.findElement(By.id("confirm-demo")).getText(),
                "Alert was not canceled");
    }

    @Test
    @Tag("Java_Script_Alert_Box_Text_and_Entering")
    @DisplayName("Java Script Alert Box Text and Entering")
    void JavaScriptAlertBoxTest() {
        WebElement buttonCConfirm = driver.findElement(By.xpath("//button[@onclick='myPromptFunction()']"));
        buttonCConfirm.click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        alert.sendKeys(keysForSend);
        alert.accept();
        String expectedResult = String.format("You have entered '%s' !", keysForSend);

        assertAll("Results",
                () -> assertEquals(expectedResult, driver.findElement(By.id("prompt-demo")).getText(),
                        "Incorrect keys were sent!"),
                () -> assertEquals("Please enter your name", alertText, "Alert has incorrect text")
        );
    }

}
