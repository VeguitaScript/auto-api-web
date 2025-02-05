package com.nttdata.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;


    private By checkoutButton    = By.id("checkoutBtn");
    private By cartTitle         = By.cssSelector("h1.cart-title");
    private By priceCalculation  = By.id("priceCalculation");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void completePurchase() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }

    public String getCartPageTitle() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartTitle)).getText();
    }

    public boolean validatePriceCalculation() {
        try {
            String calc = wait.until(ExpectedConditions.visibilityOfElementLocated(priceCalculation)).getText();
            return !calc.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}
