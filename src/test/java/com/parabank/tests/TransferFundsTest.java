
package com.parabank.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.parabank.base.TransferFundsBaseTest;

public class TransferFundsTest extends TransferFundsBaseTest {

    // Select two different accounts dynamically
    public void selectAccounts() {

        WebDriverWait wait = new WebDriverWait(
            driver, Duration.ofSeconds(10)
        );

        wait.until(
            ExpectedConditions.numberOfElementsToBeMoreThan(
                By.cssSelector("#fromAccountId option"), 1
            )
        );

        Select fromAccount = new Select(
            driver.findElement(By.id("fromAccountId"))
        );

        Select toAccount = new Select(
            driver.findElement(By.id("toAccountId"))
        );

        List<WebElement> accounts = fromAccount.getOptions();

        String source = accounts.get(0).getDomAttribute("value");
        String destination = accounts.get(1).getDomAttribute("value");

        fromAccount.selectByValue(source);
        toAccount.selectByValue(destination);

        String selectedSource =
            fromAccount.getFirstSelectedOption().getText();

        String selectedDestination =
            toAccount.getFirstSelectedOption().getText();

        Assert.assertNotEquals(
            selectedSource,
            selectedDestination,
            "Source and destination accounts must be different!"
        );

        System.out.println("Source Account: " + selectedSource);
        System.out.println("Destination Account: " + selectedDestination);
    }


    // TC13: Transfer a valid amount
    @Test(priority = 1)
    public void TC13_validTransfer() {

        selectAccounts();

        driver.findElement(By.id("amount")).sendKeys("10");

        driver.findElement(
            By.cssSelector("input.button[value='Transfer']")
        ).click();

        waitFor(3);

        String result =
            driver.findElement(By.id("rightPanel")).getText();

        System.out.println("TC13 Result:\n" + result);

        Assert.assertTrue(
            result.contains("Transfer Complete!"),
            "Valid transfer was not completed!"
        );

        System.out.println("TC13 PASSED: Valid transfer");
    }


    // TC14: Transfer zero amount
    @Test(priority = 2)
    public void TC14_zeroAmountTransfer() {

        selectAccounts();

        driver.findElement(By.id("amount")).sendKeys("0");

        driver.findElement(
            By.cssSelector("input.button[value='Transfer']")
        ).click();

        waitFor(3);

        String result =
            driver.findElement(By.id("rightPanel")).getText();

        System.out.println("TC14 Result:\n" + result);

        Assert.assertFalse(
            result.contains("Transfer Complete!"),
            "Zero amount transfer should not be completed!"
        );

        System.out.println("TC14 PASSED: Zero amount rejected");
    }


    // TC15: Transfer more than available balance
    @Test(priority = 3)
    public void TC15_insufficientBalanceTransfer() {

        selectAccounts();

        driver.findElement(By.id("amount"))
              .sendKeys("999999999");

        driver.findElement(
            By.cssSelector("input.button[value='Transfer']")
        ).click();

        waitFor(3);

        String result =
            driver.findElement(By.id("rightPanel")).getText();

        System.out.println("TC15 Result:\n" + result);

        Assert.assertFalse(
            result.contains("Transfer Complete!"),
            "Transfer should not complete with insufficient funds!"
        );

        System.out.println(
            "TC15 PASSED: Insufficient balance rejected"
        );
    }
}
