
package com.parabank.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.parabank.base.AccountOverviewBaseTest;

public class AccountOverviewTest extends AccountOverviewBaseTest {

    // TC08: Verify Account Overview table
    @Test
    public void TC08_verifyAccountOverview() {

        WebElement accountTable =
                driver.findElement(By.id("accountTable"));

        Assert.assertTrue(
            accountTable.isDisplayed(),
            "Account Overview table is not displayed!"
        );

        Assert.assertTrue(
            accountTable.getText().contains("Account"),
            "Account column is missing!"
        );

        Assert.assertTrue(
            accountTable.getText().contains("Balance*"),
            "Balance column is missing!"
        );

        Assert.assertTrue(
            accountTable.getText().contains("Available Amount"),
            "Available Amount column is missing!"
        );

        System.out.println(
            "TC08 PASSED: Account Overview table displayed"
        );

        waitFor(3);
    }

    // TC09: Verify account balance and available amount
    @Test
    public void TC09_verifyAccountBalance() {

        WebElement accountTable =
                driver.findElement(By.id("accountTable"));

        // Get all account rows
        int accountCount = driver.findElements(
            By.cssSelector("#accountTable tbody tr td a")
        ).size();

        Assert.assertTrue(
            accountCount > 0,
            "No accounts are displayed!"
        );

        // Check balance and available amount
        WebElement firstAccountRow = driver.findElement(
            By.cssSelector("#accountTable tbody tr:first-child")
        );

        String balance = firstAccountRow
                .findElements(By.tagName("td"))
                .get(1)
                .getText();

        String availableAmount = firstAccountRow
                .findElements(By.tagName("td"))
                .get(2)
                .getText();

        Assert.assertFalse(
            balance.trim().isEmpty(),
            "Account balance is empty!"
        );

        Assert.assertFalse(
            availableAmount.trim().isEmpty(),
            "Available amount is empty!"
        );

        System.out.println(
            "TC09 PASSED: Balance = " + balance +
            ", Available Amount = " + availableAmount
        );

        waitFor(3);
    }
}
