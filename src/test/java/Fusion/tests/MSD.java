package Fusion.tests;

import Fusion.TestComponent.BaseTest;
import Fusion.pageobjects.*;
import org.testng.annotations.Test;

public class MSD extends BaseTest {

    @Test
    public void msd() throws InterruptedException {
        String caseTitle="Belt Service";
        String customer="Alpine Ski House";
        String contact="Cacilia Viera";
        String product="Radius Belt";
        String incidentType="";
        String functionalLocation="";
        //Create New Case
        MsdSignInPage msdSignInPage=new MsdSignInPage(driver);
        msdSignInPage.enterMail("AlexW@w53j6.onmicrosoft.com");
        msdSignInPage.enterPassword("Poha@68102");
        msdSignInPage.staySignIn();
        AppsPage appsPage=new AppsPage(driver);
        appsPage.clickFieldService();
        FieldServicesPage fieldServicesPage=new FieldServicesPage(driver);
        fieldServicesPage.closeTab();
        fieldServicesPage.clickCases();
        fieldServicesPage.createNewCase();
        CaseDetailsPage caseDetailsPage=new CaseDetailsPage(driver);
        caseDetailsPage.addCaseDetails(caseTitle,customer,contact,product);
        caseDetailsPage.clickSave();
        String caseId=caseDetailsPage.getCaseId();
        caseDetailsPage.clickFieldService();
        FieldServicePage fieldServicePage=new FieldServicePage(driver);
        fieldServicePage.addFieldServiceDetails(incidentType,functionalLocation);
        //Schedule board

        //Resolver case
    }
}
