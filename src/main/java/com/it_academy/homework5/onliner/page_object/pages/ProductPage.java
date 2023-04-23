package com.it_academy.homework5.onliner.page_object.pages;

import com.it_academy.homework5.onliner.page_object.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.it_academy.homework5.onliner.framework.DriveManager.getWebDriver;
import static java.lang.String.format;

public class ProductPage extends BasePage {
    private static final String PRODUCT_GROUP_XPATH_PATTERN =
            "//div[contains(@class,'schema-product__group')]";
    public List<WebElement> checkProductGroup() {
        waitForElementVisible(By.xpath(format(PRODUCT_GROUP_XPATH_PATTERN)));
        return getWebDriver().findElements(By.xpath(format(PRODUCT_GROUP_XPATH_PATTERN)));
    }

// размер компонентов не одинаковый, метод закоментировала
    /*
    public boolean checkProductsComponentsSize(List<WebElement> components) {
        Dimension size = components.stream().findAny().get().getSize();
        for (WebElement element: components) {
            if (!size.equals(element.getSize())) {
                return false;
            }
        }
        return true;
    }
    */

    public boolean checkProductTitlesCount(List<WebElement> components) {
        return checkComponentPart(components, "span[data-bind*='html: product.extended_name || product.full_name']");
    }
    public boolean checkProductDescriptionCount(List<WebElement> components) {
        return checkComponentPart(components, "span[data-bind*='html: product.description']");
    }
    public boolean checkComponentRatingCount(List<WebElement> components) {
        return checkComponentPartWithoutText(components, ".rating__fill");
    }
    public boolean checkComponentPriceCount(List<WebElement> components) {
        return checkComponentPart(components, ".schema-product__price");
    }
    public boolean checkComponentIconCount(List<WebElement> components) {
        return checkComponentPartWithoutText(components, ".js-product-image-link");
    }
    public boolean checkComponentCheckboxCount(List<WebElement> components) {
        return checkComponentPartWithoutText(components, ".schema-product__control");
    }
}
