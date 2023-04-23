package com.it_academy.homework5.onliner.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;

public class DriveManager {
    private static ThreadLocal<RemoteWebDriver> driver =
            new ThreadLocal();

    private static void setWebDriver() {
        driver.set(new ChromeDriver());
        driver.get().manage().window().maximize();
        driver.get().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get().manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
    }

    public static WebDriver getWebDriver() {
        if (driver.get() == null) {
            setWebDriver();
        }
        return driver.get();
    }

    public static void closeBrowser() {
        driver.get().close();
        driver.remove();
    }
}
