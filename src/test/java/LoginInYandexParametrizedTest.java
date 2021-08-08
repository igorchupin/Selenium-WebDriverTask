import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;


public class LoginInYandexParametrizedTest {
    private final String expectedName = "Inbox — Yandex.Mail";
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


    @ParameterizedTest
    @Tag("yandex_login_parametrized_test")
    @CsvSource({"firstnamelastname1989, pTKJctHpbaj@t7M",
            "test1test1account, 111111111Q", "test2testaccount, 111111qwe"})
    void yandexLoginParasTest(String username, String password) {
        driver.get("https://mail.yandex.com/");
        WebElement enterButton = driver.findElement
                (By.xpath("//*[@id=\"index-page-container\"]/div/div[2]/div/div/div[4]/a[2]"));
        enterButton.click();
        WebElement emailField = driver.findElement(By.id("passp-field-login"));
        emailField.sendKeys(username);
        WebElement loginButton = driver.findElement(By.id("passp:sign-in"));
        loginButton.click();
        WebElement passwordField = driver.findElement(By.id("passp-field-passwd"));
        passwordField.sendKeys(password);
        WebElement logInButton = driver.findElement(By.id("passp:sign-in"));
        logInButton.click();
        WebElement userIcon = driver.findElement(By.className("user-pic__image"));
        userIcon.click();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.pollingEvery(1, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.textMatches(By.xpath("//div[@class='legouser__menu-header']//span[@class='user-account__name']"),
                Pattern.compile(username)));
        WebElement userAccountName = driver.findElement
                (By.xpath("//div[@class='legouser__menu-header']//span[@class='user-account__name']"));

        assertAll("Links",
                () -> assertTrue(driver.getTitle().contains(expectedName),
                        "Incorrect page was opened (Incorrect Title)"),
                () -> assertTrue(driver.findElement(By.linkText("Change password")).isDisplayed(),
                        "Settings Button is not displayed"),
                () -> assertEquals(username + "\n" + username + "@yandex.com", userAccountName.getText(),
                        "Incorrect user account name is shown")
        );
    }
}
