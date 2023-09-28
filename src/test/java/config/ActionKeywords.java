package test.java.config;

import main.java.common.Constant;
import main.java.common.helpers.BrowserHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import test.java.executionEngine.DriverScript;
import test.java.utils.Log;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;
import java.util.Objects;

import static test.java.executionEngine.DriverScript.OR;

public class ActionKeywords {


    public static void openBrowser(String object, String data) {
        Log.info("Opening Browser");
        try {
            if (data.equals("firefox")) {
                Constant.driver = BrowserHelper.openBrowser(BrowserHelper.DriverType.FIREFOX);
                Log.info("Firefox browser started");
            } else if (data.equals("IE")) {
                //Dummy Code, Implement you own code
                Constant.driver = new InternetExplorerDriver();
                Log.info("IE browser started");
            } else if (data.equals("chrome")) {
                //Dummy Code, Implement you own code
                Constant.driver = BrowserHelper.openBrowser(BrowserHelper.DriverType.CHROME);
                Log.info("Chrome browser started");
            } else if (data.equals("edge")) {
                //Dummy Code, Implement you own code
                Constant.driver = BrowserHelper.openBrowser(BrowserHelper.DriverType.EDGE);
                Log.info("Chrome browser started");
            }
        } catch (Exception e) {
            Log.info("Not able to open the Browser --- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }

    public static void navigate(String object, String data) {
        try {
            Log.info("Navigating to URL");
            Constant.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            Constant.driver.get(data);
        } catch (Exception e) {
            Log.info("Not able to navigate --- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }
    public static void setWindowsSize(String object, String data) {
        try {
            Log.info("Setting Windows size: "+data );
            String[] result = data.split(",");
            String height= result[0];
            String width= result[1];
            Dimension newDimension = new Dimension(Integer.valueOf(height), Integer.valueOf(width));
            Constant.driver.manage().window().setSize(newDimension);
        } catch (Exception e) {
            Log.info("Not able to set windows size --- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }

    public static void click(String object, String data) {
        try {
            Log.info("Clicking on Web element " + object);
            Constant.driver.findElement(By.xpath(OR.getProperty(object))).click();
        } catch (Exception e) {
            Log.error("Not able to click --- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }
    public static void selectValueFromDropDown(String object, String data) {
        try {
            Log.info("Selecting on Web element " + object +" Select value: "+ data);
            Constant.select = new Select(Constant.driver.findElement(By.xpath(OR.getProperty(object))));
            Constant.select.selectByValue(data);
        } catch (Exception e) {
            Log.error("Not able to select --- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }
    public static void selectIndexFromDropDown(String object, int data) {
        try {
            Log.info("Selecting on Web element " + object +" Select value: "+ data);
            Constant.select = new Select(Constant.driver.findElement(By.xpath(OR.getProperty(object))));
            Constant.select.selectByIndex(data);
        } catch (Exception e) {
            Log.error("Not able to select --- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }
    public static void scrollToElement(String object, String data) {
        try {
            Log.info("Scrolling to element " + object);
            Constant.jse.executeScript("arguments[0].scrollIntoView();", Constant.driver.findElement(By.xpath(OR.getProperty(object))));
        } catch (Exception e) {
            Log.error("Not able to scroll --- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }

    public static void hoverMouse(String object, String data) {
        try {
            Log.info("Hovering on Web element " + object);
            Actions actions = new Actions(Constant.driver);
            actions.moveToElement(Constant.driver.findElement(By.xpath(OR.getProperty(object)))).perform();
        } catch (Exception e) {
            Log.error("Not able to hover mouse--- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }
    public static void clickByAction(String object, String data) {
        try {
            Log.info("clicking on Web element " + object);
            Actions actions = new Actions(Constant.driver);
            actions.moveToElement(Constant.driver.findElement(By.xpath(OR.getProperty(object)))).click();
        } catch (Exception e) {
            Log.error("Not able to hover mouse--- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }
    public static void sendkeyByAction(String object, String data) {
        try {
            Log.info("clicking on Web element " + object);
            Actions actions = new Actions(Constant.driver);
            actions.sendKeys(Constant.driver.findElement(By.xpath(OR.getProperty(object))),data).build().perform();
        } catch (Exception e) {
            Log.error("Not able to hover mouse--- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }
    public static void clickByJavascript(String object, String data) {
        try {
            Log.info("clicking on Web element " + object);
            Constant.jse.executeScript("document.getElementByXpath(OR.getProperty(object)).click();");
        } catch (Exception e) {
            Log.error("Not able to hover mouse--- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }
    public static void scroll(String object, String data) {
        try {
            Log.info("Scrolling to: " + data);
            Constant.jse = (JavascriptExecutor) Constant.driver;
            Constant.jse.executeScript("window.scrollTo("+data+")");
        } catch (Exception e) {
            Log.error("Not able to scroll page--- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }
    public static void dragAndDrop(String from, String to) {
        try {
            Log.info("Drag and drop to value: " + to);
            WebElement fromObject=Constant.driver.findElement(By.xpath(OR.getProperty(from)));
            WebElement toObject=Constant.driver.findElement(By.xpath(OR.getProperty(to)));
            Constant.actions=new Actions(Constant.driver);
            Constant.actions.dragAndDrop(fromObject,toObject).perform();
        } catch (Exception e) {
            Log.error("Not able to Drag and Drop item--- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }
    public static void input(String object, String data) {
        try {
            Log.info("Entering the text in " + object);
            Constant.driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
        } catch (Exception e) {
            Log.error("Not able to Enter text --- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }
    public static void sendKey(String object,String data) {
            try {
                switch (data) {
                    case "ENTER":
                        Log.info("Sending key: " + data + "to " + object);
                        Constant.driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(Keys.ENTER);
                        break;
                    case "BACK_SPACE":
                        Log.info("Sending key: " + data + "to " + object);
                        Constant.driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(Keys.BACK_SPACE);
                        break;
                    case "TAB":
                        Log.info("Sending key: " + data + "to " + object);
                        Constant.driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(Keys.TAB);
                        break;
                    case "PAGE_DOWN":
                        Log.info("Sending key: " + data + "to " + object);
                        Constant.driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(Keys.PAGE_DOWN);
                        break;
                    case "ARROW_RIGHT":
                        Log.info("Sending key: " + data + "to " + object);
                        Constant.driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(Keys.ARROW_RIGHT);
                        break;
                }
            }
                catch (Exception e) {
                    Log.error("Not able to send key "+data+ "--- " + e.getMessage());
                    DriverScript.bResult = false;
                }
    }

    public static void clearText(String object, String data) {
        try {
            Log.info("Clearing the text in " + object);
            Constant.driver.findElement(By.xpath(OR.getProperty(object))).clear();
        } catch (Exception e) {
            Log.error("Not able to clear text --- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }

    public static void waitFor(String data) throws Exception {
        try {
            Log.info("Wait for: "+ data+"second");
            Thread.sleep(Long.parseLong(data));
        } catch (Exception e) {
            Log.error("Not able to Wait --- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }

    public static void assertData(String object, String expected) throws Exception {
        try {
            String getText = Constant.driver.findElement(By.xpath(OR.getProperty(object))).getText();
            boolean compare = Objects.equals(getText, expected);
            if (compare) {
                Log.info("Assert matched.....");
                DriverScript.bResult = true;
            } else if (!(compare)) {
                Log.error("Assert fail");
                Log.info("Actual result: " + getText);
                Log.info("Expected result: " + expected);
                DriverScript.bResult = false;
            }
        }catch(Exception e){
                DriverScript.bResult = false;
            }
    }

    public static void closeBrowser(String object, String data) {
        try {
            Log.info("Closing the browser");
            Constant.driver.quit();
        } catch (Exception e) {
            Log.error("Not able to Close the Browser --- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }

    public void waitForControlVisible(String object, String data) {
        try {
            Constant.wait = new WebDriverWait(Constant.driver, Duration.ofSeconds(Long.parseLong(data)));
            Constant.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(object))));
            Log.info("Wait for element visible");
        } catch (Exception e) {
            Log.error("Not able to Wait --- " + e.getMessage());
            DriverScript.bResult = false;
        }

    }

    public void waitForControlInvisible(String object, String data) {
        try {
            Constant.wait = new WebDriverWait(Constant.driver, Duration.ofSeconds(Long.parseLong(data)));
            Constant.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(OR.getProperty(object))));
            Log.info("Wait for element invisible ");
        } catch (Exception e) {
            Log.error("Not able to Wait --- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }

}