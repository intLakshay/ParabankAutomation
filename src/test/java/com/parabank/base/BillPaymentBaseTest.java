
package com.parabank.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.parabank.utils.TestData;

import org.testng.annotations.AfterClass;

public class BillPaymentBaseTest {

    protected WebDriver driver;

    private final String BASE_URL =
        "https://parabank.parasoft.com/parabank/index.htm";

    @BeforeClass
    public void setUp() {

        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        driver.get(BASE_URL);

        // Replace these placeholders with your local test credentials
        driver.findElement(By.name("username"))
              .sendKeys(TestData.VALID_USERNAME);

        driver.findElement(By.name("password"))
              .sendKeys(TestData.VALID_PASSWORD);

        driver.findElement(
            By.cssSelector("input.button[value='Log In']")
        ).click();

        waitFor(3);

        System.out.println("Bill Payment module started!");
    }

    @BeforeMethod
    public void openBillPaymentPage() {

        driver.findElement(By.linkText("Bill Pay")).click();
        waitFor(2);
    }

    @AfterClass
    public void tearDown() {

        if (driver != null) {
            waitFor(2);
            driver.quit();

            System.out.println("Bill Payment module completed!");
        }
    }

    public void waitFor(int seconds) {

        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
