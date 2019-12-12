package ru.yandex.market;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import io.qameta.allure.Step;

import java.util.concurrent.TimeUnit;

public class YandexMarketHomePage{


    private WebDriver driver;
    By allCategories = By.cssSelector(".n-w-tab_js_inited");
    By forAnimals = By.cssSelector(".n-w-tab_type_navigation-menu-vertical [href='/catalog--tovary-dlia-zhivotnykh/54496']");
    By treatForCats = By.cssSelector("[href='/catalog--lakomstva-dlia-koshek/62819/list?hid=15963668']");



    public YandexMarketHomePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Go to TreatsForCats page")
    public TreadsForCatsPage clickOnTreatForCats() {
        driver.findElement(allCategories).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(forAnimals)).build().perform();
        driver.findElement(treatForCats).click();
        return new TreadsForCatsPage(driver);
    }


}
