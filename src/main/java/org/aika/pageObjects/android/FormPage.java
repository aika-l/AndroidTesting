package org.aika.pageObjects.android;

import org.aika.utils.AndroidActions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;


public class FormPage extends AndroidActions {
	
	AndroidDriver driver;
	
	public FormPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);  //this refers to everything in this class
	}
	
	
	// 		Object																	//action
	//		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Test 1");
	// to get the driver we need to create a constructor
	
	
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	
	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Female']")
	private WebElement femaleOption;
	//driver.findElement(By.xpath("//android.widget.RadioButton[@text='Female']")).click();
	
	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Male']")
	private WebElement maleOption;
	
	@AndroidFindBy(id="android:id/text1")
	private WebElement countrySelection;

	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement shopButton;
	
	
	
	
	public void setNameField(String name) {
		nameField.sendKeys(name);
		driver.hideKeyboard();
	}
	
	public void setGender(String gender) {
		if(gender.contains("female")) 
			femaleOption.click();
		else 
			maleOption.click();
	}
	
	public void setCountrySelection(String countryName) {
		countrySelection.click();
		scrollToText(countryName);
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+ countryName +"']")).click();
	}
	
	public ProductCatalog submitForm() {
		shopButton.click();
		return new ProductCatalog(driver); //so instead of creating a new object in my test I just create an object here
	}
	
	public void setActivity() {
		// screen to Home Page
		Activity activity = new Activity("io.appium.android.apis", "io.appium.android.apis.preference.PreferenceDependencies");
		((JavascriptExecutor)driver).executeScript("mobile: startActivity", ImmutableMap.of(
				"intent", "io.appium.android.apis/io.appium.android.apis.preference.PreferenceDependencies")); 
	}
	
}
