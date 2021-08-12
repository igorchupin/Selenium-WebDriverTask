import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

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

        driver.get("https://www.seleniumeasy.com/test/basic-select-dropdown-demo.html");
        WebElement multiSelectForm = driver.findElement(By.id("multi-select"));
        Select selectObject = new Select(multiSelectForm);
        selectObject.selectByIndex(2);
        selectObject.selectByValue("Texas");
        selectObject.selectByVisibleText("Washington");
        List<WebElement> selectedOptions = selectObject.getAllSelectedOptions();
        ArrayList <String> expectedOptions = new ArrayList<String>();
        expectedOptions.add("Texas");
        expectedOptions.add("Washington");
        expectedOptions.add("New Jersey");

        assertTrue(selectedOptions.stream().map(WebElement::getText).allMatch(expectedOptions::contains),
                            "Not all elements or incorrect elements were selected");
    }
}

