import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;

public class MultiselectTest {
    private WebDriver driver;

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
    @Tag("Multiselect")
    void multiselectTest() {
        boolean result = false;
        driver.get("https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html");
        WebElement multiSelectForm = driver.findElement(By.id("multi-select"));
        Select selectObject = new Select(multiSelectForm);
        selectObject.selectByIndex(2);
        selectObject.selectByValue("Texas");
        selectObject.selectByVisibleText("Washington");
        List<WebElement> selectedOptions = selectObject.getAllSelectedOptions();
        for (int i = 0; i < selectedOptions.size(); i++) {
            result = selectedOptions.get(i).getText().contains("New Jersey") || selectedOptions.get(i).getText().contains("Texas")
                                                                        || selectedOptions.get(i).getText().contains("Washington");
            if (result == false) {break;}
        }
       assertTrue(result, "Not all elements or incorrect elements were selected failed at point");
        }
    }
