package com.base;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import com.utility.FileReaderManager;

public class BaseClass {
    public WebDriver driver;

    public WebDriver initDriver() {
        try {
            System.out.println("Step 1: Reading browser from config");
            String browser = FileReaderManager.getInstance().getConfigReader().getBrowser();
            if (browser == null) browser = "chrome";
            System.out.println("Browser = " + browser);

            if (browser.equalsIgnoreCase("chrome")) {
                System.out.println("Step 2: Setting up ChromeDriver using WebDriverManager");
                WebDriverManager.chromedriver().setup();

                ChromeOptions opts = new ChromeOptions();
                opts.addArguments("--start-maximized");
                System.out.println("Step 3: Launching Chrome browser");
                driver = new ChromeDriver(opts);
            } else {
                throw new RuntimeException("Only Chrome is configured in this template.");
            }

            System.out.println("Step 4: Setting timeouts");
            int implicit = FileReaderManager.getInstance().getConfigReader().getImplicitWait();
            int pageLoad = FileReaderManager.getInstance().getConfigReader().getPageLoadTimeout();

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicit));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoad));

            System.out.println("Step 5: Driver initialized successfully");
            return driver;
        } catch (Exception e) {
            System.out.println("===== ERROR in initDriver() =====");
            e.printStackTrace();
            throw e;
        }
    }

    public void openUrl(String url) {
        driver.get(url);
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}