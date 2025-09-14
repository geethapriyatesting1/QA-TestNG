package com.pageobjectmodel;


import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    WebDriver driver;
    WebDriverWait wait;

    // Quick Links → Resume Upload link
    @FindBy(xpath = "//div[contains(@class,'quickLink')]//a[contains(text(),'Upload')]")
    WebElement resumeUploadLink;

    // Resume file input (becomes available after clicking Upload link)
    @FindBy(id = "attachCV")
    WebElement uploadResumeInput;

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    // Handle career popup if shown
    public void handleCareerPopup() {
        try {
            WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@role='dialog']//button[contains(@class,'crossIcon')] | //span[text()='×']")));
            closeBtn.click();
            System.out.println("Career popup closed.");
        } catch (Exception e) {
            System.out.println("No career popup appeared, moving ahead.");
        }
    }

    // Go to Complete Profile
    public void goToProfile() {
        try {
            WebElement completeProfileBtn = wait.until(ExpectedConditions
                    .elementToBeClickable(By.xpath("//a[contains(text(),'Complete profile')] | //button[contains(text(),'Complete profile')]")));
            completeProfileBtn.click();
            System.out.println("Navigated to Complete Profile page.");
        } catch (Exception e) {
            System.out.println("Complete Profile button not found: " + e.getMessage());
        }
    }

    // Upload resume flow
    public void uploadResume(String filePath) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Scroll to Quick Links section
            WebElement uploadLink = wait.until(
                ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//div[text()='Resume']/following-sibling::a[text()='Upload']")
                )
            );
            js.executeScript("arguments[0].scrollIntoView(true);", uploadLink);
            wait.until(ExpectedConditions.elementToBeClickable(uploadLink)).click();
            System.out.println("Clicked Resume → Upload link.");

            // Upload file
            WebElement fileInput = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']"))
            );
            fileInput.sendKeys(filePath);
            System.out.println("Resume file sent to input: " + filePath);

            // Wait for success message
            wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//*[contains(text(),'successfully') or contains(text(),'uploaded')]")
            ));
            System.out.println("Resume uploaded successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Resume upload failed.", e);
        }
    }

    // Confirm upload
    public boolean isResumeUploaded() {
        try {
            WebElement successMsg = wait.until(ExpectedConditions
                    .visibilityOfElementLocated(By.xpath("//*[contains(text(),'Profile last updated') or contains(text(),'successfully uploaded')]")));
            return successMsg.isDisplayed();
        } catch (Exception e) {
            System.out.println("Upload confirmation not found.");
            return false;
        }
    }
}