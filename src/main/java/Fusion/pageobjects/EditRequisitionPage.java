package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditRequisitionPage extends AbstractComponent {

    WebDriver driver;

    public EditRequisitionPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[contains(@id,'_FONSr2:0:_FOTsr1:2:AP1:SPsb2')]")
    WebElement submit;

    By submitWait=By.xpath("//*[contains(@id,'_FONSr2:0:_FOTsr1:2:AP1:SPsb2')]");

    public RequisitionsPage clickSubmit()
    {
        waitForElementPresence(submitWait);
        submit.click();
        RequisitionsPage requisitionsPage=new RequisitionsPage(driver);
        return requisitionsPage;
    }
}
