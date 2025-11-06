package com.hrblizz.qa.testcases;

import com.hrblizz.qa.base.Base;
import com.hrblizz.qa.pages.LoginPage;
import com.hrblizz.qa.pages.ManagerHomePage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class ManagerHomeTest extends Base {

    LoginPage loginPage;
    String checkUser;

    public ManagerHomeTest() {
        super();
    }

    public WebDriver driver;

    @BeforeMethod
    public void setup() {

        driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
        ManagerHomePage homePage = new ManagerHomePage(driver);
        loginPage = homePage.naviageToLoginPage();

    }

    @AfterMethod
    public void tearDown() {

        driver.quit();

    }

    @Test(priority = 1)
    public void verifyAllTabsPresetOnManagerHomePage() throws InterruptedException {

        ManagerHomePage homepage = loginPage.login(prop.getProperty("validEmailManager"), prop.getProperty("validPasswordManager"));
        System.out.println("Login Successfully completed");
        Assert.assertTrue(homepage.retrieveProfileName().contains(dataProp.getProperty("managerProfileName")), "Profile name not matching with the required user name");
        homepage.clickOnQuickAccessLeaves();
        Thread.sleep(5000);
        homepage.clickOnMainLeaves();
        Thread.sleep(5000);
        homepage.clickOnCalender();
        Thread.sleep(5000);
        homepage.clickOnReviewerApprovals();
//        homepage.clickOnMainLeaves();
        homepage.clickOnManagerLeaves();
        Thread.sleep(5000);
        homepage.clickOnManagerCalendar();
        Thread.sleep(5000);
        homepage.clickOnManagerReports();
        Thread.sleep(5000);
        homepage.clickOnManagerBalances();
        Thread.sleep(5000);
        homepage.clickOnRequestNewLeave();

    }

    @Test(priority = 2)
    public void requestNewLeave() throws InterruptedException {

        ManagerHomePage homepage = loginPage.login(prop.getProperty("validEmailManager"), prop.getProperty("validPasswordManager"));
        System.out.println("Login Successfully completed");
        Assert.assertTrue(homepage.retrieveProfileName().contains(dataProp.getProperty("managerProfileName")), "Profile name not matching with the required user name");
        homepage.clickOnQuickAccessLeaves();
        Thread.sleep(5000);
        homepage.clickOnRequestNewLeaveUsingQuickAccess();
        homepage.selectLeaveTypeAnnual();
        //homepage.selectLeaveTypeSick();
        Thread.sleep(5000);
        homepage.selectDate("2026", "July", "28");
        Thread.sleep(5000);
        homepage.enterLeaveNotesAnnualLeave();
        Thread.sleep(5000);
        homepage.clickOnLeaveRequestButton();
        Thread.sleep(5000);
        homepage.clickOnConfirmButton();
        Thread.sleep(5000);
        System.out.println(homepage.retrieveLeaveRequestModifyCancelAutoSuccessMessage());
        Assert.assertTrue(homepage.retrieveLeaveRequestModifyCancelAutoSuccessMessage().contains(dataProp.getProperty("leaveRequestModifyCancelAutoSuccessMessage")), "Result message is different from expected message");


    }
}
