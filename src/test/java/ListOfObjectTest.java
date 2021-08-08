
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfObjectTest {
    private WebDriver driver;
    private final int AGE_VALUE = 22;
    private final int SALARY_VALUE = 93000;

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
    @Tag("List_Of_Objects")
    @DisplayName("List of objects")
    void listOfObjectsTest() {
        ArrayList<People> peopleList = new ArrayList<People>();
        driver.get("https://www.seleniumeasy.com/test/table-sort-search-demo.html");
        WebElement selectEntries = driver.findElement(By.xpath("//select[@name='example_length']"));
        Select selectObject = new Select(selectEntries);
        selectObject.selectByValue("25");
        WebElement nextButton;
        List<WebElement> buttonsCount = driver.findElements(By.xpath("//a[@class='paginate_button '] | " +
                "//a[@class='paginate_button current']"));

        for (int i = 1; i <= buttonsCount.size(); i++) {
            List<WebElement> peopleListWeb = driver.findElements(By.xpath("//tbody/tr[@role='row']"));

            for (int j = 0; j < peopleListWeb.size(); j++) {
                String humanName = new String(peopleListWeb.get(j).findElement(By.xpath(String.format("//tr[%s]//td[1]", j+1))).getText());
                String humanPosition = new String(peopleListWeb.get(j).findElement(By.xpath(String.format("//tr[%s]//td[2]", j+1))).getText());
                String humanoffice = new String(peopleListWeb.get(j).findElement(By.xpath(String.format("//tr[%s]//td[3]", j+1))).getText());;
                Integer age = new Integer(Integer.valueOf(peopleListWeb.get(j).findElement(By.xpath(String.format("//tr[%s]//td[4]", j+1))).getText()));
                String salary = peopleListWeb.get(j).findElement(By.xpath(String.format("//tr[%s]//td[6]", j+1))).getText();
                salary = salary.substring(1, salary.length()-2).replace(",","");
                Integer salaryInt = Integer.valueOf(salary);

                if (age > AGE_VALUE && salaryInt <= SALARY_VALUE) {
                    People tempPeople = new People (humanName, humanPosition, humanoffice, age, salaryInt);
                    peopleList.add(tempPeople);
                }
            }
            WebElement buttonNext = driver.findElement(By.id("example_next"));
            buttonNext.click();
        }
        System.out.println("____________________________________________________________________________________");
        System.out.println("Was found: " + peopleList.size() + " people;");
        for (int z = 0; z < peopleList.size(); z++) {
            peopleList.get(z).toString();
        }
    }
}
