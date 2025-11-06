package com.hrblizz.qa.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Month;


public class CalendarUtils {

    // this is only clicking one next button --> issue with this code
    public static WebElement selectDate(WebDriver driver, String year, String month, String day) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));

        // Wait for calendar container to be visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='calendar']")));

        while (true) {
            // Get current displayed month & year (Example: "November 2025")
            WebElement monthYearElement = driver.findElement(By.xpath("//div[@class='date-picker__header__content']"));
            String displayedMonthYear = monthYearElement.getText().trim();
            String[] parts = displayedMonthYear.split(" ");
            String displayedMonth = parts[0];
            String displayedYear = parts[1];

            // Stop if it matches
            if (displayedMonth.equalsIgnoreCase(month) && displayedYear.equals(year)) {
                break;
            }

            // Convert to numeric for comparison
            int displayedMonthNum = Month.valueOf(displayedMonth.toUpperCase()).getValue();
            int targetMonthNum = Month.valueOf(month.toUpperCase()).getValue();
            int displayedYearNum = Integer.parseInt(displayedYear);
            int targetYearNum = Integer.parseInt(year);

            // Click next or previous based on logic
            if (displayedYearNum > targetYearNum ||
                    (displayedYearNum == targetYearNum && displayedMonthNum > targetMonthNum)) {
                driver.findElement(By.xpath("//div[@class='date-picker__header__arrow-wrapper left']")).click();
            } else {
                driver.findElement(By.xpath("//div[@class='date-picker__header__arrow-wrapper right']")).click();
            }

            // Wait for the calendar header to update
            wait.until(ExpectedConditions.textToBePresentInElementLocated(
                    By.xpath("//div[contains(@class,'calendar')]//div[contains(text(), '20')]"), month));
        }

        // Select the given day
        String dayXpath = "//div[contains(@class,'day-cell') and contains(text()," + day + ")]";
        WebElement dateElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dayXpath)));
        return dateElement;
    }


    public static WebElement selectDateLatest(WebDriver driver, String targetYear, String targetMonth, String targetDay) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Wait until calendar is visible
        WebElement calendar = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='calendar']")));

        while (true) {
            // Read current displayed month and year
            WebElement header = calendar.findElement(By.xpath("//div[@class='date-picker__header__content']"));
            String[] headerParts = header.getText().trim().split(" ");
            String displayedMonth = headerParts[0];
            String displayedYear = headerParts[1];

            if (displayedMonth.equalsIgnoreCase(targetMonth) && displayedYear.equals(targetYear)) {
                break; // Reached correct month-year
            }

            int displayedMonthNum = Month.valueOf(displayedMonth.toUpperCase()).getValue();
            int targetMonthNum = Month.valueOf(targetMonth.toUpperCase()).getValue();
            int displayedYearNum = Integer.parseInt(displayedYear);
            int targetYearNum = Integer.parseInt(targetYear);

            // Decide direction (next or prev)
            boolean goNext = (targetYearNum > displayedYearNum)
                    || (targetYearNum == displayedYearNum && targetMonthNum > displayedMonthNum);

            // Click the correct arrow
            By arrowLocator = goNext
                    ? By.xpath("//div[@class='date-picker__header__arrow-wrapper right']")
                    : By.xpath("//div[@class='date-picker__header__arrow-wrapper left']");
            driver.findElement(arrowLocator).click();

            // Wait until header text changes
            wait.until(ExpectedConditions.not(
                    ExpectedConditions.textToBePresentInElement(header, displayedMonth)));
        }

        // Select the target day
        //String dayXpath = "//div[contains(@class,'calendar')]//td[normalize-space()='" + targetDay + "']";
        //String dayXpath = "//div[contains(@class,'day-cell') and contains(text(),'" + targetDay + "')]"; picking previous month matches
        // Working one //div[contains(@class,'calendar')]//div[contains(@class,'current__month') and normalize-space()='3']
        String dayXpath = "//div[contains(@class,'calendar')]//div[contains(@class,'current__month') and normalize-space()='" + targetDay + "']";
        WebElement dateElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dayXpath)));
        return dateElement;
    }
}
