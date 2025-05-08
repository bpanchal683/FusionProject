package Fusion.tests;

import Fusion.TestComponent.BaseTest;
import Fusion.pageobjects.*;
import org.testng.annotations.Test;

import static Fusion.AbstractComponents.AbstractComponent.*;

public class MSD extends BaseTest {

    @Test
    public void msd() throws InterruptedException {
        String caseTitle="Belt Service";
        String customer="Alpine Ski House";
        String contact="Cacilia Viera";
        String product="Radius Belt";
        String incidentType="Replace Peripheral Part";
        String functionalLocation="NW Main Building";
        String promisedTimeFrom=getCurrentDateYearFormatted();
        String promisedTimeTo=getNextDayYearFormatted();
        String requirement="Allison Dickson";
        String startTime=getFixedTime730AM();
        String endTime=getFixedTime1030AM();
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
        fieldServicePage.clickSave();
        fieldServicePage.clickConvertToWorkOrder();
        fieldServicePage.clickOk();
        WorkOrderNumberPage workOrderNumberPage=new WorkOrderNumberPage(driver);
        String workOrderNum=workOrderNumberPage.getWorkOrderNumber();
        workOrderNumberPage.clickSettings();
        workOrderNumberPage.EnterPromisedTimeDetails(promisedTimeFrom,promisedTimeTo);
        workOrderNumberPage.clickSaveAndClose();
        //Schedule board
        fieldServicePage.clickScheduleBoard();
        fieldServicePage.clickContinueAnyway();
        ScheduleBoardPage scheduleBoardPage=new ScheduleBoardPage(driver);
        scheduleBoardPage.clickBook();
        scheduleBoardPage.enterCreateBookingDeatails(workOrderNum,requirement,startTime,endTime);

        //Resolver case
    }
}
