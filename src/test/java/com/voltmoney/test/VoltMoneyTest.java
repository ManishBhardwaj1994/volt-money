package com.voltmoney.test;

import com.voltmoney.constant.Browser;
import com.voltmoney.constant.Url;
import com.voltmoney.helper.BrowserFactory;
import com.voltmoney.pojo.testcase.TestCase;
import com.voltmoney.pom.*;
import com.voltmoney.testdata.TestData;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;
import static com.voltmoney.constant.Url.PRODUCT_PAGE;
import static com.voltmoney.helper.GsonHelper.getGson;

public class VoltMoneyTest {

    WebDriver webDriver;

    @BeforeTest
    public void launchBrowser(){
        this.webDriver = BrowserFactory.getBrowser(Browser.CHROME);
    }

    @Test(dataProvider = "loginTestCases", dataProviderClass = TestData.class)
    public void loginTestCase(TestCase testCase){
        this.webDriver.get(Url.SAUCE_DEMO_URL.getUrl());
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login(testCase.getUsername(), testCase.getPassword());
        switch(testCase.getResultStatus()){
            case SUCCESS:
                Assert.assertTrue(testCase.getExpectedResult().equalsIgnoreCase(webDriver.getCurrentUrl()), "Failed to login successfully");
                break;
            case REJECTED:
                Assert.assertEquals(loginPage.getFailedLoginAttemptErrorMessage(), testCase.getExpectedResult());
                break;
        }
    }

    @SneakyThrows
    @Test
    public void SingleProductPurchaseTestCase(){
        this.webDriver.get(Url.SAUCE_DEMO_URL.getUrl());
        /**
         * Login to the application
         */
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(webDriver.getCurrentUrl(), PRODUCT_PAGE.getUrl(), "Failed to login");

        /**
         * Add product to the cart
         */
        ProductPage productPage = new ProductPage(webDriver);
        productPage.clickAddToCartButton(0);
        Assert.assertEquals(productPage.getShoppingCartTotal(), "1");
        Assert.assertEquals(productPage.getTextFromAddToCartButton(0), "Remove");

        /**
         * Remove product from the cart
         */
        productPage.clickRemoveFromCartButton(0);
        Assert.assertEquals(productPage.getTextFromAddToCartButton(0), "Add to cart");

        /**
         * Add product to the cart
         */
        productPage.clickAddToCartButton(0);
        Assert.assertEquals(productPage.getShoppingCartTotal(), "1");
        Assert.assertEquals(productPage.getTextFromAddToCartButton(0), "Remove");
        List<Map<String, String>> selectedProducts = productPage.getListOfProductsAddedInTheCart();
        System.out.println("User Selected Products: " + getGson().toJson(selectedProducts));

        productPage.clickShoppingCartLink();
        /**
         * Navigate to the cart page
         */
        CartPage cartPage = new CartPage(webDriver);

        /**
         * Verify the product details in the cart page, which user added from the product page
         */
        List<Map<String, String>> cartProductDetails = cartPage.getProductDetails();
        // asserting if the product details are same as selected products in the cart.
        Assert.assertEquals(cartProductDetails.size(), selectedProducts.size(), "Number of products do not match.");
        JSONAssert.assertEquals(getGson().toJson(cartProductDetails), getGson().toJson(selectedProducts), true);
        cartPage.clickCheckoutButton();

        /**
         * Fill the checkout information
         */
        CheckoutInformationPage checkoutInformationPage = new CheckoutInformationPage(webDriver);
        checkoutInformationPage.fillForm("Manish", "Bhardwaj", "12345");
        checkoutInformationPage.clickContinueButton();

        /**
         * Verify the product details in the checkout overview page
         */
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(webDriver);
        List<Map<String, String>> allInventoryProducts = checkoutOverviewPage.getAllInventoryProducts();
        Assert.assertEquals(allInventoryProducts.size(), selectedProducts.size(), "Number of products do not match.");
        JSONAssert.assertEquals(getGson().toJson(allInventoryProducts), getGson().toJson(selectedProducts), true);

        checkoutOverviewPage.verifyShippingInformation();
        checkoutOverviewPage.verifyPaymentInformation();
        Assert.assertEquals(checkoutOverviewPage.getBillingNumber(), "SauceCard #31337", "Billing number not matching.");
        Assert.assertEquals(checkoutOverviewPage.getDeliveryType(), "Free Pony Express Delivery!", "Delivery type not matching.");

        double totalPriceBeforeTax = allInventoryProducts.stream().map(product -> product.get("price").replace("$", "")).mapToDouble(Double::parseDouble).sum();
        Assert.assertEquals(totalPriceBeforeTax, checkoutOverviewPage.getTotalBeforeTax(), "Total price before tax does not match.");
        Assert.assertEquals(totalPriceBeforeTax, checkoutOverviewPage.getTotalBeforeTax(), "Total price after tax does not match.");

        checkoutOverviewPage.clickFinishButton();

        /**
         * Verify the order completion
         */
        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(webDriver);
        Assert.assertEquals(checkoutCompletePage.getThankMessageText(), "Thank you for your order!", "Thanks message does not match.");
        Assert.assertEquals(checkoutCompletePage.getDispatchMessageText(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!" ,"Order completion message does not match.");
        checkoutCompletePage.clickBackHomeButton();

    }

    @AfterTest
    public void shutdownBrowser(){
        this.webDriver.close();
    }

}
