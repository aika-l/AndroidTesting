package testUtils;

import org.testng.annotations.BeforeClass;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.aika.pageObjects.android.FormPage;
import org.aika.utils.AppiumUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest extends AppiumUtils {
	
	public AndroidDriver driver;
	public FormPage formPage;
	
	@BeforeClass (alwaysRun = true)
	public void ConfigureAppium() throws IOException {	
		// moved to AppiumTest since it can be used for both IOS and Android if you have both here
//		service = new AppiumServiceBuilder()
//				.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
//				.withIPAddress("127.0.0.1")
//				.usingPort(4723).build();
//		
//		//service.start();
		// port and ipAddress should come from global properties
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//resources//data.properties");
		String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");
		prop.load(fis);
	//	String ipAddress = prop.getProperty("ipAddress");
		String port = prop.getProperty("port"); //it can only return String
		
		service = startAppiumServer(ipAddress, Integer.parseInt(port));
		
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(prop.getProperty("AndroidDeviceName"));
	//	options.setChromedriverExecutable("/Users/aiday/Downloads/chromedriver_mac64/chromedriver");
	//	options.setCapability("browserName", "Chrome");
		options.setApp(System.getProperty("user.dir")+"//src//test//java//org//aika//resources//General-Store.apk");
	//	driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options); // optimized version below
		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		formPage = new FormPage(driver);
		
	}
	

	@AfterClass (alwaysRun = true)
	public void tearDown() {
		driver.quit();
		//service.stop();
	}
}
