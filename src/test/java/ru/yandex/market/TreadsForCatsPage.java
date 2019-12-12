package ru.yandex.market;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.qameta.allure.Step;

import java.util.regex.Pattern;

public class TreadsForCatsPage {
    private WebDriver driver;

    By minPrice = By.cssSelector("#glpricefrom");
    By maxPrice = By.cssSelector("#glpriceto");
    By deliveryMethod = By.cssSelector("[for='offer-shipping_delivery'] .rCLpHJFFlJ");
    By manufacturer1 = By.cssSelector("[for='7893318_10739158']");
    By manufacturer2 = By.cssSelector("[for='7893318_10717513']");
    By showAllManufacturer = By.cssSelector("[data-autotest-id='7893318'] button");
    By searchManufacturer = By.cssSelector("[name='Поле поиска']");
    By product = By.cssSelector(".n-snippet-list>:nth-child(1) .n-snippet-card2__title a");
    By product2 = By.cssSelector(".n-snippet-list>:nth-child(2) .n-snippet-card2__title a");
    By compare = By.cssSelector(".header2-menu__item_type_compare");
    String productName = "Мнямс";
    String productName2 = "Whiskas";
    String setMinPrice = "50";
    String setMaxPrice = "150";
    String currentUrl ="";

    public TreadsForCatsPage(WebDriver driver){
        this.driver = driver;
    }

    @Step("Set filters(min price, max price, delivery method)")
    public void setFilters(){
        driver.findElement(minPrice).sendKeys(setMinPrice);
        driver.findElement(maxPrice).sendKeys(setMaxPrice);
        driver.findElement(deliveryMethod).click();
    }

    @Step("Set manufacturers")
    public void setManufacturers(int numOfProduct){
        if(numOfProduct == 1) {
            if (driver.findElements(manufacturer1).size() <= 0) {
                if(elementCheck(showAllManufacturer))
                    driver.findElement(showAllManufacturer).click();
                driver.findElement(searchManufacturer).sendKeys(productName);
            }
            driver.findElement(manufacturer1).click();
        }
        if(numOfProduct == 2) {
            if (driver.findElements(manufacturer2).size() <= 0) {
                if(elementCheck(showAllManufacturer))
                    driver.findElement(showAllManufacturer).click();
                driver.findElement(searchManufacturer).sendKeys(productName2);
            }
            driver.findElement(manufacturer2).click();
        }

    }
    public boolean elementCheck(By element){
        if (driver.findElements(element).size() <= 0)
            return false;
        else
            return true;
    }
    public void pageIsReady(By element, String str){
        WebElement elem = driver.findElement(element);
        if(!elem.isSelected()) {
            WebDriverWait wait = new WebDriverWait(driver, 5,1000);
            wait.until(ExpectedConditions.textMatches(element, Pattern.compile(str)));
        }
    }

    @Step("Go to compare page")
    public ComparePage goToComparePage(){
        driver.findElement(compare).click();
        return new ComparePage(driver);
    }

    @Step("Go to product page")
    public ProductPage goToProductPage(int numOfProduct){
        if(numOfProduct==1){
            pageIsReady(product,productName);
            driver.findElement(product).click();
        }
        if(numOfProduct==2){
            pageIsReady(product2,productName2);
            driver.findElement(product2).click();
        }
        currentUrl = driver.getCurrentUrl();
        return new ProductPage(driver,currentUrl);
    }
}
