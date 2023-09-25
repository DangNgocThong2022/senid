package main.java.common;

import java.awt.*;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.sikuli.api.Screen;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Constant {
    public static JavascriptExecutor jse;
    public static Robot robot;
    public static Select select;
    public static Screen screen;
    public static Action action;
    public static Actions actions;
    public static Date date;
    public static Random random;
    public static WebDriverWait wait;
    public static Alert alert;
    public static WebDriver driver;
    public static WebElement element;
    public static List<WebElement> list;
    public static Object object;
    public static String pathFileImport = new File("file-import/").getAbsolutePath();
    public static String pathDownloadFile = new File("file-download").getAbsolutePath();
    //public static String pathDownloadFile = "D:\\Driver\\chromedriver.exe";
    public static String pathDataDictionaryFile = "data/data-dictionary/DataDictionary.xlsx";

    public static final String Path_TestData = "src/test/java/dataEngine/DataEngine.xlsx";
    public static final String Path_OR = "src/test/java/config/OR.txt";
    public static final String File_TestData = "DataEngine.xlsx";
    public static final String KEYWORD_FAIL = "FAIL";
    public static final String KEYWORD_PASS = "PASS";

    //Data Sheet Column Numbers
    public static final int Col_TestCaseID = 0;
    public static final int Col_TestScenarioID =1 ;
    public static final int Col_PageObject =4 ;
    public static final int Col_ActionKeyword =5 ;
    public static final int Col_ExpectedResult =7 ;
    public static final int Col_RunMode =2 ;
    public static final int Col_Result =3 ;
    public static final int Col_DataSet =6 ;
    public static final int Col_TestStepResult =7 ;

    // Data Engine Excel sheets
    public static final String Sheet_TestSteps = "Test Steps";
    public static final String Sheet_TestCases = "Test Cases";
}
