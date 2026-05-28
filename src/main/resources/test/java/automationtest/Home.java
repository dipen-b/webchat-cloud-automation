package automationtest;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;
import java.util.List;

public class Home {

    public static void main(String[] args) {

        AndroidDriver driver = null;

        try {

            // =====================================
            // CAPABILITIES
            // =====================================

            DesiredCapabilities caps =
                    new DesiredCapabilities();

            caps.setCapability(
                    "platformName",
                    "Android"
            );

            caps.setCapability(
                    "appium:automationName",
                    "UiAutomator2"
            );

            caps.setCapability(
                    "appium:deviceName",
                    "Android"
            );

            caps.setCapability(
                    "appium:appPackage",
                    "com.web.messenger.dual.chat"
            );

            // KEEP LOGIN SESSION

            caps.setCapability(
                    "appium:noReset",
                    true
            );

            caps.setCapability(
                    "appium:autoGrantPermissions",
                    true
            );

            caps.setCapability(
                    "appium:disableWindowAnimation",
                    true
            );

            caps.setCapability(
                    "appium:newCommandTimeout",
                    300
            );

            caps.setCapability(
                    "appium:ignoreHiddenApiPolicyError",
                    true
            );

            caps.setCapability(
                    "appium:uiautomator2ServerLaunchTimeout",
                    120000
            );

            caps.setCapability(
                    "appium:uiautomator2ServerInstallTimeout",
                    120000
            );

            caps.setCapability(
                    "appium:adbExecTimeout",
                    120000
            );

            caps.setCapability(
                    "appium:androidInstallTimeout",
                    120000
            );

            // =====================================
            // DRIVER START
            // =====================================

            driver = new AndroidDriver(
                    new URL("http://20.0.255.148:4723/"),
                    caps
            );

            driver.manage()
                    .timeouts()
                    .implicitlyWait(
                            Duration.ofSeconds(15)
                    );

            System.out.println(
                    "Application Started Successfully"
            );

            // =====================================
            // WAIT FOR APP STABILIZATION
            // =====================================

            Thread.sleep(15000);

            // =====================================
            // ACTIVATE APP
            // =====================================

            driver.activateApp(
                    "com.web.messenger.dual.chat"
            );

            Thread.sleep(5000);

            // =====================================
            // STEP 1 : FIRST CONTINUE BUTTON
            // =====================================

            List<WebElement> continueBtn1 =
                    driver.findElements(
                            By.xpath(
                                    "//android.widget.Button[contains(@text,'Continue')]"
                            )
                    );

            if (continueBtn1.size() > 0) {

                continueBtn1.get(0).click();

                System.out.println(
                        "First Continue Button Clicked"
                );

                Thread.sleep(4000);

            } else {

                System.out.println(
                        "First Continue Button Not Found"
                );
            }

            // =====================================
            // STEP 2 : SECOND CONTINUE BUTTON
            // =====================================

            List<WebElement> continueBtn2 =
                    driver.findElements(
                            By.xpath(
                                    "//android.widget.Button[contains(@text,'Continue')]"
                            )
                    );

            if (continueBtn2.size() > 0) {

                continueBtn2.get(0).click();

                System.out.println(
                        "Second Continue Button Clicked"
                );

                Thread.sleep(5000);

            } else {

                System.out.println(
                        "Second Continue Button Not Found"
                );
            }

            // =====================================
            // STEP 3 : SUBSCRIPTION SCREEN
            // =====================================

            System.out.println(
                    "Checking Subscription Screen"
            );

            // FIRST CLOSE BUTTON

            List<WebElement> closeBtn =
                    driver.findElements(
                            By.xpath(
                                    "//android.widget.ImageView[@resource-id='com.web.messenger.dual.chat:id/ivClose']"
                            )
                    );

            if (closeBtn.size() > 0) {

                closeBtn.get(0).click();

                System.out.println(
                        "Subscription Close Button Clicked"
                );

                Thread.sleep(4000);

            } else {

                System.out.println(
                        "Subscription Close Button Not Found"
                );
            }

            // HIDE OPTION

            List<WebElement> hideOption =
                    driver.findElements(
                            By.xpath(
                                    "//android.widget.TextView[@resource-id='com.web.messenger.dual.chat:id/tvHideOptions']"
                            )
                    );

            if (hideOption.size() > 0) {

                hideOption.get(0).click();

                System.out.println(
                        "Hide Option Clicked"
                );

                Thread.sleep(4000);

            } else {

                System.out.println(
                        "Hide Option Not Found"
                );
            }

            // SECOND CLOSE BUTTON

            List<WebElement> secondClose =
                    driver.findElements(
                            By.xpath(
                                    "//android.widget.ImageView[@resource-id='com.web.messenger.dual.chat:id/ivClose']"
                            )
                    );

            if (secondClose.size() > 0) {

                secondClose.get(0).click();

                System.out.println(
                        "Second Close Clicked"
                );

                Thread.sleep(5000);

            } else {

                System.out.println(
                        "Second Close Button Not Found"
                );
            }

            // =====================================
            // STEP 4 : CHECK HOME SCREEN
            // =====================================

            System.out.println(
                    "Checking Home Screen..."
            );

            Thread.sleep(5000);

            String currentPackage =
                    driver.getCurrentPackage();

            String currentActivity =
                    driver.currentActivity();

            System.out.println(
                    "Current Package : "
                            + currentPackage
            );

            System.out.println(
                    "Current Activity : "
                            + currentActivity
            );

            if (currentPackage.equals(
                    "com.web.messenger.dual.chat"
            )) {

                System.out.println(
                        "Reached Home Screen Successfully"
                );

            } else {

                System.out.println(
                        "Home Screen Not Detected"
                );
            }

            // =====================================
            // FINAL WAIT
            // =====================================

            Thread.sleep(300000);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            if (driver != null) {

                driver.quit();
            }
        }
    }
}