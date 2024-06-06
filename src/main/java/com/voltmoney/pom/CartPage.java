package com.voltmoney.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements( driver, this);
    }

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(xpath = "//button[@id='remove-sauce-labs-bike-light']")
    private WebElement removeProductButton;

    @FindBy(xpath = "//span[@class='title']")
    private WebElement cartTitle;

    @FindBy(xpath = "//div[@class='cart_list']/div/div/div/div")
    private List<WebElement> productQuantity;

    @FindBy(xpath = "//div[@class='cart_item_label']/a/div")
    private List<WebElement> productTitle;

    @FindBy(xpath = "//div[@class='cart_item_label']/div[1]")
    private List<WebElement> productDescription;
    @FindBy(xpath = "//div[@class='cart_item_label']/div[2]/div")
    private List<WebElement> productPrice;

    public void clickContinueShoppingButton() {
        continueShoppingButton.click();
    }

    public void clickCheckoutButton() {
        checkoutButton.click();
    }

    public void removeProductFromCart() {
        removeProductButton.click();
    }

    public String cartPageTitle() {
        return cartTitle.getText();
    }

    public Map<String, String> getProductDetails(int index) {
        Map<String, String> productDetails = Map.of(
                "name", productTitle.get(index).getText(),
                "description", productDescription.get(index).getText(),
                "price", productPrice.get(index).getText()
        );
        return productDetails;
    }

    public List<Map<String, String>> getProductDetails() {
        List<Map<String, String>> productDetails = new LinkedList<>();
        for(int i=0; i<productTitle.size(); i++){
            productDetails.add(getProductDetails(i));
        }
        return productDetails;
    }

}
