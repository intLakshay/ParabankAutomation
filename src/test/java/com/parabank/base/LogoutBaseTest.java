
package com.parabank.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import java.time.Duration;

import com.parabank.utils.TestData;

public class LogoutBaseTest {

    protected WebDriver driver;

    private final String BASE_URL =
        "https://parabank.parasoft.com/parabank/index.htm";

    @BeforeClass
    public void setUp() {

        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        try {
            driver.get(BASE_URL);

            WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(20));

            WebElement usernameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.name("username")
                )
            );

            usernameField.sendKeys(TestData.VALID_USERNAME);

            driver.findElement(By.name("password"))
                .sendKeys(TestData.VALID_PASSWORD);

            driver.findElement(
                By.cssSelector("input.button[value='Log In']")
            ).click();

            wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                    By.linkText("Log Out")
                )
            );

            System.out.println("Login successful!");
            System.out.println("Logout module started!");

        } catch (Exception e) {

            System.out.println("===== LOGOUT SETUP DEBUG =====");
            System.out.println("Current URL: " + driver.getCurrentUrl());
            System.out.println("Page Title: " + driver.getTitle());

            Assert.fail("Logout setup failed: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {

        if (driver != null) {
            driver.quit();
            System.out.println("Logout module completed!");
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
