package udemyframework.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.udemyframework.pageobjects.*;
import udemyframework.TestComponents.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrderTest(HashMap<String, String> input) throws InterruptedException {

        ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));

        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(input.get("product"));
        CartPage cartPage = productCatalog.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);

        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("india");

        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String confirmationMessage = confirmationPage.getConfirmationMessage();

        Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @Test(dependsOnMethods = {"submitOrderTest"})
    public void orderHistoryTest() {
        ProductCatalog productCatalog = landingPage.loginApplication("misha.kma4@gmail.com", "Rahulshetty!1");
        OrderPage orderPage = productCatalog.goToOrderPage();
        Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
    }



    @DataProvider
    public Object[][] getData() throws IOException {
        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\main\\java\\org\\udemyframework\\data\\PurchaseOrder.json");

        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }
}
