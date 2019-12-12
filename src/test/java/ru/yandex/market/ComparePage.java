package ru.yandex.market;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import io.qameta.allure.Step;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComparePage {

    private WebDriver driver;
    By secondProduct = By.cssSelector(".n-compare-content__line_js_inited :nth-child(1) .n-compare-head__name");
    By firstProduct = By.cssSelector(".n-compare-content__line_js_inited :nth-child(2) .n-compare-head__name");
    By secondProductPrice = By.cssSelector(".n-compare-row_type_price .n-compare-content :nth-child(1) .price");
    By firstProductPrice = By.cssSelector(".n-compare-row_type_price .n-compare-content :nth-child(2) .price");
    By deleteButton = By.cssSelector(".n-compare-content__line_js_inited :nth-child(1) ._popup2-destructor");
    By deleteAllButton = By.cssSelector(".n-compare-toolbar__action-clear");
    By empty = By.cssSelector(".n-compare-toolbar__action-clear");
    By buttonToolBar = By.cssSelector(".n-compare-content__line_js_inited :nth-child(1) .n-compare-head__toolbar");

    public  ComparePage(WebDriver driver){
        this.driver = driver;
    }


    public String productPriceFirst(){
        String name = driver.findElement(firstProductPrice).getText();
        name = intFromStr(name);
        return name;
    }
    public String productPriceSecond(){
        String name = driver.findElement(secondProductPrice).getText();
        name = intFromStr(name);
        return name;
    }
    public String productNameFirst(){
        String name = driver.findElement(firstProduct).getText();
        return name;
    }
    public String productNameSecond(){
        String name = driver.findElement(secondProduct).getText();
        return name;
    }

    public boolean ifExist(String productName){
        if (driver.findElement(secondProduct).getText().equals(productName)) {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean ifEmpty(){
        if (driver.findElements(empty).size() != 0)
            return true;
        else
            return false;
    }

    @Step("Delete product from compare page")
    public void deleteProduct(){
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(buttonToolBar)).build().perform();
        action.moveToElement(driver.findElement(deleteButton)).build().perform();
        driver.findElement(deleteButton).click();
    }

    @Step("Delete all products from compare page")
    public void deleteAllProducts(){
        driver.findElement(deleteAllButton).click();
    }

    public String intFromStr(String str){
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(str);

        String s ="";

        while(m.find()){
            s = s+m.group();
        }
        return s;
    }
}