package com.it_academy.homework5;

import com.it_academy.homework5.onliner.framework.DriveManager;
import org.testng.annotations.AfterSuite;

public class BaseTest {
    @AfterSuite
    public void closeBrowser() {
        DriveManager.closeBrowser();
    }
}
