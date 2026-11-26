
package com.parabank.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.parabank.base.BaseTest;
import com.parabank.utils.TestData;

public class LoginTest extends BaseTest {

    public void login(String username, String password) {

        WebElement usernameField = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.name("username")
            )
        );

        WebElement passwordField = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.name("password")
            )
        );

        usernameField.clear();
        usernameField.sendKeys(username);

        passwordField.clear();
        passwordField.sendKeys(password);

        wait.until(
            ExpectedConditions.elementToBeClickable(
                By.cssSelector("input.button[value='Log In']")
            )
        ).click();

        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.id("rightPanel")
            )
        );
    }

    @Test
    public void TC01_validLogin() {

        login(TestData.VALID_USERNAME, TestData.VALID_PASSWORD);

        String response =
            driver.findElement(By.id("rightPanel")).getText();

        Assert.assertTrue(
            response.contains("Accounts Overview"),
            "Valid login failed!"
        );

        System.out.println("TC01 PASSED: Valid login");
    }

    @Test
    public void TC02_invalidUsername() {

        login(TestData.INVALID_USERNAME, TestData.VALID_PASSWORD);

        String response =
            driver.findElement(By.id("rightPanel")).getText();

        Assert.assertTrue(
            response.contains("Error"),
            "Error message was not displayed!"
        );

        System.out.println("TC02 PASSED: Invalid username rejected");
    }

    @Test
    public void TC03_invalidPassword() {

        login(TestData.VALID_USERNAME, TestData.INVALID_PASSWORD);

        String response =
            driver.findElement(By.id("rightPanel")).getText();

        Assert.assertTrue(
            response.contains("Error"),
            "Error message was not displayed!"
        );

        System.out.println("TC03 PASSED: Invalid password rejected");
    }

    @Test
    public void TC04_emptyCredentials() {

        login("", "");

        String response =
            driver.findElement(By.id("rightPanel")).getText();

        Assert.assertTrue(
            response.contains("required")
                || response.contains("Please enter"),
            "Empty credential validation was not displayed!"
        );

        System.out.println("TC04 PASSED: Empty credentials validation");
    }
}
