package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RequsitionPage extends AbstractComponent {

    WebDriver driver;

    public RequsitionPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".xmo")
    WebElement moreTask;
    @FindBy(xpath = "//td[contains(text(),'Enter Requisition Line')]")
    WebElement enterRequisition;

    By moreTaskWait= By.cssSelector(".xmo");
    By enterRequisitionLoc=By.xpath("//td[contains(text(),'Enter Requisition Line')]");


    public EnterRequisitionPage clickRequsition() throws InterruptedException {
        //Thread.sleep(2000);
        waitForElementPresence(moreTaskWait);
        moreTask.click();
        //Thread.sleep(1000);
        jsClick(enterRequisitionLoc);
        //enterRequisition.click();
        EnterRequisitionPage enterRequisitionPage=new EnterRequisitionPage(driver);
        return enterRequisitionPage;

    }

}
