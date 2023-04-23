package com.it_academy.homework5.onliner.page_object.pages;

import com.it_academy.homework5.onliner.page_object.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Quotes;

import static java.lang.String.format;

public class HomePage extends BasePage {
    private static final String SECTION_LINK_XPATH_PATTERN =
            "//a[contains(@class,\"navigation__link\")]//span[contains(text(),%s)]";

    public CatalogPage clickOnSectionLink(String link) {
        waitForElementVisible(By.xpath(format(SECTION_LINK_XPATH_PATTERN, Quotes.escape(link))))
                .click();
        return new CatalogPage();
    }
}
