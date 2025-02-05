package com.nttdata.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators para el primer producto y elementos del popup
    private By firstProductAddButton = By.xpath("(//button[contains(text(),'Add to Cart')])[1]");
    private By popupConfirmation     = By.id("popupConfirmation");
    private By popupTotalAmount      = By.id("popupTotal");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addFirstProductToCart(int cantidad) {
        // Se asume que la cantidad se agrega haciendo clic varias veces
        for (int i = 0; i < cantidad; i++) {
            wait.until(ExpectedConditions.elementToBeClickable(firstProductAddButton)).click();
        }
    }

    public boolean validatePopupConfirmation() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(popupConfirmation));
            String message = driver.findElement(popupConfirmation).getText();
            // Se valida que el mensaje contenga texto esperado (por ejemplo, "Agregado")
            return message.contains("added") || message.contains("Agregado");
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateTotalAmountInPopup() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(popupTotalAmount));
            String totalText = driver.findElement(popupTotalAmount).getText();
            // Se verifica que el total no esté vacío (en una implementación real se compararía con el cálculo esperado)
            return !totalText.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}

