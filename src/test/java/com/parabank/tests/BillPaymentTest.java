
package com.parabank.tests;

import com.parabank.base.BillPaymentBaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import org.testng.Assert;
import org.testng.annotations.Test;

public class BillPaymentTest extends BillPaymentBaseTest {

    // TC16: Valid Bill Payment
    @Test
    public void validBillPayment() {

        driver.findElement(By.name("payee.name"))
              .sendKeys("Test Payee");

        driver.findElement(By.name("payee.address.street"))
              .sendKeys("123 Test Street");

        driver.findElement(By.name("payee.address.city"))
              .sendKeys("Chandigarh");

        driver.findElement(By.name("payee.address.state"))
              .sendKeys("Punjab");

        driver.findElement(By.name("payee.address.zipCode"))
              .sendKeys("160001");

        driver.findElement(By.name("payee.phoneNumber"))
              .sendKeys("9876543210");

        driver.findElement(By.name("payee.accountNumber"))
              .sendKeys("123456789");

        driver.findElement(By.name("verifyAccount"))
              .sendKeys("123456789");

        driver.findElement(By.name("amount"))
              .sendKeys("10");

        // Select an available funding account dynamically
        Select account = new Select(
            driver.findElement(By.name("fromAccountId"))
        );

        account.selectByIndex(0);

        driver.findElement(
            By.cssSelector("input.button[value='Send Payment']")
        ).click();

        waitFor(3);

        String pageText = driver.findElement(By.tagName("body"))
                                 .getText();

        System.out.println("TC16 Result:");
        System.out.println(pageText);

        Assert.assertTrue(
            pageText.contains("Bill Payment Complete"),
            "Bill payment confirmation was not displayed."
        );
    }


    // TC17: Submit Bill Payment with Missing Details
    @Test
    public void missingBillPaymentDetails() {

        // Leave all text fields empty
        // The funding account dropdown may have a default selection.

        driver.findElement(
            By.cssSelector("input.button[value='Send Payment']")
        ).click();

        waitFor(2);

        String pageText = driver.findElement(By.tagName("body"))
                                 .getText();

        System.out.println("TC17 Result:");
        System.out.println(pageText);

        Assert.assertTrue(
            pageText.toLowerCase().contains("required"),
            "Expected validation error was not displayed."
        );
    }
}
