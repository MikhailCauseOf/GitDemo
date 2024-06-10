package org.udemyframework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.udemyframework.AbstractComponents.AbstractComponent;

import java.util.List;

public class OrderPage extends AbstractComponent {
    public WebDriver driver;

    @FindBy(css = "tr td:nth-child(3)")
    private List<WebElement> productNames;

    public OrderPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Boolean verifyOrderDisplay(String productName) {
        return productNames.stream().anyMatch(cartProduct ->
                cartProduct.getText().equalsIgnoreCase(productName));
    }
}
