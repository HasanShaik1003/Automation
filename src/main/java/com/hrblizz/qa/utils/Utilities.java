package com.hrblizz.qa.utils;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

public class Utilities {
    public static final int IMPLICIT_WAIT_TIME=10;
    public static final int PAGE_LOAD_TIME=5;
    static WebDriverWait waitref;


    public static String generateEmailWithTimeStamp() {

        Date date = new Date();
        String timestamp = date.toString().replace(" ","_").replace(":","_");
        return "hasan"+timestamp+"@gmail.com";

    }


    public static Object[][] getTestDataFromExcel(String sheetName) {
        String filePath = System.getProperty("user.dir") +
                "/src/main/java/com/hrblizz/qa/testdata/HRBlizzTestData.xlsx";

        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet '" + sheetName + "' not found in Excel file: " + filePath);
            }

            int rows = sheet.getLastRowNum(); // excludes header
            int cols = sheet.getRow(0).getLastCellNum();

            Object[][] data = new Object[rows][cols];
            DataFormatter formatter = new DataFormatter(); // handles all cell types

            for (int i = 0; i < rows; i++) {
                XSSFRow row = sheet.getRow(i + 1); // skip header
                for (int j = 0; j < cols; j++) {
                    XSSFCell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    data[i][j] = formatter.formatCellValue(cell); // safely converts to String
                }
            }

            return data;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to read Excel data: " + e.getMessage());
        }
    }

    public static String captureScreenshot(WebDriver driver, String testName) {

        File srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String destinationScreenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";

        try {
            FileHandler.copy(srcScreenshot,new File(destinationScreenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return destinationScreenshotPath;
    }

}
