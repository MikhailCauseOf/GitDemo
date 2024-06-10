package udemyframework.tests;


import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.udemyframework.pageobjects.CartPage;
import org.udemyframework.pageobjects.ProductCatalog;
import udemyframework.TestComponents.BaseTest;
import udemyframework.TestComponents.Retry;

import java.util.List;

public class ErrorValidationsTest extends BaseTest {

    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void loginErrorValidation() {
        ProductCatalog productCatalog  = landingPage.loginApplication("misha.kma4@gmail.com", "Ratty!1");
        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
    }

    @Test
    public void productErrorValidation() throws InterruptedException {
        String productName = "ZARA COAT 3";

        ProductCatalog productCatalog  = landingPage.loginApplication("udemytestframework@gmail.com", "Rahulshetty!1");

        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(productName);
        CartPage cartPage = productCatalog.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
        Assert.assertFalse(match);

    }
}
