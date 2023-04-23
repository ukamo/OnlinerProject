package com.it_academy.homework5.onliner.page_object.pages;

import com.it_academy.homework5.onliner.page_object.BasePage;
import org.openqa.selenium.By;

import static java.lang.String.format;

public class Header extends BasePage {
    private static final String MAIN_NAVIGATION_LINK_XPATH_PATTER =
            "//*[contains(@class, 'main-navigation__text') and contains(text(), '%s')]";

    public CatalogPage clickOnMainNavigationLink(String link) {
        waitForElementVisible(By.xpath(format(MAIN_NAVIGATION_LINK_XPATH_PATTER, link))).click();
        return new CatalogPage();
    }

}
