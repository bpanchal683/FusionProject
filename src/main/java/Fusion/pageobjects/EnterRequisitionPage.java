package Fusion.pageobjects;

import Fusion.AbstractComponents.AbstractComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EnterRequisitionPage extends AbstractComponent {

    WebDriver driver;

    public EnterRequisitionPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[contains(@id,'itemNumberId::content')]")
    WebElement itemName;
    @FindBy(xpath = "//*[contains(@id,'itemNumberId::content')]")
    WebElement itemNameKey;
    @FindBy(xpath = "//*[contains(@id,'ItemI1:0:quantity::content')]")
    WebElement quantity;
    @FindBy(xpath = "//*[contains(@id,'currUnitPriceItemRN::content')]")
    WebElement price;
    @FindBy(xpath = "//*[contains(@id,'Deliv1:0:inputDate1::content')]")
    WebElement deliveryDate;
    @FindBy(xpath = "//*[contains(@id,'Deliv1:0:deliverToLocationId::content')]")
    WebElement deliveryLocation;
    @FindBy(xpath = "//*[contains(@id,'Deliv1:0:deliverToLocationId::su0')]")
    WebElement selectLoc;
    @FindBy(xpath = "//*[contains(@id,'_FOTsr1:1:pt1:AP1:ctb2')]")
    WebElement addToCart;
    @FindBy(xpath = "//*[contains(@id,'FOTsr1:1:pt1:AP1:s6:cl2')]")
    WebElement cart;
    @FindBy(xpath = "//*[contains(@id,'FOTsr1:1:pt1:AP1:s6:cb2')]")
    WebElement reviewCart;

    By deloc=By.xpath("//*[contains(@id,'Deliv1:0:deliverToLocationId::su0')]");
    By itemNameWait=By.xpath("//*[contains(@id,'itemNumberId::content')]");
    By deliveryLocWait=By.xpath("//*[contains(@id,'Deliv1:0:deliverToLocationId::content')]");
    By quantityLoc=By.xpath("//*[contains(@id,'ItemI1:0:quantity::content')]");
    By priceLoc=By.xpath("//*[contains(@id,'currUnitPriceItemRN::content')]");
    By deliverDateLoc=By.xpath("//*[contains(@id,'Deliv1:0:inputDate1::content')]");
    By addToCartLoc=By.xpath("//*[contains(@id,'_FOTsr1:1:pt1:AP1:ctb2')]");
    By cartLoc=By.xpath("//*[contains(@id,'FOTsr1:1:pt1:AP1:s6:cl2')]");
    By reviewCartLoc=By.xpath("//*[contains(@id,'FOTsr1:1:pt1:AP1:s6:cb2')]");

    public EditRequisitionPage addItemsToCart(String item, String qty, String prc, String delDate, String delLoc) throws InterruptedException {
        waitForFieldToBeReady(itemNameWait);
        //itemName.sendKeys(item);
        sendKeysToElement(itemNameWait,item);
        itemNameKey.sendKeys(Keys.TAB);
        quantity.clear();
        //quantity.sendKeys(qty);
        sendKeysToElement(quantityLoc,qty);
        //price.sendKeys(prc);
        sendKeysToElement(priceLoc,prc);
        Thread.sleep(2000);
        deliveryDate.clear();
        //deliveryDate.sendKeys(delDate);
        sendKeysToElement(deliverDateLoc,delDate);
        Thread.sleep(3000);
        deliveryLocation.clear();
        //deliveryLocation.sendKeys(delLoc);
//        sendKeysToElement(deliveryLocWait,delLoc);
//        Thread.sleep(2000);
//        jsClick(deliveryLocWait);
        //addToCart.click();
        //scrollIntoView(addToCartLoc);

        Thread.sleep(3000);
        clickElement(addToCartLoc);
        //jsClick(addToCartLoc);
        //cart.click();
        Thread.sleep(3000);
        clickElement(cartLoc);
        //jsClick(cartLoc);
        //reviewCart.click();
        Thread.sleep(5000);
        clickElement(reviewCartLoc);
        //jsClick(reviewCartLoc);
        EditRequisitionPage editRequisitionPage=new EditRequisitionPage(driver);
        return editRequisitionPage;
    }

}
