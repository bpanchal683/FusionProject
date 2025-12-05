package Fusion.pageobjects;

import  org.openqa.selenium.By ;
import org.openqa.selenium.WebDriver;

public class ServiceNowPage {
    private WebDriver driver;

    public ServiceNowPage(WebDriver driver) {
        this.driver = driver;
    }

    private By username =  By.id ("user_name");
    private By password =  By.id ("user_password");
    private By loginBtn =  By.id ("sysverb_login");
    private By favourites = By.xpath("//span[text()='Favorites']");
    private By users = By.xpath("//span[text()='Users']");
    private By search = By.xpath("//input[@placeholder='Search']");

    public void login(String user, String pass) {
        driver.findElement(username).sendKeys(user);
        driver.findElement(password).sendKeys(pass);
        driver.findElement(loginBtn).click();
    }

    public void openUsers() {
        driver.findElement(favourites).click();
        driver.findElement(users).click();
    }

    public void searchUser(String email) {
        driver.findElement(search).sendKeys(email);
    }
}

