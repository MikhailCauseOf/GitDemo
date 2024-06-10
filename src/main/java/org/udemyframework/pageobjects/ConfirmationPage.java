package org.udemyframework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.udemyframework.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {

    WebDriver driver;

    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (css = ".hero-primary")
    private WebElement confirmationMessage;

    public String getConfirmationMessage(){
        return confirmationMessage.getText();
    }
}