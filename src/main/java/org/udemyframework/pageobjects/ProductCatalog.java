package org.udemyframework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.udemyframework.AbstractComponents.AbstractComponent;

import java.util.Collection;
import java.util.List;

public class ProductCatalog extends AbstractComponent {

    WebDriver driver;

    public ProductCatalog(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".mb-3")
    private List<WebElement> products;

    @FindBy(css = ".ng-animating")
    private WebElement spinner;

    By productsBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");

    public List<WebElement> getProductList() {
        waitForElementAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName) {
        return getProductList().stream().filter(product ->
                product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
    }

    public void addProductToCart(String productName) throws InterruptedException {
        WebElement foundProduct = getProductByName(productName);
        foundProduct.findElement(addToCart).click();
        waitForElementAppear(toastMessage);
        waitForElementToDisappear();
    }

}
