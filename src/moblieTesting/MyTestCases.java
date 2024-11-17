package moblieTesting;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class MyTestCases {
	DesiredCapabilities cap = new DesiredCapabilities();
	String Appinumurl = "http://192.168.100.100:4723/wd/hub";
	AndroidDriver driver;

	@BeforeTest
	public void setUp() throws MalformedURLException {
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 3a XL API 31");
		File myapp = new File("src/Apps/calculator.apk");
		cap.setCapability(MobileCapabilityType.APP, myapp.getAbsolutePath());
		driver = new AndroidDriver(new URL(Appinumurl), cap);
	}

	@Test(priority = 1, enabled = false)
	public void firstTest() {
		driver.findElement(By.id("com.google.android.calculator:id/digit_9")).click();
		driver.findElement(By.id("com.google.android.calculator:id/op_add")).click();
		driver.findElement(By.id("com.google.android.calculator:id/digit_3")).click();
		String acutil = driver.findElement(By.id("com.google.android.calculator:id/result_preview")).getText();
		String excputed = "12";
		assertEquals(acutil, excputed);

	}

	@Test(priority = 2, enabled = false)
	public void choseAllNumber() {
		List<WebElement> buttons = driver.findElements(By.className("android.widget.ImageButton"));
		for (int i = 0; i < buttons.size(); i++) {
			if (buttons.get(i).getAttribute("resource-id").contains("digit_")) {
				buttons.get(i).click();
			}

		}
		String acutil = driver.findElement(By.id("com.google.android.calculator:id/formula")).getText();
		String excputied = "7894561230";
		assertEquals(acutil, excputied);

	}

	@Test(priority = 3, enabled = true)
	public void choseAllEvenNumber() {
		List<WebElement> buttons = driver.findElements(By.className("android.widget.ImageButton"));
		for (int i = 0; i < buttons.size(); i++) {
			if (buttons.get(i).getAttribute("resource-id").contains("digit_")) {
				String w = buttons.get(i).getAttribute("resource-id").replace("com.google.android.calculator:id/digit_",
						"");
				int x = Integer.parseInt(w);
				if (x % 2 == 0) {
					buttons.get(i).click();
				}
			}

		}
		String acutil = driver.findElement(By.id("com.google.android.calculator:id/formula")).getText();
		String excputied = "84620";
		assertEquals(acutil, excputied);

	}
}
