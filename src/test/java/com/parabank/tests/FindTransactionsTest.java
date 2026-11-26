
package com.parabank.tests;

import com.parabank.base.FindTransactionsBaseTest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FindTransactionsTest extends FindTransactionsBaseTest {

    // TC18: Search for a valid dynamically generated transaction
    @Test
    public void searchValidTransactionId() {

        driver.findElement(By.id("transactionId"))
              .sendKeys(generatedTransactionId);

        driver.findElement(By.id("findById")).click();

        waitFor(3);

        String pageText = driver.findElement(By.tagName("body"))
                                .getText();

        System.out.println("TC18 Transaction ID: "
                           + generatedTransactionId);

        System.out.println("TC18 Result:");
        System.out.println(pageText);

        Assert.assertTrue(
            pageText.contains("Transaction Results"),
            "Transaction Results were not displayed."
        );

        Assert.assertTrue(
            pageText.contains("FindTxn Test Payee"),
            "The generated transaction was not found."
        );

        System.out.println("TC18: Valid transaction found.");
    }

    // TC19: Search for an invalid transaction ID
    @Test
    public void searchInvalidTransactionId() {

        String invalidId = "999999999999999999";

        driver.findElement(By.id("transactionId"))
              .sendKeys(invalidId);

        driver.findElement(By.id("findById")).click();

        waitFor(3);

        String pageText = driver.findElement(By.tagName("body"))
                                .getText();

        System.out.println("TC19 Result:");
        System.out.println(pageText);

        Assert.assertFalse(
            pageText.contains("FindTxn Test Payee"),
            "Invalid ID returned the generated transaction."
        );

        Assert.assertFalse(
            pageText.contains("An internal error has occurred"),
            "Application returned an internal error."
        );

        System.out.println("TC19: No matching transaction found.");
    }

    public void waitFor(int seconds) {

        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
