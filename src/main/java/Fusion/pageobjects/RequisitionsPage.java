package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RequisitionsPage extends AbstractComponent {

    WebDriver driver;

    public RequisitionsPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[@id='_FOpt1:_FOr1:0:_FONSr2:0:_FOTsr1:0:SP4:panelGroupLayout2']")
    WebElement requsitionId;
    @FindBy(xpath = "//*[contains(@id,'_FOTsr1:0:SP4:commandButton2')]")
    WebElement ok;
    @FindBy(linkText = "View More")
    WebElement viewMore;

    By requisitionsIdWait=By.xpath("//div[@id='_FOpt1:_FOr1:0:_FONSr2:0:_FOTsr1:0:SP4:panelGroupLayout2']");
    By viewMoreLoc=By.linkText("View More");

        public String getRequsitionId() throws InterruptedException {
            waitForElementVisible(requisitionsIdWait);
            //Thread.sleep(2000);
            String Req=requsitionId.getText();
            String[] Req1=Req.split("submitted");
            String[] Req2=Req1[0].split("Requisition");
            String[] Req3=Req2[1].split("was");
            String ReqNum=Req3[0].trim();
            System.out.println(ReqNum);
            ok.click();
            return ReqNum;
        }

        public ManageRequisitionsPage clickViewMore()
        {
            waitForElementPresence(viewMoreLoc);
            //viewMore.click();
            clickElement(viewMoreLoc);
            ManageRequisitionsPage manageRequisitionsPage=new ManageRequisitionsPage(driver);
            return manageRequisitionsPage;
        }
}
