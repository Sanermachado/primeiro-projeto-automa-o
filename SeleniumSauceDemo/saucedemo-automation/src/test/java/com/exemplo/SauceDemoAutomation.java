package com.exemplo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SauceDemoAutomation {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();

        WebDriver driver = new ChromeDriver();


        try {
            driver.get("https://www.saucedemo.com/");

            // Realiza login
            login(driver, "standard_user", "secret_sauce");

            // Adiciona produto ao carrinho
            addToCart(driver, "Sauce Labs Backpack");

            checkout(driver);

        } finally {

        }
    }

    public static void login(WebDriver driver, String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.id("login-button")).click();
    }

    public static void addToCart(WebDriver driver, String productName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-cart-sauce-labs-backpack"))).click();
    }

    public static void checkout(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(By.className("shopping_cart_link"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout"))).click();

        // Preenche os dados de checkout
        driver.findElement(By.id("first-name")).sendKeys("João");
        driver.findElement(By.id("last-name")).sendKeys("Silva");
        driver.findElement(By.id("postal-code")).sendKeys("12345");

        driver.findElement(By.id("continue")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("finish"))).click();
    }
}
