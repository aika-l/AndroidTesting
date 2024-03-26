package org.aika;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import org.testng.AssertJUnit;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.aika.pageObjects.android.CartPage;
import org.aika.pageObjects.android.FormPage;
import org.aika.pageObjects.android.ProductCatalog;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import testUtils.BaseTest;

public class eCommerce_tc_4 extends BaseTest {

	@BeforeMethod (alwaysRun = true)
	public void preSetup() { // setting back to home page
		
		formPage.setActivity();
				
	}
	
	@Test (dataProvider = "getData", groups= {"Smoke"}) // based on method name // grouping tests
	public void LongPressActionTest(String name, String gender, String country) throws InterruptedException {
	//	public void LongPressActionTest() throws InterruptedException {
// login		
		
	//	FormPage formPage = new FormPage(driver);
// original code without dataProvider
/*
		formPage.setNameField("Jane Doe");
		formPage.setGender("female");
		formPage.setCountrySelection("Argentina");
*/
		formPage.setNameField(name);
		formPage.setGender(gender);
		formPage.setCountrySelection(country);
	//	formPage.submitForm();
		ProductCatalog prodCatalog = formPage.submitForm();
		

	//	ProductCatalog prodCatalog = new ProductCatalog(driver);
		prodCatalog.addItemToCartByIndex(0);
		prodCatalog.addItemToCartByIndex(0);
		CartPage cartPage = prodCatalog.goToCartPage();
		
		double totalSum = cartPage.getProductSum();
		double displayFormatedSum = cartPage.getTotalAmountDisplayed();
		
		AssertJUnit.assertEquals(totalSum, displayFormatedSum );
		cartPage.acceptTermsCondition();
		cartPage.submitOrder();
	}	
	
	@Test (dataProvider = "getDataJSON") // based on method name
	public void EndToEndWithJson(HashMap<String, String> input) throws InterruptedException {

		formPage.setNameField(input.get("name"));
		formPage.setGender(input.get("gender"));
		formPage.setCountrySelection(input.get("country"));
		ProductCatalog prodCatalog = formPage.submitForm();
		
		prodCatalog.addItemToCartByIndex(0);
		prodCatalog.addItemToCartByIndex(0);
		CartPage cartPage = prodCatalog.goToCartPage();
		
		double totalSum = cartPage.getProductSum();
		double displayFormatedSum = cartPage.getTotalAmountDisplayed();
		
		AssertJUnit.assertEquals(totalSum, displayFormatedSum );
		cartPage.acceptTermsCondition();
		cartPage.submitOrder();
	}	

	
	@DataProvider
	public Object[][] getData() {
		
		return new Object[][] {{"Rahul shetty", "female", "Argentina"}, {"2nd TestCase", "female", "Armenia"}};
	
	}
	
	
	// reading from JSON file
	@DataProvider
	public Object[][] getDataJSON() throws IOException {
	
		List<HashMap<String, String>> data = getJsonData(System.getProperty("user.dir")+"/src/test/java/org/aika/testData/eCommerce.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	
	}
}
