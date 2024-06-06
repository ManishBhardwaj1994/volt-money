package com.voltmoney.pom;

import com.voltmoney.constant.ProductDetail;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ProductPage {
    WebDriver driver;
    private List<Map<ProductDetail, String>> selectedProducts;
    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements( driver, this);
        selectedProducts = new LinkedList<>();
    }

    @FindBy(xpath="//*[@class='pricebar']/button")
    private List<WebElement> addToCartButton;
    @FindBy(xpath="//span[@class='shopping_cart_badge']")
    private WebElement shoppingCartBadge;
    @FindBy(xpath="//a[@class='shopping_cart_link']")
    private WebElement shoppingCartLink;
    @FindBy(xpath="//div[@class='inventory_item_desc']")
    private List<WebElement> productDescription;
    @FindBy(xpath="//div[@class='inventory_item_price']")
    private List<WebElement> productPrice;
    @FindBy(xpath="//div[@class='inventory_item_name ']")
    private List<WebElement> productName;

    public void clickAddToCartButton(int index) {
        addToCartButton.get(index).click();
        selectedProducts.add(Map.of(
                ProductDetail.NAME, productName.get(index).getText(),
                ProductDetail.DESCRIPTION, productDescription.get(index).getText(),
                ProductDetail.PRICE, productPrice.get(index).getText()
        ));
    }

    public List<Map<ProductDetail, String>> getListOfProductsAddedInTheCart(){
        return selectedProducts;
    }

    public String getTextFromAddToCartButton(int index) {
        return addToCartButton.get(index).getText();
    }

    public void clickRemoveFromCartButton(int index) {
        addToCartButton.get(index).click();
        selectedProducts.remove(index);
    }

    public String getShoppingCartTotal() {
        return shoppingCartBadge.getText();
    }

    public void clickShoppingCartLink() {
        shoppingCartLink.click();
    }

}
