package udemyframework.StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.udemyframework.pageobjects.*;
import udemyframework.TestComponents.BaseTest;

import java.io.IOException;
import java.util.List;

public class StepDefinitionImpl extends BaseTest {

    public LandingPage landingPage;
    public ProductCatalog productCatalog;
    public ConfirmationPage confirmationPage;

    @Given("I landed on Ecommerce Page")
    public void i_Landed_on_ecommerce_Page() throws IOException {
        landingPage = launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_username_and_password(String username, String password){
        productCatalog = landingPage.loginApplication(username, password);
    }

    @When("^I add product (.+) to Cart$")
    public void i_add_product_to_cart(String productName) throws InterruptedException {
        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(productName);
    }

    @When("^Checkout (.+) and submit the order$")
    public void checkout_submit_order(String productName){
        CartPage cartPage = productCatalog.goToCartPage();

        Boolean match = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(match);

        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("india");

        confirmationPage = checkoutPage.submitOrder();
    }

    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_confirmationPage(String string){
        String confirmationMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.equalsIgnoreCase(string));
        driver.quit();
    }

    @Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String string){
        Assert.assertEquals(landingPage.getErrorMessage(), string);
        driver.quit();
    }
}
