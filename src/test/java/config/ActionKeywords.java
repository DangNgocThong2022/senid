package test.java.config;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static test.java.executionEngine.DriverScript.OR;

import main.java.common.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;

import test.java.executionEngine.DriverScript;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.java.utils.Log;
import main.java.common.helpers.BrowserHelper;
import main.java.common.Constant;

public class ActionKeywords {

    //public static WebDriver driver;

    public static void openBrowser(String object,String data){
        Log.info("Opening Browser");
        try{
            if(data.equals("firefox")){
                Constant.driver=BrowserHelper.openBrowser(BrowserHelper.DriverType.FIREFOX);
                Log.info("Firefox browser started");
            }
            else if(data.equals("IE")){
                //Dummy Code, Implement you own code
                Constant.driver=new InternetExplorerDriver();
                Log.info("IE browser started");
            }
            else if(data.equals("chrome")){
                //Dummy Code, Implement you own code
                Constant.driver=BrowserHelper.openBrowser(BrowserHelper.DriverType.CHROME);
                Log.info("Chrome browser started");
            }
            else if(data.equals("edge")){
                //Dummy Code, Implement you own code
                Constant.driver=BrowserHelper.openBrowser(BrowserHelper.DriverType.EDGE);
                Log.info("Chrome browser started");
            }
        }catch (Exception e){
            Log.info("Not able to open the Browser --- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }

    public static void navigate(String object, String data){
        try{
            Log.info("Navigating to URL");
            Constant.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            Constant.driver.get(Constant.url);
        }catch(Exception e){
            Log.info("Not able to navigate --- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }

    public static void click(String object, String data){
        try{
            Log.info("Clicking on Web element "+ object);
            Constant.driver.findElement(By.xpath(OR.getProperty(object))).click();
        }catch(Exception e){
            Log.error("Not able to click --- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }
    public static void hoverMouse(String object, String data){
        try{
            Log.info("Hovering on Web element "+ object);
            Actions actions = new Actions(Constant.driver);
            actions.moveToElement(Constant.driver.findElement(By.xpath(OR.getProperty(object)))).perform();
        }catch(Exception e){
            Log.error("Not able to hover mouse--- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }

    public static void input(String object, String data){
        try{
            Log.info("Entering the text in " + object);
            Constant.driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
        }catch(Exception e){
            Log.error("Not able to Enter text --- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }
    public static void clearText(String object, String data){
        try{
            Log.info("Clearing the text in " + object);
            Constant.driver.findElement(By.xpath(OR.getProperty(object))).clear();
        }catch(Exception e){
            Log.error("Not able to clear text --- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }

    public void waitForControlVisible(String object, String data) {
        try{
            Constant.wait = new WebDriverWait(Constant.driver, Duration.ofSeconds(Long.parseLong(data)));
            Constant.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
            Log.info("Wait for element visible");
        }catch(Exception e){
            Log.error("Not able to Wait --- " + e.getMessage());
            DriverScript.bResult = false;
        }

    }
    public void waitForControlInvisible(String object, String data) {
        try{
            Constant.wait = new WebDriverWait(Constant.driver, Duration.ofSeconds(Long.parseLong(data)));
            Constant.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(OR.getProperty(object))));
            Log.info("Wait for element invisible ");
        }catch(Exception e){
            Log.error("Not able to Wait --- " + e.getMessage());
            DriverScript.bResult = false;
        }

    }
    public static void waitFor(String object, String data) throws Exception{
        try{
            Log.info("Wait for 5 seconds");
            Thread.sleep(Long.parseLong(data));
        }catch(Exception e){
            Log.error("Not able to Wait --- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }

    public static void assertData(String object, String data) throws Exception{
        String getText = (String) ((JavascriptExecutor) Constant.driver).executeScript("return arguments[0].value;", Constant.driver.findElement(By.xpath(OR.getProperty(object))));
        try{
            Log.info("Asserting.....");
            if(getText.equals(data)){
                Log.info("Assert matched.....");
                DriverScript.bResult = true;
            }
        }catch(Exception e){
            Log.error("Assert fail");
            Log.error("Actual result:" + getText);
            Log.error("Expected result:" + data);
            DriverScript.bResult = false;
        }
    }

    public static void closeBrowser(String object, String data){
        try{
            Log.info("Closing the browser");
            Constant.driver.quit();
        }catch(Exception e){
            Log.error("Not able to Close the Browser --- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }

}