package org.udemyframework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.udemyframework.AbstractComponents.AbstractComponent;

import java.util.List;

public class CartPage extends AbstractComponent {
    WebDriver driver;

    @FindBy(css = ".cartSection h3")
    private List<WebElement> cartProducts;

    @FindBy(css = ".totalRow button")
    private WebElement checkoutElement;

    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean verifyProductDisplay(String productName) {
        return cartProducts.stream().anyMatch(cartProduct ->
                cartProduct.getText().equalsIgnoreCase(productName));
    }

    public CheckoutPage goToCheckout() {
        checkoutElement.click();
        return new CheckoutPage(driver);
    }
}
