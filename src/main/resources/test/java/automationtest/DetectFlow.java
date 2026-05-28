package automationtest;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;
import java.util.List;

public class DetectFlow {

    static AndroidDriver driver;

    public static void main(String[] args) {

        try {

            // =========================================
            // CAPABILITIES
            // =========================================

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

            caps.setCapability(
                    "appium:appActivity",
                    "com.web.messenger.dual.chat.activity.SplashActivity"
            );

            caps.setCapability(
                    "appium:noReset",
                    true
            );

            caps.setCapability(
                    "appium:autoGrantPermissions",
                    false
            );

            caps.setCapability(
                    "appium:newCommandTimeout",
                    300
            );

            caps.setCapability(
                    "appium:adbExecTimeout",
                    120000
            );

            caps.setCapability(
                    "appium:uiautomator2ServerLaunchTimeout",
                    120000
            );

            caps.setCapability(
                    "appium:ignoreHiddenApiPolicyError",
                    true
            );

            // =========================================
            // DRIVER
            // =========================================

            driver = new AndroidDriver(
                    new URL("http://127.0.0.1:4723"),
                    caps
            );

            driver.manage()
                    .timeouts()
                    .implicitlyWait(
                            Duration.ofSeconds(5)
                    );

            System.out.println(
                    "Application Started Successfully"
            );

            Thread.sleep(5000);

            // =========================================
            // ACTIVATE APP
            // =========================================

            driver.activateApp(
                    "com.web.messenger.dual.chat"
            );

            System.out.println(
                    "App Activated"
            );

            Thread.sleep(5000);

            // =========================================
            // FLOW DETECTION
            // =========================================

            boolean firstTimeUser = false;

            for (int i = 1; i <= 10; i++) {

                System.out.println(
                        "Checking Current Screen : " + i
                );

                // =====================================
                // LANGUAGE SCREEN
                // =====================================

                if (driver.findElements(
                        By.id(
                                "com.web.messenger.dual.chat:id/ivSelect"
                        )
                ).size() > 0) {

                    firstTimeUser = true;

                    System.out.println(
                            "FIRST TIME FLOW DETECTED"
                    );

                    break;
                }

                // =====================================
                // SECOND TIME FLOW
                // =====================================

                if (driver.findElements(
                        By.xpath(
                                "//android.widget.Button[contains(@text,'Continue')]"
                        )
                ).size() > 0) {

                    System.out.println(
                            "SECOND TIME FLOW DETECTED"
                    );

                    break;
                }

                Thread.sleep(2000);
            }

            // =========================================
            // START FLOW
            // =========================================

            if (firstTimeUser) {

                firstTimeFlow();

            } else {

                secondTimeFlow();
            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                if (driver != null) {

                    driver.quit();
                }

            } catch (Exception ignored) {
            }
        }
    }

    // =====================================================
    // FIRST TIME FLOW
    // =====================================================

