
package com.parabank.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.parabank.utils.TestData;

import org.testng.annotations.AfterClass;

public class TransferFundsBaseTest {

    protected WebDriver driver;

    private final String BASE_URL =
            "https://parabank.parasoft.com/parabank/index.htm";

    @BeforeClass
    public void setUp() {

        driver = new FirefoxDriver();

        driver.manage().window().maximize();

        driver.get(BASE_URL);

        // Login
        driver.findElement(By.name("username"))
              .sendKeys(TestData.VALID_USERNAME);

        driver.findElement(By.name("password"))
              .sendKeys(TestData.VALID_PASSWORD);
        driver.findElement(
            By.cssSelector("input.button[value='Log In']")
        ).click();

        waitFor(3);

        System.out.println("Transfer Funds module started!");
    }

    @BeforeMethod
    public void openTransferPage() {

        driver.findElement(
            By.linkText("Transfer Funds")
        ).click();

        waitFor(2);
    }

    @AfterClass
    public void tearDown() {

        if (driver != null) {

            waitFor(3);

            driver.quit();

            System.out.println("Transfer Funds module completed!");
        }
    }

    public void waitFor(int seconds) {

        try {
            Thread.sleep(seconds * 1000L);
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
