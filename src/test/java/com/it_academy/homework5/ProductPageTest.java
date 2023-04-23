package com.it_academy.homework5;

import com.it_academy.homework5.onliner.framework.DriveManager;
import com.it_academy.homework5.onliner.page_object.pages.Header;
import com.it_academy.homework5.onliner.page_object.pages.ProductPage;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProductPageTest {
    private ProductPage productPage = new ProductPage();
    private Header header = new Header();
    @BeforeClass
    public void navigationToHomePage() {
        DriveManager.getWebDriver().get("https://www.onliner.by/");
    }
    @Test
    public void checkProductTitleSize() {
        header
                .clickOnMainNavigationLink("Каталог")
                .clickOnCatalogClassifierLink("Электроника")
                .clickTextSectionItemCatalog("Аудиотехника")
                .clickOnProductLink("Наушники");
        List<WebElement> components = productPage.checkProductGroup();
        assertThat(productPage.checkProductTitlesCount(components))
                .as("Количество тайтлов не соответствует количеству компонентов")
                .isTrue();
        }
    @Test
    public void checkProductDescriptionSize() {
        header
                .clickOnMainNavigationLink("Каталог")
                .clickOnCatalogClassifierLink("Электроника")
                .clickTextSectionItemCatalog("Аудиотехника")
                .clickOnProductLink("Наушники");
        List<WebElement> components = productPage.checkProductGroup();
        assertThat(productPage.checkProductDescriptionCount(components))
                .as("Количество описания не соответствует количеству компонентов")
                .isTrue();
    }
    @Test
    public void checkProductRatingSize() {
        header
                .clickOnMainNavigationLink("Каталог")
                .clickOnCatalogClassifierLink("Электроника")
                .clickTextSectionItemCatalog("Аудиотехника")
                .clickOnProductLink("Наушники");
        List<WebElement> components = productPage.checkProductGroup();
        assertThat(productPage.checkComponentRatingCount(components))
                .as("Количество рейтингов не соответствует количеству компонентов")
                .isTrue();
    }
    @Test
    public void checkProductPriceSize() {
        header
                .clickOnMainNavigationLink("Каталог")
                .clickOnCatalogClassifierLink("Электроника")
                .clickTextSectionItemCatalog("Аудиотехника")
                .clickOnProductLink("Наушники");
        List<WebElement> components = productPage.checkProductGroup();
        assertThat(productPage.checkComponentPriceCount(components))
                .as("Количество цен не соответствует количеству компонентов")
                .isTrue();
    }
    @Test
    public void checkProductIconSize() {
        header
                .clickOnMainNavigationLink("Каталог")
                .clickOnCatalogClassifierLink("Электроника")
                .clickTextSectionItemCatalog("Аудиотехника")
                .clickOnProductLink("Наушники");
        List<WebElement> components = productPage.checkProductGroup();
        assertThat(productPage.checkComponentIconCount(components))
                .as("Количество иконок не соответствует количеству компонентов")
                .isTrue();
    }

    @Test
    public void checkProductCheckboxSize() {
        header
                .clickOnMainNavigationLink("Каталог")
                .clickOnCatalogClassifierLink("Электроника")
                .clickTextSectionItemCatalog("Аудиотехника")
                .clickOnProductLink("Наушники");
        List<WebElement> components = productPage.checkProductGroup();
        assertThat(productPage.checkComponentCheckboxCount(components))
                .as("Количество чекбоксов не соответствует количеству компонентов")
                .isTrue();
    }
    @AfterSuite
    public void closeWindow(){
        header.closeAllWindow();
    }
}
