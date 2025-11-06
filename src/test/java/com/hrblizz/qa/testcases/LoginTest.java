package com.hrblizz.qa.testcases;

import com.hrblizz.qa.base.Base;
import com.hrblizz.qa.pages.LoginPage;
import com.hrblizz.qa.pages.ManagerHomePage;
import com.hrblizz.qa.utils.Utilities;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends Base {

    LoginPage loginPage;
    String checkUser;

    public LoginTest() {
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

    @Test(priority = 1, dataProvider = "validCredentialsSupplier")
    public void verifyLoginWithValidCredentials(String email, String password) {

        ManagerHomePage homePage = loginPage.login(email, password);
        System.out.println(homePage.retrieveProfileName());
        Assert.assertTrue(homePage.retrieveProfileName().contains(dataProp.getProperty("managerProfileName")), "Profile name not matching with the required user name");
    }

    @DataProvider(name = "validCredentialsSupplier")
    public Object[][] supplyTestData() {

        Object[][] data = Utilities.getTestDataFromExcel("Login");
        return data;
    }

    @Test(priority = 2)
    public void verifyLoginWithValidCredentialsFromProPFile() throws InterruptedException {

        ManagerHomePage homepage = loginPage.login(prop.getProperty("validEmailManager"), prop.getProperty("validPasswordManager"));
        Thread.sleep(10);
        System.out.println("Login Successfully completed");
        Assert.assertTrue(homepage.retrieveProfileName().contains(dataProp.getProperty("managerProfileName")), "Profile name not matching with the required user name");

    }

    @Test(priority = 3)
    public void verifyLoginWithInvalidCredentials() {
        loginPage.login(Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("invalidPassword"));
        Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")), "Expected Warning message is not displayed");

    }

    @Test(priority = 4)
    public void verifyLoginWithInvalidEmailAndValidPassword() {

        loginPage.login(Utilities.generateEmailWithTimeStamp(), prop.getProperty("validEmailManager"));
        Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")), "Expected Warning message is not displayed");

    }

    @Test(priority = 5)
    public void verifyLoginWithValidEmailAndInvalidPassword() {

        loginPage.login(prop.getProperty("validEmailManager"), dataProp.getProperty("invalidPassword"));
        Assert.assertTrue(loginPage.retrieveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")), "Expected Warning message is not displayed");

    }

    @Test(priority = 6)
    public void verifyLoginWithoutProvidingCredentials() {

        loginPage.clickOnLoginButton();
        Assert.assertTrue(loginPage.retrieveEmailPasswordNotProvidingWarningMessageText().contains(dataProp.getProperty("withoutProvidingCredentailsWarning")), "Expected Warning message is not displayed");

    }

}
