package Fusion.tests;

import Fusion.TestComponent.BaseTest;
import Fusion.TestComponent.ExcelReader;
import Fusion.pageobjects.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static Fusion.AbstractComponents.AbstractComponent.*;

public class MSD extends BaseTest {

    @Test(dataProvider = "MSDData")
    public void msd(Map<String, String> data) throws InterruptedException {
        String caseTitle=data.get("caseTitle");
        String customer=data.get("customer");
        String contact=data.get("contact");
        String product=data.get("product");
        String incidentType=data.get("incidentType");
        String functionalLocation=data.get("functionalLocation");
        String promisedTimeFrom=getCurrentDateYearFormatted();
        String promisedTimeTo=getNextDayYearFormatted();
        String requirement=data.get("requirement");
        String startTime=getFixedTime730AM();
        String endTime=getFixedTime1030AM();
        String bookingStatus=data.get("bookingStatus");
        String title=data.get("title");
        String resolution=data.get("resolution");
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
        scheduleBoardPage.bookOrder();
        scheduleBoardPage.clickWorkOrder();
        ActiveWorkOrdersPage activeWorkOrdersPage=new ActiveWorkOrdersPage(driver);
        activeWorkOrdersPage.clickWorkOrderNumber();
        WorkOrderPage workOrderPage=new WorkOrderPage(driver);
        workOrderPage.clickOrderNumber();
        workOrderPage.changeBookingStatus(bookingStatus);
        workOrderPage.clickSave();
        workOrderPage.clickBack();
        workOrderPage.addTitleAndClose(title);
        fieldServicesPage.clickCases();
        MyActiveCasesPage myActiveCasesPage=new MyActiveCasesPage(driver);
        myActiveCasesPage.filterCaseByKeyword(caseId);
        myActiveCasesPage.clickCaseTitle();
        myActiveCasesPage.enterNoteTitleAndClose(title);
        //Resolver case
        myActiveCasesPage.clickResolveCase();
        myActiveCasesPage.resolveCase(resolution);
        myActiveCasesPage.logOut();
    }

    @DataProvider(name = "MSDData")
    public Iterator<Object[]> getMSDData() throws IOException {
        String filePath = System.getProperty("user.dir") + "/src/test/java/resources/MSD_TestData.xlsx";
        List<Map<String, String>> testData = ExcelReader.getTestData(filePath, "MSD");

        List<Object[]> dataProvider = new ArrayList<>();
        for (Map<String, String> row : testData) {
            dataProvider.add(new Object[]{row});
        }

        return dataProvider.iterator();
    }
}
