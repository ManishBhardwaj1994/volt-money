package com.voltmoney.helper;

import com.voltmoney.constant.Browser;
import com.voltmoney.constant.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserFactory {
    public static WebDriver getBrowser(Browser browserName){
        WebDriver driver = null;
        switch (browserName){
            case CHROME:
                driver = new ChromeDriver();
                System.setProperty(Driver.CHROME.getDriverType(), System.getProperty("user.dir")+Driver.CHROME.getDriverLocation());
                driver.manage().window().maximize();
                break;
            default:
                System.out.println("Invalid browser name");
        }
        return driver;
    }


}
