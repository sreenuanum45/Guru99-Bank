package TestData;


import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.*;


@Slf4j
@DataDrivenTestData.Data(name = "TestData")
public class DataDrivenTestData {
    @Data(name = "TestCaseID")
    private String TestCaseID;

    @Data(name = "TestScenario")
    private String TestScenario;

    @Data(name = "TestCases")
    private String TestCases;

    @Data(name = "TestSteps")
    private String TestSteps;

    @Data(name = "TestData")
    private String TestData;
    @Data(name = "ExpectedResult")
    private String ExpectedResult;
    @Data(name = "ActualResult")
    private String ActualResult;
    @Data(name = "Status")
    private String Status;

    public String getTestCaseID() {
        return TestCaseID;
    }

    public void setTestCaseID(String testCaseID) {
        TestCaseID = String.valueOf(testCaseID);
    }

    public String getTestScenario() {
        return TestScenario;
    }

    public void setTestScenario(String testScenario) {
        TestScenario = testScenario;
    }

    public String getTestCases() {
        return TestCases;
    }

    public void setTestCases(String testCases) {
        TestCases = testCases;
    }

    public String getTestSteps() {
        return TestSteps;
    }

    public void setTestSteps(String testSteps) {
        TestSteps = testSteps;
    }

    public String getTestData() {
        return TestData;
    }

    public void setTestData(String testData) {
        TestData = testData;
    }

    public String getExpectedResult() {
        return ExpectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        ExpectedResult = expectedResult;
    }

    public String getActualResult() {
        return ActualResult;
    }

    public void setActualResult(String actualResult) {
        ActualResult = actualResult;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TestData: iterationName: [");
        sb.append(" TestCaseID: ").append(TestCaseID).append(", ");
        sb.append("TestScenario: ").append(TestScenario).append(", ");
        sb.append("TestCases: ").append(TestCases).append(", ");
        sb.append("TestSteps: ").append(TestSteps).append(", ");
        sb.append("TestData: ").append(TestData).append(", ");
        sb.append("ExpectedResult: ").append(ExpectedResult).append(", ");
        sb.append("ActualResult: ").append(ActualResult).append(", ");
        sb.append("Status: ").append(Status);
        sb.append("]");

        return sb.toString();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.FIELD,ElementType.PARAMETER})
    @Inherited
    public @interface Data {
        String name() default "";
    }
}
