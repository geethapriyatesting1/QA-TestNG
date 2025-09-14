package com.pageobjectmodel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    // --- Locators ---
    @FindBy(id = "login_Layer")   // "Login" button on homepage
    WebElement loginButtonHome;

    @FindBy(xpath = "//input[@placeholder='Enter your active Email ID / Username']")
    WebElement usernameField;

    @FindBy(xpath = "//input[@placeholder='Enter your password']")
    WebElement passwordField;

    @FindBy(xpath = "//button[text()='Login']")
    WebElement submitButton;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    // Step 1: Open login popup
    public void openLoginModalOrPage() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButtonHome)).click();
    }

    // Step 2: Enter credentials & login
    public void login(String username, String password) {
        wait.until(ExpectedConditions.visibilityOf(usernameField)).sendKeys(username);
        passwordField.sendKeys(password);
        submitButton.click();
    }
}