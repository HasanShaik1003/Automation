package com.hrblizz.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    //Objects

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//span[text()='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[text()='Invalid username/password']")
    private WebElement emailPasswordNotMatchingWarning;

    @FindBy(xpath = "//span[text()='Please insert a valid email!']")
    private WebElement emailPasswordNotProvidedWarning;


    public LoginPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    //Actions

    public void enterEmailAddress(String emailText) {

        emailField.sendKeys(emailText);

    }

    public void enterPassword(String passwordText) {

        passwordField.sendKeys(passwordText);

    }

    public ManagerHomePage clickOnLoginButton() {

        loginButton.click();
        return new ManagerHomePage(driver);

    }

    public EmployeeHomePage clickOnLoginButtonEmployee() {

        loginButton.click();
        return new EmployeeHomePage(driver);

    }

    public ManagerHomePage login(String emailText, String passwordText) {

        emailField.sendKeys(emailText);
        passwordField.sendKeys(passwordText);
        loginButton.click();
        return new ManagerHomePage(driver);

    }

    public EmployeeHomePage loginEmployee(String emailText, String passwordText) {

        emailField.sendKeys(emailText);
        passwordField.sendKeys(passwordText);
        loginButton.click();
        return new EmployeeHomePage(driver);

    }


    public String retrieveEmailPasswordNotMatchingWarningMessageText() {

        String warningText = emailPasswordNotMatchingWarning.getText();
        return warningText;

    }

    public String retrieveEmailPasswordNotProvidingWarningMessageText() {

        String warningMessage = emailPasswordNotProvidedWarning.getText();
        return warningMessage;

    }
}
