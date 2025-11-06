package com.hrblizz.qa.pages;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;


public class Sample {
    public static void main(String[] args) {
        String excelPath = System.getProperty("user.dir") + "/src/main/java/com/hrblizz/qa/testdata/sample.xlsx";

        try (FileInputStream fis = new FileInputStream(new File(excelPath));
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet("Login");

            // Iterate through rows and cells
            for (Row row : sheet) {
                for (Cell cell : row) {
                    // Print each cell value based on its type
                    switch (cell.getCellType()) {
                        case STRING:
                            System.out.print(cell.getStringCellValue() + "\t");
                            break;
                        case NUMERIC:
                            System.out.print((int) cell.getNumericCellValue() + "\t");
                            break;
                        case BOOLEAN:
                            System.out.print(cell.getBooleanCellValue() + "\t");
                            break;
                        default:
                            System.out.print(" \t");
                            break;
                    }
                }
                System.out.println(); // New line after each row
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
