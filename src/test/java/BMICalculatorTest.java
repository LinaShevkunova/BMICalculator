import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BMICalculatorTest {

    WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void seleniumStart() {
        driver = new ChromeDriver();
        //открыть сайт
        driver.get("https://healthunify.com/bmicalculator/");
        //найти поле "вес" и ввести значение 90
        WebElement weightInput = driver.findElement(new By.ByName("wg"));
        weightInput.sendKeys("55");
        //найти поле "рост" и ввести значение 184
        WebElement heightInput = driver.findElement(new By.ByName("ht"));
        heightInput.sendKeys("166");
        //нажать на кнопку
        WebElement buttonCalculate = driver.findElement(new By.ByName("cc"));
        buttonCalculate.click();
        //валидация
        WebElement bmiStatus = driver.findElement(new By.ByClassName("content"));
        String expectedBMIstatus = "Your category is Normal";
        String actualBMIstatus = bmiStatus.getAttribute("value");
        Assert.assertEquals(expectedBMIstatus, actualBMIstatus);
    }

}
