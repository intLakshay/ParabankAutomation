
package com.parabank.tests;

import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.parabank.base.RegistrationBaseTest;

public class RegistrationTest extends RegistrationBaseTest {

    // Stores the username created by TC05
    private static String registeredUsername;

    // Common registration method
    public void registerUser(String username) {

        driver.findElement(By.id("customer.firstName"))
              .sendKeys("Lakshay");

        driver.findElement(By.id("customer.lastName"))
              .sendKeys("Mishra");

        driver.findElement(By.id("customer.address.street"))
              .sendKeys("Sector 15");

        driver.findElement(By.id("customer.address.city"))
              .sendKeys("Chandigarh");

        driver.findElement(By.id("customer.address.state"))
              .sendKeys("Punjab");

        driver.findElement(By.id("customer.address.zipCode"))
              .sendKeys("160015");

        driver.findElement(By.id("customer.phoneNumber"))
              .sendKeys("9876543210");

        driver.findElement(By.id("customer.ssn"))
              .sendKeys("123456789");

        // Username field with diagnostics
        WebElement usernameField =
                driver.findElement(By.id("customer.username"));

        usernameField.clear();
        usernameField.sendKeys(username);

        System.out.println("Generated username: " + username);
        System.out.println("Entered username: "
                + usernameField.getAttribute("value"));
        System.out.println("Username maxlength: "
                + usernameField.getAttribute("maxlength"));

        driver.findElement(By.id("customer.password"))
              .sendKeys("Test@12345");

        driver.findElement(By.id("repeatedPassword"))
              .sendKeys("Test@12345");

        waitFor(2);

        driver.findElement(
            By.cssSelector("input.button[value='Register']")
        ).click();

        waitFor(3);
    }

    // TC05: Register a new user
    @Test
    public void TC05_validRegistration() {

        registeredUsername = "L" +
                UUID.randomUUID().toString()
                .replace("-", "")
                .substring(0, 12);

        registerUser(registeredUsername);

        String response =
                driver.findElement(By.id("rightPanel")).getText();

        System.out.println("TC05 Registration Response:\n" + response);

        Assert.assertTrue(
            response.contains("Your account was created successfully"),
            "Registration success message was not displayed!"
        );

        System.out.println("TC05 PASSED: New user registration");

        waitFor(3);
    }

    // TC06: Attempt registration with an existing username
    @Test(dependsOnMethods = {"TC05_validRegistration"})
    public void TC06_existingUsername() {

        Assert.assertNotNull(
            registeredUsername,
            "TC05 did not generate a username!"
        );

        System.out.println(
            "Testing duplicate username: " + registeredUsername
        );

        registerUser(registeredUsername);

        String response =
                driver.findElement(By.id("rightPanel")).getText();

        System.out.println("TC06 Duplicate Response:\n" + response);

        Assert.assertTrue(
            response.contains("This username already exists."),
            "Existing username was not rejected!"
        );

        System.out.println("TC06 PASSED: Existing username rejected");

        waitFor(3);
    }

    // TC07: Submit registration with empty fields
    @Test
    public void TC07_emptyRegistration() {

        driver.findElement(
            By.cssSelector("input.button[value='Register']")
        ).click();

        waitFor(3);

        Assert.assertTrue(
            driver.getPageSource().contains("First name is required"),
            "Required field validation was not displayed!"
        );

        System.out.println(
            "TC07 PASSED: Empty registration validation"
        );

        waitFor(3);
    }
}
