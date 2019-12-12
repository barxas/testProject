package ru.yandex.market;

import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import static org.junit.Assert.*;


public class YandexMarketTest{
    public ChromeDriver driver;
    protected YandexMarketHomePage homePageActions;
    protected TreadsForCatsPage treadsForCatsActions;
    protected ProductPage product;
    protected ComparePage compare;
    protected String productNameFromComparePageFirst;
    protected String productNameFromComparePageSecond;
    protected String productNameFirst;
    protected String priceFirstProduct;
    protected String priceSecondProduct;
    protected String productNameSecond;
    protected String url = "https://market.yandex.ru/";
    @Before
    public void setUp(){

        String path = System.getProperty("user.dir")
                + File.separator+"chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver();
        driver.get(url);

        homePageActions = new YandexMarketHomePage(driver);

        treadsForCatsActions = homePageActions.clickOnTreatForCats();

        treadsForCatsActions.setFilters();
        treadsForCatsActions.setManufacturers(1);

        product = treadsForCatsActions.goToProductPage(1);

        productNameFirst = product.getProductName();
        product.addToCompare();

        treadsForCatsActions.setManufacturers(1);
        treadsForCatsActions.setManufacturers(2);

        product = treadsForCatsActions.goToProductPage(2);

        productNameSecond = product.getProductName();
        product.addToCompare();

        compare = treadsForCatsActions.goToComparePage();

        productNameFromComparePageFirst = compare.productNameFirst();
        productNameFromComparePageSecond = compare.productNameSecond();

        priceFirstProduct = compare.productPriceFirst();
        priceSecondProduct = compare.productPriceSecond();

    }

    @After
    public void close(){
        driver.quit();
    }

    @Description("Check that the name of product is correct")
    @Test
    public void test1(){
        assertEquals(productNameFirst,productNameFromComparePageFirst);
        assertEquals(productNameSecond,productNameFromComparePageSecond);
    }

    @Description("Check that price is less than 300")
    @Test
    public void test2(){
        assertTrue(Integer.parseInt(priceFirstProduct)+Integer.parseInt(priceSecondProduct) <= 300);
    }

    @Description("Check that product has been removed")
    @Test
    public void test3(){
        compare.deleteProduct();
        assertFalse(compare.ifExist(productNameFirst));
    }

    @Description("Check that all products has been removed")
    @Test
    public void test4(){
        compare.deleteProduct();
        compare.deleteAllProducts();
        assertTrue(compare.ifEmpty());
    }
}