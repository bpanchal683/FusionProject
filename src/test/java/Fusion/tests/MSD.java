package Fusion.tests;

import Fusion.TestComponent.BaseTest;
import Fusion.pageobjects.AppsPage;
import Fusion.pageobjects.FieldServicesPage;
import Fusion.pageobjects.MsdSignInPage;
import org.testng.annotations.Test;

public class MSD extends BaseTest {

    @Test
    public void msd()
    {
        //Create New Case
        MsdSignInPage msdSignInPage=new MsdSignInPage(driver);
        msdSignInPage.enterMail("AlexW@w53j6.onmicrosoft.com");
        msdSignInPage.enterPassword("Poha@68102");
        msdSignInPage.staySignIn();
        AppsPage appsPage=new AppsPage(driver);
        appsPage.clickFieldService();
        FieldServicesPage fieldServicesPage=new FieldServicesPage(driver);
        fieldServicesPage.clickCases();
        //Schedule board

        //Resolver case
    }
}
