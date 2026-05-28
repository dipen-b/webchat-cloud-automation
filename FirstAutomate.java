package automationtest;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class FirstAutomate {

    public static void main(String[] args) throws Exception {

        DesiredCapabilities cap = new DesiredCapabilities();

        cap.setCapability("platformName", "Android");
        cap.setCapability("deviceName", "Android");

        cap.setCapability("appPackage", "com.android.settings");
        cap.setCapability("appActivity", "com.android.settings.Settings");

        AndroidDriver driver =
                new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);

        System.out.println("App Opened");

        Thread.sleep(5000);

        driver.quit();
    }
}