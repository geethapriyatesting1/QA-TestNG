package com.runner;

import org.testng.Assert;
import org.testng.annotations.*;

import com.base.BaseClass;
import com.pageobjectmanager.PageObjectManager;
import com.utility.FileReaderManager;
import com.pageobjectmodel.LoginPage;
import com.pageobjectmodel.ProfilePage;

import org.openqa.selenium.WebDriver;

@Listeners(com.listener.ExtentReportListener.class)
public class TestRunner extends BaseClass {
    WebDriver driver;
    PageObjectManager pom;

    @BeforeClass
    public void setUp() {
        System.out.println("===== STARTING DRIVER INIT =====");
        driver = initDriver();
        pom = new PageObjectManager(driver);
        System.out.println("Driver + POM initialized");
    }

    @Test(priority = 1)
    public void loginAndUploadResume() {
        String url = FileReaderManager.getInstance().getConfigReader().getUrl();
        String username = FileReaderManager.getInstance().getConfigReader().getUsername();
        String password = FileReaderManager.getInstance().getConfigReader().getPassword();
        String resumePath = FileReaderManager.getInstance().getConfigReader().getResumePath();

        // Step 1: Open site
        openUrl(url);

        // Step 2: Login
        LoginPage login = pom.getLoginPage();
        login.openLoginModalOrPage();
        login.login(username, password);

        // Step 3: Handle Fresher/Experienced popup
        ProfilePage profile = pom.getProfilePage();
        profile.handleCareerPopup();

        // Step 4: Go to Complete Profile
        profile.goToProfile();

        // Step 5: Upload Resume
        profile.uploadResume(resumePath);

        // Step 6: Assert resume upload success
        boolean uploaded = profile.isResumeUploaded();
        Assert.assertTrue(uploaded, "Resume upload failed or success message not found.");
    }

    @AfterClass
    public void tearDown() {
        quitDriver();
    }
}