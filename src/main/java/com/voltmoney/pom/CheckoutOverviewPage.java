package com.voltmoney.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CheckoutOverviewPage {
    WebDriver driver;

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements( driver, this);
    }

    @FindBy(className = "inventory_item_name") private List<WebElement> inventoryItemName;
    @FindBy(className = "inventory_item_desc") private List<WebElement> inventoryItemNameDescription;
    @FindBy(className = "inventory_item_price") private List<WebElement> inventoryItemNamePrice;
    @FindBy(xpath = "//div[normalize-space()='Shipping Information:']") private WebElement shippingInformation;
    @FindBy(xpath = "//div[@class='summary_info']/div[2]") private WebElement billNumber;
    @FindBy(xpath = "//div[normalize-space()='Shipping Information:']") private WebElement paymentInformation;
    @FindBy(xpath = "//div[@class='summary_info']/div[4]") private WebElement deliveryType;
    @FindBy(className = "summary_subtotal_label") private WebElement totalBeforeTax;
    @FindBy(className = "summary_tax_label") private WebElement tax;
    @FindBy(className = "summary_total_label") private WebElement totalAfterTax;
    @FindBy(id = "cancel") private WebElement cancelButton;
    @FindBy(id = "finish") private WebElement finishButton;

    public void clickCancelButton(){
        cancelButton.click();
    }

    public void clickFinishButton(){
        finishButton.click();
    }

    public Map<String, String> getInventoryProduct(int index){
        return Map.of(
                "name", inventoryItemName.get(index).getText(),
                "description", inventoryItemNameDescription.get(index).getText(),
                "price", inventoryItemNamePrice.get(index).getText()
        );
    }

    public List<Map<String, String>> getAllInventoryProducts(){
        List<Map<String, String>> inventoryProducts = new LinkedList<>();
        for(int i=0; i<inventoryItemName.size(); i++){
            inventoryProducts.add(getInventoryProduct(i));
        }
        return inventoryProducts;
    }

    public double getTotalBeforeTax(){
        return Double.parseDouble(totalBeforeTax.getText().replace("Item total: $", ""));
    }

    public double getTax(){
        return Double.parseDouble(tax.getText().replace("Tax: $", ""));
    }

    public double getTotalAfterTax(){
        return Double.parseDouble(totalAfterTax.getText().replace("Total: $", ""));
    }

    public boolean verifyShippingInformation(){
        return shippingInformation.isDisplayed();
    }

    public boolean verifyPaymentInformation(){
        return paymentInformation.isDisplayed();
    }

    public String getBillingNumber(){
        return billNumber.getText();
    }

    public String getDeliveryType(){
        return deliveryType.getText();
    }
}
