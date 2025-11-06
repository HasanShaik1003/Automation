package com.hrblizz.qa.pages;

import com.hrblizz.qa.utils.CalendarUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ManagerHomePage {
    WebDriver driver;

    //Objects

    @FindBy(xpath = "//div[text()='Thomas Howard ']")
    private WebElement userProfileName;

    @FindBy(xpath = "//span[text()='Leaves']")
    private WebElement quickAccessLeaves;

    @FindBy(xpath = "//p[text()='Quick access']")
    private WebElement clickOnQuickAccess;

    @FindBy(xpath = "//p[text()='Leaves']")
    private WebElement mainTabLeaves;

    @FindBy(xpath = "//a[text()='My leaves']") // @FindBy(linkText = "My leaves")
    private WebElement myLeaves;

    @FindBy(xpath = "//a[text()='Calendar']") // @FindBy(linkText = "Calendar")
    private WebElement calender;

    @FindBy(xpath = "//a[text()='Approvals']") // @FindBy(linkText = "Approvals")
    private WebElement reviewerApprovals;

    @FindBy(linkText = "Leaves")
    private WebElement managerLeaves;

    @FindBy(xpath = "(//a[text()='Calendar'])[position()=2]")
    private WebElement managerCalender;

    @FindBy(linkText = "Reports")
    private WebElement managerReports;

    @FindBy(linkText = "Balances")
    private WebElement managerBalances;

    @FindBy(xpath = "//button[@aria-label='Request new leave']")
    private WebElement requestNewLeave;

    @FindBy(xpath = "//div[text()='Select']")
    private WebElement selectLeaveType;

    @FindBy(xpath = "//div[text()='Annual leave'  and contains(@id,'request-type')]")
    private WebElement annualLeave;

    @FindBy(xpath = "//div[text()='Sick leave'  and contains(@id,'request-type')]")
    private WebElement sickLeave;

    @FindBy(xpath = "//textarea[@id='notes']")
    private WebElement leaveNotes;

    @FindBy(xpath = "//span[text()='Request leave']")
    private WebElement requestLeaveButton;

    @FindBy(xpath = "//span[text()='Confirm']")
    private WebElement confirmButton;

    @FindBy(xpath = "//div[text()='Request has been auto approved!']")
    private WebElement leaveRequestModifyCancelAutoSuccessMessage;

    @FindBy(xpath = "//span[text()='Open request']")
    private WebElement openRequest;


    public ManagerHomePage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    //Actions

    public LoginPage naviageToLoginPage() {

        return new LoginPage(driver);

    }

    public String retrieveProfileName() {

        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//div[text()='Thomas Howard ']"))));
        String userProfileNameText = userProfileName.getText();

        return userProfileNameText;

    }

    public void clickOnQuickAccessLeaves() {
        quickAccessLeaves.click();
    }

    public void clickOnQuickAccessTab() {
        clickOnQuickAccess.click();
    }


    public void clickOnMainLeaves() {
        mainTabLeaves.click();
    }


    public void clickOnMyLeaves() {
        myLeaves.click();
    }

    public void clickOnCalender() {
        calender.click();
    }

    public void clickOnReviewerApprovals() {
        reviewerApprovals.click();
    }

    public void clickOnManagerLeaves() {
        managerLeaves.click();
    }

    public void clickOnManagerCalendar() {
        managerCalender.click();
    }

    public void clickOnManagerReports() {
        managerReports.click();
    }

    public void clickOnManagerBalances() {
        managerBalances.click();
    }

    public void clickOnRequestNewLeave() {
        myLeaves.click();
        requestNewLeave.click();

    }

    public void clickOnRequestNewLeaveUsingQuickAccess() {
        requestNewLeave.click();

    }

    public void selectLeaveTypeAnnual() {
        selectLeaveType.click();
        annualLeave.click();
    }

    public void selectLeaveTypeSick() {
        selectLeaveType.click();
        sickLeave.click();
    }

    public void selectDate(String year, String month, String day) {
        WebElement dateSelect = CalendarUtils.selectDateLatest(driver, year, month, day);
        dateSelect.click();
        dateSelect.click();
    }

    public void enterLeaveNotesAnnualLeave() {
        leaveNotes.sendKeys("Requesting annual leave");
    }

    public void enterLeaveNotesSickLeave() {
        leaveNotes.sendKeys("Requesting sick leave");
    }

    public void clickOnLeaveRequestButton() {
        requestLeaveButton.click();
    }

    public void clickOnConfirmButton() {
        confirmButton.click();
    }

    public String retrieveLeaveRequestModifyCancelAutoSuccessMessage() {

        String successMessage = leaveRequestModifyCancelAutoSuccessMessage.getText();
        return successMessage;

    }


}
