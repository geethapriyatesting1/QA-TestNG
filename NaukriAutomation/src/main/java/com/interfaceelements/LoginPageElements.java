package com.interfaceelements;

import org.openqa.selenium.By;

public class LoginPageElements {
    // Note: Naukri's live site uses dynamic/complex locators. These examples are generic and may need update.
    public static final By LOGIN_BUTTON_HOME = By.xpath("//a[text()='Login' or contains(@href,'login')]");
    public static final By EMAIL_INPUT = By.id("loginFormEmail");      // update when actual id differs
    public static final By PASSWORD_INPUT = By.id("loginFormPassword");
    public static final By SUBMIT_LOGIN = By.xpath("//button[contains(text(),'Login') or @type='submit']");
    public static final By PROFILE_LINK = By.xpath("//a[contains(@href,'my-profile') or contains(text(),'My Naukri')]");
    public static final By UPLOAD_RESUME = By.id("attachCV"); // example id; update as per site
    public static final By LOGOUT_LINK = By.xpath("//a[contains(text(),'Logout')]");
}