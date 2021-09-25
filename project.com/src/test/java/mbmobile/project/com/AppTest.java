package mbmobile.project.com;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.mobile.NetworkConnection;
import org.openqa.selenium.mobile.NetworkConnection.ConnectionType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidBatteryInfo;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.connection.ConnectionState;
import io.appium.java_client.android.connection.ConnectionStateBuilder;
import io.appium.java_client.android.connection.HasNetworkConnection;
import io.appium.java_client.android.connection.*;
//import io.appium.java_client.android.connection.ConnectionState;
import io.appium.java_client.android.connection.ConnectionState;
import io.appium.java_client.remote.MobileCapabilityType;
import mbmobilepage.project.com.HomePage;



public class AppTest {

	private File projectDirectory;
	private File appFile;
	public AndroidDriver<MobileElement> driver;
	public Runtime rt;

	@BeforeSuite
	public void startUp() throws IOException, InterruptedException {
		rt = Runtime.getRuntime();
		//rt.exec("cmd.exe /c start cmd.exe /k \\\"cd users\\dheer appium -a 127.0.0.1 -p 4723 --session-override -dc \\\"{\\\"\\\"noReset\\\"\\\": \\\"\\\"false\\\"\\\"}\\\"\\\"");
		Process p = rt.exec("adb devices");
		InputStream is = p.getInputStream();
		String[] split=null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String s= (reader.readLine());
        System.out.println("-----"+s+"end");
			while ((s = reader.readLine()) != null) {
			split = s.split(" ");	
			System.out.println("Device name "+split[0]);
			}
			is.close();
		projectDirectory = new File(System.getProperty("user.dir"));
		appFile = new File(projectDirectory, "apk/ApiDemos-debug.apk");
		System.out.println(projectDirectory.getAbsolutePath());
		System.out.println(appFile.getAbsolutePath());
		rt.exec("adb shell input keyevent KEYCODE_POWER");
		//rt.exec("adb shell am start -n io.appium.unlock/.Unlock");
		//rt.exec("adb shell input keyevent 82");
		//rt.exec("adb shell input swipe 930 880 930 380");
		//rt.exec("adb shell input text 1234");
		/*rt.exec("adb shell input keyevent 82 && adb shell input keyevent 82 && adb shell input text 1234 && adb shell input keyevent 66");
        */
		DesiredCapabilities cap = new DesiredCapabilities();
		//cap.setCapability(MobileCapabilityType.APP, appFile.getAbsolutePath());
		cap.setCapability("appPackage","com.timesgroup.magicbricks");
		// Activity name for the Android activity you want to launch from your package
		cap.setCapability("appActivity","com.til.mb.home.RedHomeView");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, split[0]);
		cap.setCapability(MobileCapabilityType.UDID, split[0]);
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		cap.setCapability("unlockType","pin");
		cap.setCapability("unlockKey","1234");
		cap.setCapability("autoGrantPermissions", "true");
		try {
		      driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),cap);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HomePage hp = new HomePage(driver);
		WebDriverWait wait = new WebDriverWait(driver,10);
		boolean screenlock = driver.isDeviceLocked();
		driver.unlockDevice();
		//driver.toggleWifi();
		rt.exec("adb shell am broadcast -a io.appium.settings.wifi --es setstatus enable");
		wait.until(ExpectedConditions.visibilityOf(hp.getElement("location")));
		hp.tap(hp.getElement("allowOnce"));
		//rt.exec("adb shell input keyevent KEYCODE_ENTER");
		System.out.println("screen is locked- "+screenlock);
		
		//driver.get("https://www.amazon.com");
		//driver.findElementByXPath("//android.widget.TextView[@content-desc='Access\'ibility']").click();
		//driver.navigate().back();
		
		Thread.sleep(5000);
		hp.tap(hp.getElement("menu"));
		Thread.sleep(2000);
		hp.scroll("Home Inspection");
		//hp.scroll(hp.getElement("menu"));
		hp.tap(hp.getElement("ratesnTrends"));
		wait.until(ExpectedConditions.visibilityOf(hp.getElement("location")));
		hp.tap(hp.getElement("location"));
		wait.until(ExpectedConditions.visibilityOf(hp.getElement("mumbai")));
		hp.tap(hp.getElement("mumbai"));
		wait.until(ExpectedConditions.visibilityOf(hp.getElement("done")));
		hp.tap(hp.getElement("done"));
		wait.until(ExpectedConditions.visibilityOf(hp.getElement("forSelect")));
		hp.tap(hp.getElement("forSelect"));
		wait.until(ExpectedConditions.visibilityOf(hp.getElement("forFlat")));
		hp.tap(hp.getElement("forFlat"));
		hp.tap(hp.getElement("showTrends"));
		Thread.sleep(5000);
		

	}
	@AfterSuite
	public void shutDown() {
		/*try {
			rt.exec("taskkill /F /IM node.exe");
			rt.exec("taskkill /F /IM cmd.exe");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		driver.closeApp();
	}

}
