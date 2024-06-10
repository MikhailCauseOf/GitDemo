package org.udemyframework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.udemyframework.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

    WebDriver driver;
    public LandingPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="userEmail")
    private WebElement userEmail;

    @FindBy(id="userPassword")
    private WebElement userPassword;

    @FindBy(id="login")
    private WebElement submitButton;

    @FindBy(css = "[class*='flyInOut']")
    private WebElement errorMessage;

    public ProductCatalog loginApplication(String email, String password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        submitButton.click();
        return new ProductCatalog(driver);
    }

    public String getErrorMessage(){
        waitForWebElementAppear(errorMessage);
        return errorMessage.getText();
    }
    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client");
    }
}
