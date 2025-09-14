package com.pageobjectmanager;

import org.openqa.selenium.WebDriver;
import com.pageobjectmodel.LoginPage;
import com.pageobjectmodel.ProfilePage;

public class PageObjectManager {
    private WebDriver driver;
    private LoginPage loginPage;
    private ProfilePage profilePage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage getLoginPage() {
        if (loginPage == null) loginPage = new LoginPage(driver);
        return loginPage;
    }

    public ProfilePage getProfilePage() {
        if (profilePage == null) profilePage = new ProfilePage(driver);
        return profilePage;
    }
}