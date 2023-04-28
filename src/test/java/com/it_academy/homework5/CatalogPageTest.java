package com.it_academy.homework5;

import com.it_academy.homework5.onliner.Links;
import com.it_academy.homework5.onliner.page_object.pages.CatalogPage;
import com.it_academy.homework5.onliner.page_object.pages.Header;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CatalogPageTest {
    private Header header = new Header();
    private CatalogPage catalogPage = new CatalogPage();
    @DataProvider(name = "sections")
    public static Object[][] sections() {
        return new Object[][]{{"Электроника", "Электроника"},
                {"Компьютеры и\u00a0сети", "Компьютеры и сети"},
                {"Бытовая техника", "Бытовая техника"},
                {"На каждый день", "На каждый день"},
                {"Стройка и\u00a0ремонт", "Стройка и ремонт"},
                {"Дом и\u00a0сад", "Дом и сад"},
                {"Авто и\u00a0мото", "Авто и мото"},
                {"Красота и\u00a0спорт", "Красота и спорт"},
                {"Детям и\u00a0мамам", "Детям и мамам"}};
    }
    @DataProvider(name = "verticalList")
    public static Object[][] verticalList() {
        return new Object[][]{{"Ноутбуки, компьютеры, мониторы", "Ноутбуки, компьютеры, мониторы"},
                {"Комплектующие", "Комплектующие"}};
    }
    @BeforeClass
    public void navigateToHomePage() {
        header.navigate(Links.homepage.getLink());
    }
    @Test
    public void testNavigateToCatalog() {
        header.clickOnMainNavigationLink("Каталог");
        assertThat(catalogPage.getBrowserTitle())
                .as("Title of Catalog is incorrect")
                .isEqualTo("Каталог Onlíner");
    }
    @Test(dataProvider = "sections")
    public void testSectionsOnCatalog(String str1, String answer) {
        header.clickOnMainNavigationLink("Каталог");
        assertThat(catalogPage.getTextFromSectionCatalog(str1))
                .as("Browser title on Catalog page is incorrect")
                .isEqualTo(answer);
    }
    @Test(dataProvider = "verticalList")
    public void testVerticalListOnComputersSection(String str1, String expected) {
        header.clickOnMainNavigationLink("Каталог");
        catalogPage.clickOnSectionCatalogLink("Компьютеры и\u00a0сети");
        assertThat(catalogPage.getTextSectionItemCatalogLink(str1))
                .as("В списке отсутсвуют некоторые элементы")
                .isEqualTo(expected);
    }
    @Test
    public void testTitleOfList() {
        header.clickOnMainNavigationLink("Каталог");
        catalogPage.clickOnSectionCatalogLink("Компьютеры и\u00a0сети");
        catalogPage.clickTextSectionItemCatalog("Комплектующие");
        Collection<String> collect = catalogPage.checkTitleOfCatalogPage("Комплектующие");
        assertThat(collect.stream().noneMatch(""::equals))
                .as("Не все комплектующие содержат названия")
                .isTrue();
    }
    @Test
    public void testGoodsOfList() {
        header.clickOnMainNavigationLink("Каталог");
        catalogPage.clickOnSectionCatalogLink("Компьютеры и\u00a0сети");
        catalogPage.clickTextSectionItemCatalog("Комплектующие");
        List<String> collect = catalogPage.checkGoodsCatalogPage("Комплектующие");
        assertThat(catalogPage.checkCountAndPrice(collect))
                .as("Товар или цена пустые")
                .isTrue();
    }
    @Test
    public void testComponentGoodsOfList() {
        header.clickOnMainNavigationLink("Каталог");
        catalogPage.clickOnSectionCatalogLink("Компьютеры и\u00a0сети");
        catalogPage.clickTextSectionItemCatalog("Комплектующие");
        List<WebElement> components = catalogPage.getGoodsComponentsByName("Комплектующие");
        assertThat(catalogPage.checkGoodsComponentsSize(components))
                .as("Размер компонентов не соответвует размеру элементов")
                .isTrue();

    }
    @Test
    public void testTitleGoodsOfList() {
        header.clickOnMainNavigationLink("Каталог");
        catalogPage.clickOnSectionCatalogLink("Компьютеры и\u00a0сети");
        catalogPage.clickTextSectionItemCatalog("Комплектующие");
        List<WebElement> components = catalogPage.getGoodsComponentsByName("Комплектующие");
        assertThat(catalogPage.checkComponentTitlesCount(components))
                .as("Количество тайтлов не соответствует количеству компонентов")
                .isTrue();
    }
    @Test
    public void testComponentPreviewsCountGoodsOfList() {
        header.clickOnMainNavigationLink("Каталог");
        catalogPage.clickOnSectionCatalogLink("Компьютеры и\u00a0сети");
        catalogPage.clickTextSectionItemCatalog("Комплектующие");
        List<WebElement> components = catalogPage.getGoodsComponentsByName("Комплектующие");
        assertThat(catalogPage.checkComponentPreviewsCount(components))
                .as("Количество превью не соответствует количеству компонентов")
                .isTrue();
    }
    @Test
    public void testComponentDescriptionCountGoodsOfList() {
        header.clickOnMainNavigationLink("Каталог");
        catalogPage.clickOnSectionCatalogLink("Компьютеры и\u00a0сети");
        catalogPage.clickTextSectionItemCatalog("Комплектующие");
        List<WebElement> components = catalogPage.getGoodsComponentsByName("Комплектующие");
        assertThat(catalogPage.checkComponentDescriptionCount(components))
                .as("Количество описания не соответствует количеству компонентов")
                .isTrue();
    }
    @AfterSuite
    public void closeWindow() {
        header.closeAllWindow();
    }
}
