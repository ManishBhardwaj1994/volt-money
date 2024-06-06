package com.voltmoney.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage {

    WebDriver driver;

    public CheckoutCompletePage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements( driver, this);
    }

    @FindBy(id = "back-to-products") private WebElement backHomeButton;
    @FindBy(className = "complete-header")  private WebElement thankMessageText;
    @FindBy(className = "complete-text") private WebElement dispatchMessageText;


    public void clickBackHomeButton(){
        backHomeButton.click();
    }

    public String getThankMessageText(){
        return thankMessageText.getText();
    }

    public String getDispatchMessageText(){
        return dispatchMessageText.getText();
    }





}
