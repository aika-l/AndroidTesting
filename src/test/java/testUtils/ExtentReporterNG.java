package testUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	static ExtentReports extent;
	
	public static ExtentReports getReporterObject() {

		// 1st need obj of these classes: ExtentReports, ExtentSparkReporter
		// extentSparkReporter --> responsible for creating html report
		// set a path for your reporter
		// reporter is resposible for configuration of your report
		String path = System.getProperty("user.dir")+"//reports//Spark.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path); 
		reporter.config().setReportName("Android Automation Results");
		reporter.config().setDocumentTitle("Test Results");  // tab title
	
		//This drives all reporting execution (creation and consolidation of all test execution)
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Alyssa Q"); //tester's name will be on the report
		
		return extent;
	}
	
	
}
