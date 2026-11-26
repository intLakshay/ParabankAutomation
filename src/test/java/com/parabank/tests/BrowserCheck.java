
package com.parabank.tests;

import org.testng.annotations.Test;
import org.testng.Assert;

import com.parabank.base.BaseTest;

public class BrowserCheck extends BaseTest {

    @Test
    public void verifyParaBankTitle() {

        String actualTitle = driver.getTitle();

        System.out.println("Page Title: " + actualTitle);

        Assert.assertTrue(
            actualTitle.toLowerCase().contains("parabank"),
            "ParaBank title verification failed!"
        );
    }
}
