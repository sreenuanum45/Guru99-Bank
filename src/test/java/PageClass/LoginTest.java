package PageClass;

import TestData.DataDrivenTestData;
import Utility.TestDataProviders;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.qameta.allure.*;
import org.testng.annotations.Listeners;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.path.json.JsonPath.from;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
@Epic("Login Module")
@Feature("Login Functionality")
public class LoginTest extends Baseclass {
    RemoteWebDriver driver;
    public int scc = 0;
    @BeforeMethod
    public void setup() {
        driver=new ChromeDriver();
        driver.get(OpenFileUtil.url);
        driver.manage().window().maximize();
        test = extent.createTest("Login Test: Invalid Userid and Valid Password");
    }

    @Test(dataProvider = "testScenarioData", dataProviderClass = TestDataProviders.class,  priority = 1)
    @Story("User tries to login with invalid userid and valid password")
    @Description("Verify alert message when invalid userid and valid password are entered.")
    public void EnterInvalidUseridAndValidPassword(DataDrivenTestData data) {
        String Testdata = data.getTestData();
        Map<String, String> dataMap = convertToHashMap(Testdata);

        step("Enter username");
        driver.findElement(By.name("uid")).sendKeys(dataMap.get("UserName"));
        step("Enter password");
        driver.findElement(By.name("password")).sendKeys(dataMap.get("Password"));
        step("Click login");
        driver.findElement(By.name("btnLogin")).click();
        String popname= driver.switchTo().alert().getText();
        test.info("Alert text: " + popname);
        Assert.assertEquals(popname, "User or Password is not valid");
        driver.switchTo().alert().dismiss();
        test.pass("Test completed: Invalid Userid and Valid Password");
    }

    @Test(dataProvider = "testScenarioData", dataProviderClass = TestDataProviders.class, priority = 2)
    public void EnterValidUseridAndInvalidPassword(DataDrivenTestData data) {
        String Testdata = data.getTestData();
        Map<String, String> dataMap = convertToHashMap(Testdata);

        // HashMap<String, Object> tdMap = from(data.getTestData()).get();
        driver.findElement(By.name("uid")).sendKeys(dataMap.get("UserName"));
        driver.findElement(By.name("password")).sendKeys(dataMap.get("Password"));
        driver.findElement(By.name("btnLogin")).click();
        String popname= driver.switchTo().alert().getText();
        Assert.assertEquals(popname, "User or Password is not valid");
        driver.switchTo().alert().dismiss();

    }
    @Test(dataProvider = "testScenarioData", dataProviderClass = TestDataProviders.class, priority = 3)
    public void EnterInvalidUseridAndInvalidPassword(DataDrivenTestData data) {
        String Testdata = data.getTestData();
        Map<String, String> dataMap = convertToHashMap(Testdata);
        driver.manage().window().maximize();
        driver.findElement(By.name("uid")).sendKeys(dataMap.get("UserName"));
        driver.findElement(By.name("password")).sendKeys(dataMap.get("Password"));
        driver.findElement(By.name("btnLogin")).click();

        String popname= driver.switchTo().alert().getText();
        Assert.assertEquals(popname, "User or Password is not valid");
        driver.switchTo().alert().dismiss();

    }

    @Test(dataProvider = "testScenarioData", dataProviderClass = TestDataProviders.class, priority = 4)
    public void UseridAndPasswordIsEmpty(DataDrivenTestData data) {
        String Testdata = data.getTestData();
        Map<String, String> dataMap = convertToHashMap(Testdata);
        driver.findElement(By.name("uid")).sendKeys(dataMap.get("UserName"));
        driver.findElement(By.name("password")).sendKeys(dataMap.get("Password"));
        driver.findElement(By.name("btnLogin")).click();

        String popname= driver.switchTo().alert().getText();
        Assert.assertEquals(popname, "User is not valid");
        driver.switchTo().alert().dismiss();

    }
    @Test(dataProvider = "testScenarioData", dataProviderClass = TestDataProviders.class,  priority = 5)
    public void EnterValidUseridAndPassword(DataDrivenTestData data) throws IOException {
        String Testdata = data.getTestData();
        Map<String, String> dataMap = convertToHashMap(Testdata);

        // HashMap<String, Object> tdMap = from(data.getTestData()).get();
        driver.findElement(By.name("uid")).sendKeys(dataMap.get("UserName"));
        driver.findElement(By.name("password")).sendKeys(dataMap.get("Password"));
        driver.findElement(By.name("btnLogin")).click();


        Assert.assertTrue(driver.findElement(By.xpath("//h2")).isDisplayed());
        Assert.assertTrue(driver.getTitle().contains("HomePage"));

        if(dataMap.get("UserName").startsWith("mngr")){
            Assert.assertTrue(driver.findElement(By.linkText("Manager")).isDisplayed());
        }
        List<WebElement> managermodules=driver.findElements(By.cssSelector(".menusubnav>li"));
        System.out.println("manager modules"+managermodules.size());
//        String idname=driver.findElement(By.xpath("(//tr//td)[7]")).getText();
//        Assert.assertTrue(idname.contains(dataMap.get("UserName")));
//        String[]x=idname.split(":");
//        HashMap<String, Object> map=new HashMap<>();
//        map.put(x[0],x[1]);;
        scc = scc + 1;
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String png = (System.getProperty("user.dir") + "/Screenshotsfolder/Screenshot" + scc + ".png");
        FileUtils.copyFile(scrFile, new File(png));


    }
    public static Map<String, String> convertToHashMap(String input) {
        Map<String, String> resultMap = new HashMap<>();
        Pattern pattern = Pattern.compile("(\\w+)\\s*=\\s*(\\w+)");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            String key = matcher.group(1);
            String value = matcher.group(2);
            resultMap.put(key, value);
        }

        return resultMap;
    }
    @AfterMethod
    public void close() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
    @Step("{stepName}")
    public void step(String stepName) {
        test.info(stepName);
    }
    }
