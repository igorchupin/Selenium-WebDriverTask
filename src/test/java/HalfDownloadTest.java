import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class HalfDownloadTest {
    WebDriverWait wait;
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
        wait = new WebDriverWait(driver, 20);
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
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                WebElement loadingPercent = driver.findElement(By.xpath("//div[@class='percenttext']"));
                stringPercent = loadingPercent.getText();
                percent = Integer.valueOf(stringPercent.substring(0, stringPercent.length() - 1));
                if (percent >= 50) return true;
                else return false;
            }
        }
      );
        driver.navigate().refresh();
    }
}
