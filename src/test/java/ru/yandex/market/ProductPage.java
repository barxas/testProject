package ru.yandex.market;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import io.qameta.allure.Step;

import java.util.Set;

public class ProductPage {

    WebDriver driver;

    String currentUrl;

    By compareButton = By.cssSelector(".n-product-toolbar_style_simple .image_name_compare");
    By productName = By.cssSelector(".n-title__text");


    public ProductPage(WebDriver driver,String currentUrl){
        this.currentUrl = currentUrl;
        this.driver = driver;
    }
    public String getProductName(){
        String currentHandle= driver.getWindowHandle();
        Set<String> handles=driver.getWindowHandles();
        for(String actual: handles)
        {
            if(!actual.equalsIgnoreCase(currentHandle))
            {
                driver.switchTo().window(actual);
            }
            else{
                driver.close();
            }
        }
        String name = driver.findElement(productName).getText();
        return name;
    }

    @Step("Add product to compare page")
    public void addToCompare(){

        driver.findElement(compareButton).click();
        if(!currentUrl.equals(""))
            driver.get(currentUrl);
    }
}
