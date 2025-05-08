package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FieldServicePage extends AbstractComponent {

    WebDriver driver;

    public FieldServicePage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@aria-label='Incident Type, Lookup']")
    WebElement incidentType;

    By incidentTypeLoc=By.xpath("//input[@aria-label='Incident Type, Lookup']");
    By functionalLocationLoc=By.xpath("//input[@aria-label='Functional Location, Lookup']");

    public void addFieldServiceDetails(String inc,String funcLoc)
    {
        waitForFieldToBeReady(incidentTypeLoc);
        sendKeysToElement(incidentTypeLoc,inc);
        sendKeysToElement(functionalLocationLoc,funcLoc);
    }

}