    public static void firstTimeFlow() {

        try {

            System.out.println(
                    "Executing First Time Flow"
            );

            // =====================================
            // LANGUAGE SELECT
            // =====================================

            clickIfPresent(
                    By.id(
                            "com.web.messenger.dual.chat:id/ivSelect"
                    )
            );

            Thread.sleep(1500);

            clickIfPresent(
                    By.id(
                            "com.web.messenger.dual.chat:id/btnDone"
                    )
            );

            Thread.sleep(2500);

            // =====================================
            // NEXT BUTTONS
            // =====================================

            for (int i = 1; i <= 3; i++) {

                clickIfPresent(
                        By.id(
                                "com.web.messenger.dual.chat:id/lvBtnNext"
                        )
                );

                Thread.sleep(2500);
            }

            // =====================================
            // PHONE NUMBER
            // =====================================

            if (isElementPresent(
                    By.id(
                            "com.web.messenger.dual.chat:id/etPhoneNumber"
                    )
            )) {

                WebElement phone =
                        driver.findElement(
                                By.id(
                                        "com.web.messenger.dual.chat:id/etPhoneNumber"
                                )
                        );

                phone.click();

                Thread.sleep(1000);

                phone.clear();

                phone.sendKeys(
                        "9876543210"
                );

                Thread.sleep(1000);

                try {

                    driver.hideKeyboard();

                } catch (Exception ignored) {
                }

                Thread.sleep(1000);

                clickIfPresent(
                        By.id(
                                "com.web.messenger.dual.chat:id/btnContinue"
                        )
                );
            }

            Thread.sleep(4000);

            // =====================================
            // SUBSCRIPTION
            // =====================================

            handleSubscriptionPopup();

            // =====================================
            // PERMISSION
            // =====================================

            handlePermissionPopup();

            // =====================================
            // HOME
            // =====================================

            if (isHomeScreen()) {

                System.out.println(
                        "Reached Home Screen Successfully"
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =====================================================
    // SECOND TIME FLOW
    // =====================================================

    public static void secondTimeFlow() {

        try {

            System.out.println(
                    "Executing Second Time Flow"
            );

            Thread.sleep(3000);

            // =====================================
            // CONTINUE BUTTONS
            // =====================================

            for (int i = 1; i <= 2; i++) {

                clickIfPresent(
                        By.xpath(
                                "//android.widget.Button[contains(@text,'Continue')]"
                        )
                );

                Thread.sleep(3000);
            }

            // =====================================
            // SUBSCRIPTION
            // =====================================

            handleSubscriptionPopup();

            // =====================================
            // PERMISSION
            // =====================================

            handlePermissionPopup();

            // =====================================
            // HOME
            // =====================================

            if (isHomeScreen()) {

                System.out.println(
                        "Reached Home Screen Successfully"
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =====================================================
    // SUBSCRIPTION POPUP
    // =====================================================

    public static void handleSubscriptionPopup() {

        try {

            Thread.sleep(2000);

            // CLOSE BUTTON

            if (isElementPresent(
                    By.id(
                            "com.web.messenger.dual.chat:id/ivClose"
                    )
            )) {

                clickIfPresent(
                        By.id(
                                "com.web.messenger.dual.chat:id/ivClose"
                        )
                );

                Thread.sleep(2000);
            }

            // HIDE OPTION

            if (isElementPresent(
                    By.id(
                            "com.web.messenger.dual.chat:id/tvHideOptions"
                    )
            )) {

                clickIfPresent(
                        By.id(
                                "com.web.messenger.dual.chat:id/tvHideOptions"
                        )
                );

                Thread.sleep(2000);
            }

            // SECOND CLOSE

            if (isElementPresent(
                    By.id(
                            "com.web.messenger.dual.chat:id/ivClose"
                    )
            )) {

                clickIfPresent(
                        By.id(
                                "com.web.messenger.dual.chat:id/ivClose"
                        )
                );

                Thread.sleep(2000);
            }

        } catch (Exception ignored) {
        }
    }

    // =====================================================
    // HANDLE PERMISSION
    // =====================================================

    public static void handlePermissionPopup() {

        try {

            Thread.sleep(3000);

            // =====================================
            // ANDROID 14+
            // =====================================

            if (isElementPresent(
                    By.id(
                            "com.android.permissioncontroller:id/permission_allow_foreground_only_button"
                    )
            )) {

                driver.findElement(
                        By.id(
                                "com.android.permissioncontroller:id/permission_allow_foreground_only_button"
                        )
                ).click();

                Thread.sleep(3000);

                return;
            }

            // =====================================
            // OLD ANDROID
            // =====================================

            if (isElementPresent(
                    By.id(
                            "com.android.permissioncontroller:id/permission_allow_button"
                    )
            )) {

                driver.findElement(
                        By.id(
                                "com.android.permissioncontroller:id/permission_allow_button"
                        )
                ).click();

                Thread.sleep(3000);

                return;
            }

            // =====================================
            // TEXT BASED BUTTON
            // =====================================

            List<WebElement> allowButtons =
                    driver.findElements(
                            AppiumBy.androidUIAutomator(
                                    "new UiSelector().textContains(\"While using\")"
                            )
                    );

            if (allowButtons.size() > 0) {

                allowButtons.get(0).click();

                Thread.sleep(3000);

                return;
            }

            List<WebElement> allowButtons2 =
                    driver.findElements(
                            AppiumBy.androidUIAutomator(
                                    "new UiSelector().textContains(\"Allow\")"
                            )
                    );

            if (allowButtons2.size() > 0) {

                allowButtons2.get(0).click();

                Thread.sleep(3000);
            }

        } catch (Exception ignored) {
        }
    }

    // =====================================================
    // CLICK IF PRESENT
    // =====================================================

    public static void clickIfPresent(By by) {

        try {

            List<WebElement> elements =
                    driver.findElements(by);

            if (elements.size() > 0) {

                elements.get(0).click();

                System.out.println(
                        "Clicked : " + by
                );
            }

        } catch (Exception ignored) {
        }
    }

    // =====================================================
    // ELEMENT PRESENT
    // =====================================================

    public static boolean isElementPresent(By by) {

        try {

            return driver.findElements(by).size() > 0;

        } catch (Exception e) {

            return false;
        }
    }

    // =====================================================
    // HOME SCREEN CHECK
    // =====================================================

    public static boolean isHomeScreen() {

        try {

            String currentPackage =
                    driver.getCurrentPackage();

            System.out.println(
                    "Current Package : " + currentPackage
            );

            return currentPackage.equals(
                    "com.web.messenger.dual.chat"
            );

        } catch (Exception e) {

            return false;
        }
    }
}