package Utility;

import TestData.DataDrivenTestData;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
@Slf4j
public class TestDataProviders {
    @DataProvider(name = "testScenarioData")
    public Object[][] testData(Method method) throws IOException {
        String methodName = method.getName();
        List<DataDrivenTestData> testDataList = ExcelDataReader.readTestDataFromExcel("src/test/resources/Testdatafolder/Testdata.xlsx");
        List<DataDrivenTestData> methodData = new ArrayList<>();

        // Filter data for the specific test method
        for (DataDrivenTestData data : testDataList) {
            if (data.getTestCases().equalsIgnoreCase(methodName)) {
                methodData.add(data);
            }
        }
        Object[][] data = new Object[methodData.size()][1];

        for (int i = 0; i < methodData.size(); i++) {
         data[i][0] = methodData.get(i);

        }

        return data;
    }
}
