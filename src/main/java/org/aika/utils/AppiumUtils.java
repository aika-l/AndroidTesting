package org.aika.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;


public class AppiumUtils {
	
	public AppiumDriverLocalService service;
	
	public Double getFormatedAmount(String amount) {
		Double price = Double.parseDouble(amount.substring(1).trim());
		return price;
	}
	
	public void waitForElementToAppear(WebElement ele, AppiumDriver driver) {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.attributeContains((ele), "text", "Cart"));
	}

	// Convert json file into HashMap
	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException{
		///Users/aiday/eclipse-workspace/AppiumFrameworkDesignAndroidOnly/src/test/java/org/aika/testData/eCommerce.json
		//Converting json file content to json string                     //user.dir --> /Users/aiday/eclipse-workspace/AppiumFrameworkDesignAndroidOnly
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, 
				new TypeReference<List<HashMap<String, String>>>() {});
		
		return data;
	}
	
	// for local you can hardcode your server, but use this for CICD pipeline
	public AppiumDriverLocalService startAppiumServer(String ipAddress, int port) {
		service = new AppiumServiceBuilder()
				.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
			//	.withIPAddress("127.0.0.1")
				.withIPAddress(ipAddress).usingPort(port).build();
			//	.usingPort(4723).build();
		
		//service.start();
		return service;
	}
	
	public String getScreenshotPath(String testCaseName, AppiumDriver driver) throws IOException {
		
		File source = driver.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
		FileUtils.copyFile(source, new File(destinationFile));
			// 1. capture and place in folder
			// 2. extent report pick file and attach to report
		return destinationFile;
		
	}
}
