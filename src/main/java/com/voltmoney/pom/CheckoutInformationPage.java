package com.voltmoney.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutInformationPage {
    WebDriver driver;

    public CheckoutInformationPage(WebDriver driver) {
        this.driver=driver;

        PageFactory.initElements( driver, this);
    }

    @FindBy(id = "first-name")
    private WebElement firstNameField;
    @FindBy(id = "last-name")
    private WebElement lastNameField;
    @FindBy(id = "postal-code")
    private WebElement postalCodeField;
    @FindBy(id = "cancel")
    private WebElement cancelButton;
    @FindBy(id = "continue")
    private WebElement continueButton;


    public void enterFirstName(String firstName){
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName){
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void enterPostalCode(String postalCode){
        postalCodeField.clear();
        postalCodeField.sendKeys(postalCode);
    }

    public void fillForm(String firstName, String lastName, String postalCode){
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
    }

    public void clickCancelButton(){
        cancelButton.click();
    }

    public void clickContinueButton(){
        continueButton.click();
    }
}
