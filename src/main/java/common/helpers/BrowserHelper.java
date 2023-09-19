package main.java.common.helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import main.java.common.Constant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class BrowserHelper {
    public static void navigateToUrl(String url) {
        Constant.driver.get(url);
    }

    public static WebDriver openBrowser(DriverType driverType) {
        switch (driverType) {

            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--start-maximized");
                Constant.driver = new FirefoxDriver(firefoxOptions);
                Constant.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                break;

            case CHROME:
                WebDriverManager.chromedriver().setup();

                Constant.driver = new ChromeDriver();
                break;

            case EDGE:
                WebDriverManager.iedriver().setup();
                Constant.driver = new EdgeDriver();
                break;

            default:
                System.out.println("Not found browser");
                break;
        }
        return Constant.driver;
    }

    public static void quitBrowser() {
        if (Constant.driver != null) {
            Constant.driver.quit();
            Constant.driver = null;
        }
    }

    public static WebDriver getDriver() {
        return Constant.driver;
    }

    public enum DriverType {CHROME, FIREFOX, EDGE}
}
