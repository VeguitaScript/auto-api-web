package com.nttdata.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By categoryLink;
    private By subcategoryLink;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean selectCategoryAndSubcategory(String categoria, String subcategoria) {
        try {
            categoryLink = By.xpath("//a[text()='" + categoria + "']");
            wait.until(ExpectedConditions.elementToBeClickable(categoryLink)).click();

            subcategoryLink = By.xpath("//a[text()='" + subcategoria + "']");
            wait.until(ExpectedConditions.elementToBeClickable(subcategoryLink)).click();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isUserLoggedIn() {
        try {
            return driver.findElement(By.id("logoutBtn")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
