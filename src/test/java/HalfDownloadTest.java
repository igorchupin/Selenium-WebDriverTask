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

public class HalfDownloadTest {
    private WebDriver driver;
    private Integer percent;
    private String stringPercent;

    @BeforeAll
    public static void beforeClass() {

        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeTest() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterEach
    public void afterTest() {
        driver.quit();
    }

    @Test
    @Tag("Half_Download_Test")
    @DisplayName("Half download test")
    void halfDownloadTest() {
        driver.get("https://www.seleniumeasy.com/test/bootstrap-download-progress-demo.html");
        WebElement downloadButton = driver.findElement(By.id("cricle-btn"));
        downloadButton.click();
        do {
            WebElement loadingPercent = driver.findElement(By.xpath("//div[@class='percenttext']"));
            stringPercent = loadingPercent.getText();
            percent = Integer.valueOf(stringPercent.substring(0, stringPercent.length()-1));
        }
        while (percent <=50);
        driver.navigate().refresh();
    }
}
