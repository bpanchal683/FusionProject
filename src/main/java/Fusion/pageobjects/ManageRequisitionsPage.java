package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManageRequisitionsPage extends AbstractComponent {

    WebDriver driver;

    public ManageRequisitionsPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[contains(@id,'pt1:r1:0:ap1:r1:0:q1:value00')]")
    WebElement requsitionBu;
    @FindBy(xpath = "//option[contains(text(),'IT Convergence Singapore')]")
    WebElement requsitionBuSelect;
    @FindBy(xpath = "//*[contains(@id,'ap1:r1:0:q1:value20::content')]")
    WebElement requsition;
    @FindBy(xpath = "//*[contains(@id,'pt1:r1:0:ap1:r1:0:q1::search')]")
    WebElement search;
    @FindBy(css = "td[style=\"width:100px;\"]:nth-child(6)")
    WebElement requisitionStatus;
    @FindBy(css = ".x1ax.x1tn")
    WebElement expand;

    public  void verifyAndApproveRequisition(String reqNum) throws InterruptedException {
        requsitionBu.click();
        requsitionBuSelect.click();
        requsition.sendKeys(reqNum);
        search.click();
        String s1=requisitionStatus.getText();
        System.out.println(s1);
        if (s1.equalsIgnoreCase("Approved")) {
            // Code to execute if condition is true
            System.out.println("Requisition is Approved");
        } else {

            // Code to execute if condition is false
            while(!s1.equalsIgnoreCase("Approved"))
            {
                expand.click();
                search.click();
                System.out.println("refreshing the search");
                Thread.sleep(1000);
                s1=requisitionStatus.getText();
            }
        }

    }
}
