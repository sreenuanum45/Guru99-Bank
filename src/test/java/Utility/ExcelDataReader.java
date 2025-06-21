package Utility;

import TestData.DataDrivenTestData;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.*;

public class ExcelDataReader {
    public static List<DataDrivenTestData> readTestDataFromExcel(String excelFilePath) throws IOException, IOException {
        List<DataDrivenTestData> testDataList = new ArrayList<>();

        FileInputStream fis = new FileInputStream(new File(excelFilePath));
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet
System.out.println(sheet.getPhysicalNumberOfRows());
System.out.println(sheet.getLastRowNum());
        for (int i = 1; i < sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            DataDrivenTestData data = new DataDrivenTestData();

            data.setTestCaseID(String.valueOf(row.getCell(0).getNumericCellValue()));
            data.setTestScenario(row.getCell(1).getStringCellValue());
            data.setTestCases(row.getCell(2).getStringCellValue());
            data.setTestSteps(row.getCell(3).getStringCellValue());
            data.setTestData(row.getCell(4).getStringCellValue());
            data.setExpectedResult(row.getCell(5).getStringCellValue());
            data.setActualResult(row.getCell(6).getStringCellValue());
            data.setStatus(row.getCell(7).getStringCellValue());

            testDataList.add(data);
        }


        workbook.close();
        fis.close();

        return testDataList;
    }
}
