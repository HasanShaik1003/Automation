package com.hrblizz.qa.testcases;

import com.hrblizz.qa.base.Base;
import com.hrblizz.qa.pages.EmployeeHomePage;
import com.hrblizz.qa.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EmployeeHomeTest extends Base {

    LoginPage loginPage;
    String checkUser;

    public EmployeeHomeTest() {
        super();
    }

    public WebDriver driver;

    @BeforeMethod
    public void setup() {

        driver = initializeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
        EmployeeHomePage homePageEmployee = new EmployeeHomePage(driver);
        loginPage = homePageEmployee.naviageToLoginPage();

    }

    @AfterMethod
    public void tearDown() {

        driver.quit();

    }

    @Test(priority = 1)
    public void verifyAllTabsPresetOnEmployeeHomePage() throws InterruptedException {

        EmployeeHomePage homePageEmployee = loginPage.loginEmployee(prop.getProperty("validEmailEmployee"), prop.getProperty("validPasswordEmployee"));
        System.out.println("Login Successfully completed");
        Assert.assertTrue(homePageEmployee.retrieveProfileName().contains(dataProp.getProperty("employeeProfileName")), "Profile name not matching with the required user name");
        homePageEmployee.clickOnQuickAccessLeaves();
        Thread.sleep(5000);
        homePageEmployee.clickOnMainLeaves();
        Thread.sleep(5000);
        homePageEmployee.clickOnCalender();
        Thread.sleep(5000);
        homePageEmployee.clickOnRequestNewLeave();
        Thread.sleep(5000);
    }

    @Test(priority = 2)
    public void requestNewLeave() throws InterruptedException {

        EmployeeHomePage homePageEmployee = loginPage.loginEmployee(prop.getProperty("validEmailEmployee"), prop.getProperty("validPasswordEmployee"));
        System.out.println("Login Successfully completed");
        Assert.assertTrue(homePageEmployee.retrieveProfileName().contains(dataProp.getProperty("employeeProfileName")), "Profile name not matching with the required user name");
        homePageEmployee.clickOnQuickAccessLeaves();
        Thread.sleep(5000);
        homePageEmployee.clickOnRequestNewLeaveUsingQuickAccess();
        //homepage.selectLeaveTypeSick();
        Thread.sleep(5000);
        homePageEmployee.selectDate("2026", "July", "28");
        Thread.sleep(5000);
        homePageEmployee.enterLeaveNotesAnnualLeave();
        Thread.sleep(5000);
        homePageEmployee.clickOnLeaveRequestButton();
        Thread.sleep(5000);
        homePageEmployee.clickOnConfirmButton();
        Thread.sleep(5000);
        System.out.println(homePageEmployee.retrieveLeaveRequestSubmitSuccessMessage());
        Assert.assertTrue(homePageEmployee.retrieveLeaveRequestSubmitSuccessMessage().contains(dataProp.getProperty("leaveRequestSubmitSuccessMessage")), "Result message is different from expected message");


    }

}
