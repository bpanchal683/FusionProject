package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MsdSignInPage extends AbstractComponent {

    WebDriver driver;

    public MsdSignInPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[contains(text(),'AlexW@w53j6.onmicrosoft.com')]")
    WebElement email;
    @FindBy(xpath = "//input[@type='submit']")
    WebElement next;
    @FindBy(xpath = "//input[@name='passwd']")
    WebElement enterPass;
    @FindBy(xpath = "//input[@name='loginfmt']")
    WebElement enterMail;
    @FindBy(css = ".win-button")
    WebElement signIn;
    @FindBy(xpath = "//input[@name='DontShowAgain']")
    WebElement signInCheckBox;
    @FindBy(xpath = "//input[@value='Yes']")
    WebElement yes;





    By emailLoc=By.xpath("//div[contains(text(),'AlexW@w53j6.onmicrosoft.com')]");
    By enterMailLoc=By.xpath("//input[@name='loginfmt']");
    By nextLoc=By.xpath("//input[@type='submit']");
    By enterPassLoc=By.xpath("//input[@name='passwd']");
    By signInLoc= By.cssSelector(".win-button");
    By signInCheckboxLoc=By.xpath("//input[@name='DontShowAgain']");
    By yesLoc=By.xpath("//input[@value='Yes']");

    public void enterMail(String mail)
    {
        waitForFieldToBeReady(enterMailLoc);
        sendKeysToElement(enterMailLoc,mail);
        clickElement(nextLoc);
    }

    public void enterPassword(String pass)
    {
        waitForElementClick(enterPassLoc);
        sendKeysToElement(enterPassLoc,pass);
        clickElement(signInLoc);
    }

    public void staySignIn()
    {
        waitForElementClick(signInCheckboxLoc);
        clickElement(signInCheckboxLoc);
        yes.click();
    }

    public void clickMail()
    {
        waitForElementClick(emailLoc);
        clickElement(emailLoc);
    }

}
