package main.java.common.helpers;

import main.java.common.Constant;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

//import static org.openqa.selenium.remote.BrowserType.CHROME;
//import static org.openqa.selenium.remote.BrowserType.FIREFOX;
//import static org.openqa.selenium.remote.BrowserType.EDGE;

public class BrowserHelper {
    public enum DriverType {CHROME, FIREFOX, EDGE}

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
                ChromeOptions Options = new ChromeOptions();
                Options.addArguments("--remote-allow-origins=*");
                //System.setProperty("webdriver.chrome.driver", "d:\\Driver\\chromedriver.exe");

                System.out.println("Khoi tao chrome");
                //String downloadFilepath = Constant.pathDownloadFile;
                //System.out.println("Duong dan download web driver"+ downloadFilepath);
                //HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                //chromePrefs.put("profile.default_content_settings.popups", 0);
                //chromePrefs.put("download.default_directory", downloadFilepath);
                //ChromeOptions chromeOptions = new ChromeOptions();
                //chromeOptions.setExperimentalOption("prefs", chromePrefs);
                //chromeOptions.addArguments("--start-maximized");
                //System.out.println("Set option chrome");
                //Constant.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                Constant.driver = new ChromeDriver(Options);
                //Constant.driver.navigate().to("https://anhtester.com");
                System.out.println("Da khoi dong chrome");
                break;

            case EDGE:
                WebDriverManager.iedriver().setup();
                Constant.driver = new EdgeDriver();
                break;

            default:
                System.out.println("Không tìm thấy browser");
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
}
