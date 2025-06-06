package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageOrdersPage extends AbstractComponent {

    WebDriver driver;

    public ManageOrdersPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[contains(@id,'Purch1:0:AP1:r1:0:q1:value40::content')]")
    WebElement order;
    @FindBy(xpath = "//*[contains(@id,'Purch1:0:AP1:r1:0:q1::search')]")
    WebElement search;
    @FindBy(xpath = "//*[contains(@id,'Purch1:0:AP1:r1:0:AT1:_ATp:table1:0:ot49')]")
    WebElement poStatus;
    @FindBy(css = ".x1ax.x1tn")
    WebElement expand;

    By orderLoc=By.xpath("//*[contains(@id,'Purch1:0:AP1:r1:0:q1:value40::content')]");
    By postatusWait=By.xpath("//*[contains(@id,'Purch1:0:AP1:r1:0:AT1:_ATp:table1:0:ot49')]");
    By expandWait=By.cssSelector(".x1ax.x1tn");
    By searchLoc=By.xpath("//*[contains(@id,'Purch1:0:AP1:r1:0:q1::search')]");

    public void verifyAndApprovePO(String poNum) throws InterruptedException {
        waitForElementPresence(orderLoc);
        //order.sendKeys(poNum);
        sendKeysToElement(orderLoc,poNum);
        //search.click();
        clickElement(searchLoc);
        waitForElementPresence(postatusWait);
        String s=poStatus.getText();
        System.out.println(s);
        if (s.equalsIgnoreCase("Open")) {
            // Code to execute if condition is true
            System.out.println("Purchase Order is Approved");
        } else {

            // Code to execute if condition is false
            while(!s.equalsIgnoreCase("Open"))
            {
                waitForElementPresence(expandWait);
                expand.click();
                //search.click();
                clickElement(searchLoc);
                System.out.println("refreshing the search");
                waitForElementPresence(postatusWait);
                s=poStatus.getText();

            }
        }
    }


}
