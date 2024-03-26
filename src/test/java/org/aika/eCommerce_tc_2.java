package org.aika;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.time.Duration;
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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import testUtils.BaseTest;

public class eCommerce_tc_2 extends BaseTest {

	@BeforeMethod
	public void preSetup() {
		// screen to Home Page
		Activity activity = new Activity("io.appium.android.apis", "io.appium.android.apis.preference.PreferenceDependencies");
		((JavascriptExecutor)driver).executeScript("mobile: startActivity", ImmutableMap.of(
				"intent", "io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies")); 
				
	}
	
	
	
	@Test
	public void errorMsg() {
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		String toastMessage = driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
	// Tag:	android.widget.Toast
		AssertJUnit.assertEquals(toastMessage, "Please enter your name");
	}
	
	@Test
	public void FillForm_PositiveFlow() {
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Rahul");
		driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		
		AssertJUnit.assertTrue(driver.findElements(By.xpath("//android.widget.Toast)[1]")).size()<1);
		
		System.out.println("Testing GIT in Eclipse");


	}
	
	
}
