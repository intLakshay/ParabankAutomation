
package com.parabank.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;

public class RegistrationBaseTest {

    protected WebDriver driver;

    private final String BASE_URL =
        "https://parabank.parasoft.com/parabank/register.htm";

    @BeforeClass
    public void setUp() {

        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        driver.get(BASE_URL);

        System.out.println("Registration module started!");
    }

    @BeforeMethod
    public void resetPage() {

        driver.get(BASE_URL);
        waitFor(2);
    }

    @AfterClass
    public void tearDown() {

        if (driver != null) {
            waitFor(3);
            driver.quit();

            System.out.println("Registration module completed!");
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
