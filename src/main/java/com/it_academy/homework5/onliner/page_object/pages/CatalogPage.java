package com.it_academy.homework5.onliner.page_object.pages;

import com.it_academy.homework5.onliner.page_object.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.it_academy.homework5.onliner.framework.DriveManager.getWebDriver;
import static java.lang.String.format;

public class CatalogPage extends BasePage {
    private static final String SECTION_FOR_CATALOG_XPATH_PATTERN =
            "//ul[@class='catalog-navigation-classifier ']/li//span[normalize-space(text()) and contains(text(), '%s')]";
    private static final String SECTION_ITEM_XPATH_PATTERN =
            "//div[contains(@class,'aside-item')]//div[contains(text(),'%s')]";
    private static final String TITLE_XPATH_PATTERN =
            "//div[contains(@class,'item_active')]//div[contains(text(),'%s')]/..//div[contains(@class,'dropdown-list')]//span[contains(@class,'dropdown-title')]";
    public static final String GOODS_XPATH_PATTERN =
            "//div[contains(@class,'item_active')]//div[contains(text(),'%s')]/..//div[contains(@class,'dropdown-list')]//span[contains(@class,'dropdown-description')]";
    public static final String GOODS_ITEM_XPATH_PATTERN =
            "//div[contains(@class,'item_active')]//div[contains(text(),'%s')]/..//a[contains(@class,'list__dropdown-item')]";
    private static final String PRODUCT_XPATH_PATTERN =
            "//div[contains(@class, 'aside-item_active')]//div[contains(@class, 'dropdown-list')]"
                    + "/a[contains(@href, 'onliner')]//span[contains(@class, 'title') and contains(text(), '%s')]";
    private static final String CATALOG_CLASSIFIER_LINK_XPATH_PATTERN =
            "//ul[@class='catalog-navigation-classifier ']/li//span[normalize-space(text()) and contains(text(), '%s')]";

    public String getTextFromSectionCatalog(String str1) {
        return waitForElementVisible(By.xpath(String.format(SECTION_FOR_CATALOG_XPATH_PATTERN, str1)))
                .getText();
    }

    public CatalogPage clickOnSectionCatalogLink(String str1) {
        waitForElementVisible(By.xpath(format(SECTION_FOR_CATALOG_XPATH_PATTERN, str1)))
                .click();
        return new CatalogPage();
    }

    public String getTextSectionItemCatalogLink(String str) {
        return waitForElementVisible(By.xpath(String.format(SECTION_ITEM_XPATH_PATTERN, str)))
                .getText();
    }

    public CatalogPage clickTextSectionItemCatalog(String str) {
        waitForElementVisible(By.xpath(String.format(SECTION_ITEM_XPATH_PATTERN, str))).click();
        return this;

    }

    public List<String> checkTitleOfCatalogPage(String testContains) {
        waitForElementVisible(By.xpath(String.format(TITLE_XPATH_PATTERN, testContains)));
        List<WebElement> elements = getWebDriver().findElements(By.xpath(format(TITLE_XPATH_PATTERN, testContains)));
        List<String> collect = elements.stream().map(WebElement::getText).collect(Collectors.toList());
        System.out.println(collect);
        return collect;
    }

    public List<WebElement> getGoodsComponentsByName(String name) {
        return getWebDriver().findElements(By.xpath(format(GOODS_ITEM_XPATH_PATTERN, name)));
    }

    public boolean checkComponentTitlesCount(List<WebElement> components) {
        return checkComponentPart(components, ".catalog-navigation-list__dropdown-title");
    }

    public boolean checkComponentPreviewsCount(List<WebElement> components) {
        List<WebElement> titles = new ArrayList<>();
        for (WebElement component : components) {
            WebElement title =
                    component.findElement(By.cssSelector(".catalog-navigation-list__dropdown-image"));
            if (title.isDisplayed()) {
                titles.add(title);
            }
        }
        return components.size() == titles.size();
    }

    public boolean checkComponentDescriptionCount(List<WebElement> components) {
        return checkComponentPart(components, ".catalog-navigation-list__dropdown-description");
    }

    public boolean checkGoodsComponentsSize(List<WebElement> components) {
        Dimension size = components.stream().findAny().get().getSize();
        for (WebElement element : components) {
            if (!size.equals(element.getSize())) {
                return false;
            }
        }
        return true;
    }

    public List<String> checkGoodsCatalogPage(String catalogName) {
        waitForElementVisible(By.xpath(format(GOODS_XPATH_PATTERN, catalogName)));
        List<WebElement> elements = getWebDriver().findElements(By.xpath(format(GOODS_XPATH_PATTERN, catalogName)));
        List<String> collect = elements.stream().map(WebElement::getText).collect(Collectors.toList());
        return collect;
    }

    public boolean checkCountAndPrice(List<String> collect) {
        List<String> files = collect.stream().filter(f -> f.contains("\n")).map(f -> f.replaceAll("\n", " ")).collect(Collectors.toList());
        Pattern pattern = Pattern.compile("(\\d+(,\\d+)?)(\\D+)(\\d+(,\\d+)?)(.*?)");
        List<String> matching = files.stream()
                .filter(pattern.asPredicate())
                .toList();
        return files.size() == matching.size();
    }

    public ProductPage clickOnProductLink(String product) {
        waitForElementVisible(By.xpath(format(PRODUCT_XPATH_PATTERN, product)))
                .click();
        return new ProductPage();
    }

    public CatalogPage clickOnCatalogClassifierLink(String link) {
        waitForElementVisible(By.xpath(format(CATALOG_CLASSIFIER_LINK_XPATH_PATTERN, link)))
                .click();
        return this;
    }
}
