
package com.parabank.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.parabank.base.OpenAccountBaseTest;

public class OpenAccountTest extends OpenAccountBaseTest {

    // Reusable method to select an available funding account
    private void selectFirstAvailableFundingAccount() {

        waitFor(3);

        System.out.println("\n===== DEBUG INFORMATION =====");

        System.out.println("Current URL: " + driver.getCurrentUrl());

        System.out.println("Page Title: " + driver.getTitle());

        String pageText =
            driver.findElement(By.id("rightPanel")).getText();

        System.out.println("Page Text:\n" + pageText);

        Select fromAccount = new Select(
            driver.findElement(By.id("fromAccountId"))
        );

        System.out.println("\nFunding Dropdown Options:");

        for (WebElement option : fromAccount.getOptions()) {

            System.out.println(
                "Text: " + option.getText() +
                " | Value: " + option.getAttribute("value")
            );
        }

        // Check whether an actual account is available
        for (WebElement option : fromAccount.getOptions()) {

            String value = option.getAttribute("value");

            if (value != null && !value.trim().isEmpty()) {

                fromAccount.selectByValue(value);

                System.out.println(
                    "\nSelected Funding Account: " +
                    option.getText()
                );

                return;
            }
        }

        Assert.fail(
            "No funding account available! " +
            "Check the logged-in user's account list."
        );
    }


    // TC10: Open a Checking account
    @Test
    public void TC10_openCheckingAccount() {

        System.out.println("\n===== TC10: OPEN CHECKING ACCOUNT =====");

        Select accountType = new Select(
            driver.findElement(By.id("type"))
        );

        accountType.selectByVisibleText("CHECKING");

        selectFirstAvailableFundingAccount();

        waitFor(2);

        driver.findElement(
            By.cssSelector("input.button[value='Open New Account']")
        ).click();

        waitFor(4);

        Assert.assertTrue(
            driver.getPageSource().contains("Account Opened!"),
            "Checking account was not opened!"
        );

        System.out.println(
            "TC10 PASSED: Checking account opened successfully"
        );

        waitFor(3);
    }


    // TC11: Open a Savings account
    @Test
    public void TC11_openSavingsAccount() {

        System.out.println("\n===== TC11: OPEN SAVINGS ACCOUNT =====");

        Select accountType = new Select(
            driver.findElement(By.id("type"))
        );

        accountType.selectByVisibleText("SAVINGS");

        selectFirstAvailableFundingAccount();

        waitFor(2);

        driver.findElement(
            By.cssSelector("input.button[value='Open New Account']")
        ).click();

        waitFor(4);

        Assert.assertTrue(
            driver.getPageSource().contains("Account Opened!"),
            "Savings account was not opened!"
        );

        System.out.println(
            "TC11 PASSED: Savings account opened successfully"
        );

        waitFor(3);
    }


    // TC12: Submit without changing the default account type
    @Test
    public void TC12_defaultAccountType() {

        System.out.println("\n===== TC12: DEFAULT ACCOUNT TYPE =====");

        Select accountType = new Select(
            driver.findElement(By.id("type"))
        );

        String defaultType =
            accountType.getFirstSelectedOption().getText();

        System.out.println(
            "Default Account Type: " + defaultType
        );

        Assert.assertEquals(
            defaultType,
            "CHECKING",
            "Default account type is not CHECKING!"
        );

        selectFirstAvailableFundingAccount();

        waitFor(2);

        driver.findElement(
            By.cssSelector("input.button[value='Open New Account']")
        ).click();

        waitFor(4);

        String pageText =
            driver.findElement(By.id("rightPanel")).getText();

        System.out.println("TC12 Result:\n" + pageText);

        Assert.assertTrue(
            pageText.contains("Account Opened!"),
            "Account was not opened using the default account type!"
        );

        System.out.println(
            "TC12 PASSED: Default account type accepted"
        );

        waitFor(3);
    }
}
