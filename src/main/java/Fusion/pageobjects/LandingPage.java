package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponent {

    WebDriver driver;

    public LandingPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "userid")
    WebElement email;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id = "btnActive")
    WebElement button;

    By buttonLoc=By.id("btnActive");
    By emailLoc=By.id("userid");
    By passwordLoc=By.id("password");

    public HomePage login(String username, String password1) throws InterruptedException {
        sendKeysToElement(emailLoc,username);
        sendKeysToElement(passwordLoc,password1);
        clickElement(buttonLoc);
        clickElement(buttonLoc);
        HomePage homePage=new HomePage(driver);
        return homePage;
    }

}
