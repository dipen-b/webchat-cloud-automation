package automationtest;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Random;

public class DetectFlow {

    static AndroidDriver driver;

    // =====================================================
    // MAIN
    // =====================================================

    public static void main(String[] args) {

        try {

            // =================================================
            // CAPABILITIES
            // =================================================

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

            // IMPORTANT
            // REMOVED appActivity
            // Appium auto-detects launcher activity

            caps.setCapability(
                    "appium:noReset",
                    true
            );

            caps.setCapability(
                    "appium:autoGrantPermissions",
                    true
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

            // =================================================
            // DRIVER
            // =================================================

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
                    "====================================="
            );

            System.out.println(
                    "APPLICATION STARTED SUCCESSFULLY"
            );

            System.out.println(
                    "====================================="
            );

            Thread.sleep(6000);

            // =================================================
            // DETECT FLOW
            // =================================================

            boolean firstTimeUser = false;

            for (int i = 1; i <= 10; i++) {

                System.out.println(
                        "CHECKING CURRENT SCREEN : " + i
                );

                // =========================================
                // LANGUAGE SCREEN
                // =========================================

                if (isElementPresent(
                        By.id(
                                "com.web.messenger.dual.chat:id/ivSelect"
                        )
                )) {

                    firstTimeUser = true;

                    System.out.println(
                            "SCREEN : LANGUAGE SELECTION SCREEN"
                    );

                    break;
                }

                // =========================================
                // CONTINUE SCREEN
                // =========================================

                if (isElementPresent(
                        By.xpath(
                                "//android.widget.Button[contains(@text,'Continue')]"
                        )
                )) {

                    System.out.println(
                            "SCREEN : CONTINUE SCREEN"
                    );

                    break;
                }

                Thread.sleep(2000);
            }

            // =================================================
            // START FLOW
            // =================================================

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
                    "====================================="
            );

            System.out.println(
                    "STARTING FIRST TIME USER FLOW"
            );

            System.out.println(
                    "====================================="
            );

            // =================================================
            // LANGUAGE SCREEN
            // =================================================

            System.out.println(
                    "CURRENT SCREEN : LANGUAGE SCREEN"
            );

            clickIfPresent(
                    "Language Selection",
                    By.id(
                            "com.web.messenger.dual.chat:id/ivSelect"
                    )
            );

            Thread.sleep(1500);

            clickIfPresent(
                    "Language Done Button",
                    By.id(
                            "com.web.messenger.dual.chat:id/btnDone"
                    )
            );

            Thread.sleep(3000);

            // =================================================
            // ONBOARDING SCREEN 1
            // =================================================

            System.out.println(
                    "CURRENT SCREEN : ONBOARDING SCREEN 1"
            );

            clickIfPresent(
                    "Onboarding Next 1",
                    By.id(
                            "com.web.messenger.dual.chat:id/lvBtnNext"
                    )
            );

            Thread.sleep(2500);

            // =================================================
            // ONBOARDING SCREEN 2
            // =================================================

            System.out.println(
                    "CURRENT SCREEN : ONBOARDING SCREEN 2"
            );

            clickIfPresent(
                    "Onboarding Next 2",
                    By.id(
                            "com.web.messenger.dual.chat:id/lvBtnNext"
                    )
            );

            Thread.sleep(2500);

            // =================================================
            // ONBOARDING SCREEN 3
            // =================================================

            System.out.println(
                    "CURRENT SCREEN : ONBOARDING SCREEN 3"
            );

            clickIfPresent(
                    "Onboarding Next 3",
                    By.id(
                            "com.web.messenger.dual.chat:id/lvBtnNext"
                    )
            );

            Thread.sleep(4000);

            // =================================================
            // PHONE NUMBER SCREEN
            // =================================================

            if (isElementPresent(
                    By.id(
                            "com.web.messenger.dual.chat:id/etPhoneNumber"
                    )
            )) {

                System.out.println(
                        "CURRENT SCREEN : PHONE NUMBER SCREEN"
                );

                String randomPhone =
                        generateRandomPhoneNumber();

                WebElement phoneField =
                        driver.findElement(
                                By.id(
                                        "com.web.messenger.dual.chat:id/etPhoneNumber"
                                )
                        );

                // CLICK FIELD

                phoneField.click();

                Thread.sleep(1000);

                // CLEAR FIELD

                phoneField.clear();

                Thread.sleep(1000);

                // ENTER NUMBER

                phoneField.sendKeys(
                        randomPhone
                );

                System.out.println(
                        "PHONE NUMBER ENTERED"
                );

                Thread.sleep(2000);

                // CLOSE KEYBOARD

                closeKeyboard();

                Thread.sleep(2000);

                // CONTINUE BUTTON

                clickIfPresent(
                        "Phone Continue Button",
                        By.id(
                                "com.web.messenger.dual.chat:id/btnContinue"
                        )
                );

                Thread.sleep(5000);
            }

            // =================================================
            // SUBSCRIPTION SCREEN
            // =================================================

            handleSubscriptionPopup();

            // =================================================
            // PERMISSION SCREEN
            // =================================================

            handlePermissionPopup();

            // =================================================
            // HOME SCREEN
            // =================================================

            if (isHomeScreen()) {

                System.out.println(
                        "====================================="
                );

                System.out.println(
                        "HOME SCREEN REACHED SUCCESSFULLY"
                );

                System.out.println(
                        "====================================="
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
                    "====================================="
            );

            System.out.println(
                    "STARTING SECOND TIME USER FLOW"
            );

            System.out.println(
                    "====================================="
            );

            Thread.sleep(3000);

            // =================================================
            // CONTINUE SCREEN 1
            // =================================================

            System.out.println(
                    "CURRENT SCREEN : CONTINUE SCREEN 1"
            );

            clickIfPresent(
                    "Continue Button 1",
                    By.xpath(
                            "//android.widget.Button[contains(@text,'Continue')]"
                    )
            );

            Thread.sleep(4000);

            // =================================================
            // CONTINUE SCREEN 2
            // =================================================

            System.out.println(
                    "CURRENT SCREEN : CONTINUE SCREEN 2"
            );

            clickIfPresent(
                    "Continue Button 2",
                    By.xpath(
                            "//android.widget.Button[contains(@text,'Continue')]"
                    )
            );

            Thread.sleep(5000);

            // =================================================
            // SUBSCRIPTION SCREEN
            // =================================================

            handleSubscriptionPopup();

            // =================================================
            // PERMISSION SCREEN
            // =================================================

            handlePermissionPopup();

            // =================================================
            // HOME SCREEN
            // =================================================

            if (isHomeScreen()) {

                System.out.println(
                        "====================================="
                );

                System.out.println(
                        "HOME SCREEN REACHED SUCCESSFULLY"
                );

                System.out.println(
                        "====================================="
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

            Thread.sleep(3000);

            System.out.println(
                    "CURRENT SCREEN : SUBSCRIPTION SCREEN"
            );

            // CLOSE BUTTON

            if (isElementPresent(
                    By.id(
                            "com.web.messenger.dual.chat:id/ivClose"
                    )
            )) {

                clickIfPresent(
                        "Subscription Close Button",
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
                        "Hide Subscription Option",
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
                        "Final Subscription Close",
                        By.id(
                                "com.web.messenger.dual.chat:id/ivClose"
                        )
                );

                Thread.sleep(3000);
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

            System.out.println(
                    "CURRENT SCREEN : PERMISSION SCREEN"
            );

            // ANDROID 14+

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

                System.out.println(
                        "PERMISSION ALLOWED"
                );

                Thread.sleep(3000);

                return;
            }

            // OLD ANDROID

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

                System.out.println(
                        "PERMISSION ALLOWED"
                );

                Thread.sleep(3000);

                return;
            }

            // TEXT BUTTON

            List<WebElement> allowButtons =
                    driver.findElements(
                            AppiumBy.androidUIAutomator(
                                    "new UiSelector().textContains(\"Allow\")"
                            )
                    );

            if (allowButtons.size() > 0) {

                allowButtons.get(0).click();

                System.out.println(
                        "PERMISSION ALLOWED"
                );

                Thread.sleep(3000);
            }

        } catch (Exception ignored) {
        }
    }

    // =====================================================
    // CLICK IF PRESENT
    // =====================================================

    public static void clickIfPresent(
            String actionName,
            By by
    ) {

        try {

            List<WebElement> elements =
                    driver.findElements(by);

            if (elements.size() > 0) {

                elements.get(0).click();

                System.out.println(
                        "ACTION : " + actionName
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
    // RANDOM PHONE NUMBER
    // =====================================================

    public static String generateRandomPhoneNumber() {

        Random random =
                new Random();

        StringBuilder phone =
                new StringBuilder("9");

        for (int i = 0; i < 9; i++) {

            phone.append(
                    random.nextInt(10)
            );
        }

        return phone.toString();
    }

    // =====================================================
    // CLOSE KEYBOARD
    // =====================================================

    public static void closeKeyboard() {

        try {

            driver.hideKeyboard();

            System.out.println(
                    "KEYBOARD CLOSED"
            );

        } catch (Exception e) {

            try {

                driver.navigate().back();

                System.out.println(
                        "KEYBOARD CLOSED USING BACK"
                );

            } catch (Exception ignored) {
            }
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
                    "CURRENT PACKAGE : " + currentPackage
            );

            return currentPackage.equals(
                    "com.web.messenger.dual.chat"
            );

        } catch (Exception e) {

            return false;
        }
    }
}