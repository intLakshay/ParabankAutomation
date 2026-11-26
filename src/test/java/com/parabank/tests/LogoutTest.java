
package com.parabank.tests;

import com.parabank.base.LogoutBaseTest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutTest extends LogoutBaseTest {

    // TC20: Verify successful logout
    @Test
    public void logoutSuccessfully() {

        // Click the Log Out link
        driver.findElement(By.linkText("Log Out")).click();

        waitFor(3);

        System.out.println("TC20 Current URL: " + driver.getCurrentUrl());

        // Verify that the login page is displayed again
        boolean usernameVisible =
            driver.findElement(By.name("username")).isDisplayed();

        boolean passwordVisible =
            driver.findElement(By.name("password")).isDisplayed();

        boolean loginButtonVisible =
            driver.findElement(
                By.cssSelector("input.button[value='Log In']")
            ).isDisplayed();

        System.out.println("Username field visible: " + usernameVisible);
        System.out.println("Password field visible: " + passwordVisible);
        System.out.println("Login button visible: " + loginButtonVisible);

        Assert.assertTrue(
            usernameVisible && passwordVisible && loginButtonVisible,
            "Logout was unsuccessful. Login page was not displayed."
        );

        System.out.println("TC20: Logout successful!");
    }
}
