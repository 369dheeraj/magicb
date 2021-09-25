package mbmobilepage.project.com;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import static io.appium.java_client.touch.TapOptions.tapOptions;
//import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

import java.lang.reflect.Field;

public class HomePage {
	
	private AndroidDriver<MobileElement> driver;
	
	public HomePage(AndroidDriver<MobileElement> driver) {
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	 @AndroidFindBy(id = "com.timesgroup.magicbricks:id/bar_icon")
     public  WebElement menu;
	 
	 @AndroidFindBy(xpath = "//android.widget.TextView[@text='Propworth']")
     public  WebElement propWorth;
	 
	 @AndroidFindBy(xpath = "//android.widget.TextView[@text='Rates & Trends']")
     public  WebElement ratesnTrends;
	 
	 @AndroidFindBy(xpath = "//android.widget.TextView[@text='City, Locality,Project']")
     public  WebElement location;
	 
	 @AndroidFindBy(xpath = "//android.widget.TextView[@text='Mumbai']")
     public  WebElement mumbai;
	 
	 @AndroidFindBy(xpath = "//android.widget.TextView[@text='Done']")
     public  WebElement done;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@text='SHOW TRENDS']")
	 public WebElement showTrends;
	 
	 @AndroidFindBy(id="com.timesgroup.magicbricks:id/spinner1")
	 public WebElement forSelect;
	 
	 @AndroidFindBy(xpath="//android.widget.TextView[@text='Flats']")
	 public MobileElement forFlat;
	 
	 @AndroidFindBy(xpath="//android.widget.Button[@text='Allow Once']")
	 public MobileElement allowOnce;
	 
	
	 
	 public WebElement getElement(String locator) { 
		 Field field;
			try {
				field = this.getClass().getField(locator);
				WebElement ele = (WebElement) field.get(this);
				return ele;
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return null;
	 }
	 
	 public void tap(WebElement ele) {
		 TouchAction<?> act = new TouchAction<>(driver);
		 act.tap(tapOptions().withElement(element(ele))).perform();
	 }
	
	 public void scroll(String ele) {
		 //driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text("+"\""+ele+"\""+"));");
		 //driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+ele+"\").instance(0))");
		 driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"Tools & Advice\"));");
		 /*TouchActions action = new TouchActions(driver);
		 action.scroll(ele, 10, 100);
		 action.perform();*/
		 
		 ////*[@id='com.timesgroup.magicbricks:id/drawer_lvw_menu']//ListView/RelativeLayout[1]/TextView[2]
		 //driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text("+"\""+ ele +"\""+ "))");
	 }

}
