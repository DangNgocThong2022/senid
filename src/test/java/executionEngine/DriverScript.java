package executionEngine;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;

import config.ActionKeywords;
import common.Constant;
import utils.ExcelUtils;
import utils.Log;

public class DriverScript {

    public static Properties OR;
    public static ActionKeywords actionKeywords;
    public static String sActionKeyword;
    public static String sPageObject;
    public static Method method[];

    public static int iTestStep;
    public static int iTestLastStep;
    public static String sTestCaseID;
    public static String sRunMode;
    public static String sData;
    public static boolean bResult;

    public DriverScript() throws NoSuchMethodException, SecurityException{
        actionKeywords = new ActionKeywords();
        method = actionKeywords.getClass().getMethods();
    }

    public static void main(String[] args) throws Exception {
        ExcelUtils.setExcelFile(Constant.Path_TestData);
        DOMConfigurator.configure("log4j.xml");
        String Path_OR = Constant.Path_OR;
        FileInputStream fs = new FileInputStream(Path_OR);
        OR= new Properties(System.getProperties());
        OR.load(fs);

        DriverScript startEngine = new DriverScript();
        startEngine.execute_TestCase();

    }

    private void execute_TestCase() throws Exception {
        int iTotalTestCases = ExcelUtils.getRowCount(Constant.Sheet_TestCases);
        for(int iTestcase=1;iTestcase<iTotalTestCases;iTestcase++){
            bResult = true;
            sTestCaseID = ExcelUtils.getCellData(iTestcase, Constant.Col_TestCaseID, Constant.Sheet_TestCases);
            sRunMode = ExcelUtils.getCellData(iTestcase, Constant.Col_RunMode,Constant.Sheet_TestCases);
            if (sRunMode.equals("Yes")){
                Log.startTestCase(sTestCaseID);
                iTestStep = ExcelUtils.getRowContains(sTestCaseID, Constant.Col_TestCaseID, Constant.Sheet_TestSteps);
                iTestLastStep = ExcelUtils.getTestStepsCount(Constant.Sheet_TestSteps, sTestCaseID, iTestStep);
                bResult=true;
                for (;iTestStep<iTestLastStep;iTestStep++){
                    sActionKeyword = ExcelUtils.getCellData(iTestStep, Constant.Col_ActionKeyword,Constant.Sheet_TestSteps);
                    sPageObject = ExcelUtils.getCellData(iTestStep, Constant.Col_PageObject, Constant.Sheet_TestSteps);
                    sData = ExcelUtils.getCellData(iTestStep, Constant.Col_DataSet, Constant.Sheet_TestSteps);
                    execute_Actions();
                    if(bResult==false){
                        ExcelUtils.setCellData(Constant.KEYWORD_FAIL,iTestcase,Constant.Col_Result,Constant.Sheet_TestCases);
                        Log.endTestCase(sTestCaseID);
                        break;
                    }
                }
                if(bResult==true){
                    ExcelUtils.setCellData(Constant.KEYWORD_PASS,iTestcase,Constant.Col_Result,Constant.Sheet_TestCases);
                    Log.endTestCase(sTestCaseID);
                }
            }
        }
    }

    private static void execute_Actions() throws Exception {

        for(int i=0;i<method.length;i++){

            if(method[i].getName().equals(sActionKeyword)){
                method[i].invoke(actionKeywords,sPageObject, sData);
                if(bResult==true){
                    ExcelUtils.setCellData(Constant.KEYWORD_PASS, iTestStep, Constant.Col_TestStepResult, Constant.Sheet_TestSteps);
                    break;
                }else{
                    ExcelUtils.setCellData(Constant.KEYWORD_FAIL, iTestStep, Constant.Col_TestStepResult, Constant.Sheet_TestSteps);
                    ActionKeywords.closeBrowser("","");
                    break;
                }
            }
        }
    }

}