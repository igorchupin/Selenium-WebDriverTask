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

public class ListOfObjectTest {
    private final int AGE_VALUE = 22;
    private final int SALARY_VALUE = 86000;
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
    @Tag("List_Of_Objects")
    @DisplayName("List of objects")
    void listOfObjectsTest() {
        driver.get("https://www.seleniumeasy.com/test/table-sort-search-demo.html");
        WebElement selectEntries = driver.findElement(By.xpath("//select[@name='example_length']"));
        Select selectObject = new Select(selectEntries);
        selectObject.selectByValue("10");
        List<WebElement> buttonsCount = driver.findElements(By.xpath("//div[@id='example_paginate']//a[not(@id)]"));
        By rowsOfPeople = By.xpath("//tbody/tr[@role='row']");
        List<People> finalList = creatingPeopleList(driver, rowsOfPeople, buttonsCount);
    }

    public ArrayList<People> creatingPeopleList(WebDriver webdriver, By pathToRows, List<WebElement> countOfButtons) {
        ArrayList<People> peopleList = new ArrayList<People>();
        for (WebElement element : countOfButtons) {
            List<WebElement> peopleListWeb = driver.findElements(pathToRows);
            for (WebElement humanWeb : peopleListWeb) {
                String humanName = humanWeb.findElement(By.xpath(".//td[1]")).getText();
                String humanPosition = humanWeb.findElement(By.xpath(".//td[2]")).getText();
                String humanOffice = humanWeb.findElement(By.xpath(".//td[3]")).getText();
                Integer age = Integer.valueOf(humanWeb.findElement(By.xpath(String.format(".//td[4]"))).getText());
                String salary = humanWeb.findElement(By.xpath(".//td[6]")).getText();
                salary = salary.substring(1, salary.length() - 2).replace(",", "");
                Integer salaryInt = Integer.valueOf(salary);
                if (age > AGE_VALUE && salaryInt <= SALARY_VALUE) {
                    People tempPeople = new People(humanName, humanPosition, humanOffice, age, salaryInt);
                    peopleList.add(tempPeople);
                }
            }
            WebElement buttonNext = driver.findElement(By.id("example_next"));
            buttonNext.click();
        }
        return peopleList;
    }
}

