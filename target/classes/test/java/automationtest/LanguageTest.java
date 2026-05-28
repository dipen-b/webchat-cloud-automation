package automationtest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.URL;
import java.time.Duration;
import java.util.List;

public class LanguageTest {

    static AndroidDriver driver;

    // APP DETAILS
    static String APP_PACKAGE = "com.web.messenger.dual.chat";
    static String APPIUM_URL = "http://20.0.255.148:4723/";

    // CHANGE THIS TO YOUR ADB PATH
    static String ADB_PATH =
            "/Users/qa/Library/Android/sdk/platform-tools/adb";

    public static void main(String[] args) {

        try {

            for (int round = 1; round <= 20; round++) {

                System.out.println("=====================================");
                System.out.println("ROUND : " + round);
                System.out.println("=====================================");

                try {

                    // CLEAR APP DATA
                    clearAppData();

                    // CREATE DRIVER
                    createDriver();

                    Thread.sleep(5000);

                    // GET LANGUAGES
                    List<WebElement> languageList =
                            driver.findElements(
                                    By.xpath(
                                            "//android.widget.ImageView[@resource-id='com.web.messenger.dual.chat:id/ivSelect']"
                                    )
                            );

                    System.out.println(
                            "Total Languages Found : "
                                    + languageList.size()
                    );

                    if (languageList.size() == 0) {

                        System.out.println("No Languages Found");

                        closeDriver();

                        continue;
                    }

                    // SELECT CURRENT LANGUAGE
                    languageList.get(round - 1).click();

                    System.out.println(
                            "Language Selected : " + round
                    );

                    Thread.sleep(2000);

                    // CLICK DONE BUTTON
                    List<WebElement> doneButton =
                            driver.findElements(
                                    By.xpath(
                                            "//*[contains(@text,'Done')]"
                                    )
                            );

                    if (doneButton.size() > 0) {

                        doneButton.get(0).click();

                        System.out.println("Done Button Clicked");

                    } else {

                        System.out.println(
                                "Done Button Not Found"
                        );
                    }

                    Thread.sleep(4000);

                    // HANDLE ONBOARDING
                    handleOnboarding();

                    // CHECK HOME SCREEN
                    boolean isHome =
                            checkHomeScreen();

                    if (isHome) {

                        System.out.println(
                                "Language "
                                        + round
                                        + " Working Successfully"
                        );

                    } else {

                        System.out.println(
                                "Home Screen Not Detected"
                        );
                    }

                    closeDriver();

                    System.out.println(
                            "Round Completed Successfully"
                    );

                } catch (Exception e) {

                    System.out.println("Round Failed");

                    e.printStackTrace();

                    try {

                        closeDriver();

                    } catch (Exception ignored) {
                    }
                }
            }

            System.out.println(
                    "====================================="
            );

            System.out.println(
                    "ALL LANGUAGE TESTING COMPLETED"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // CREATE DRIVER
    public static void createDriver() throws Exception {

        UiAutomator2Options options =
                new UiAutomator2Options();

        options.setPlatformName("Android");

        options.setAutomationName("UiAutomator2");

        options.setDeviceName("Android");

        options.setAppPackage(APP_PACKAGE);

        options.setNoReset(false);

        options.setAutoGrantPermissions(true);

        options.setCapability(
                "disableWindowAnimation",
                true
        );

        options.setCapability(
                "ignoreHiddenApiPolicyError",
                true
        );

        options.setCapability(
                "adbExecTimeout",
                120000
        );

        options.setCapability(
                "uiautomator2ServerLaunchTimeout",
                120000
        );

        options.setCapability(
                "newCommandTimeout",
                300
        );

        driver =
                new AndroidDriver(
                        new URL(APPIUM_URL),
                        options
                );

        driver.manage()
                .timeouts()
                .implicitlyWait(
                        Duration.ofSeconds(10)
                );

        System.out.println(
                "Application Started Successfully"
        );
    }

    // CLEAR APP DATA
    public static void clearAppData() {

        try {

            Process process =
                    Runtime.getRuntime().exec(
                            ADB_PATH
                                    + " shell pm clear "
                                    + APP_PACKAGE
                    );

            process.waitFor();

            System.out.println(
                    "App Data Cleared Successfully"
            );

            Thread.sleep(3000);

        } catch (Exception e) {

            System.out.println(
                    "Failed To Clear App Data"
            );

            e.printStackTrace();
        }
    }

    // HANDLE ONBOARDING
    public static void handleOnboarding() {

        try {

            Thread.sleep(3000);

            // CONTINUE BUTTON 1
            List<WebElement> continueBtn1 =
                    driver.findElements(
                            By.xpath(
                                    "//*[contains(@text,'Continue')]"
                            )
                    );

            if (continueBtn1.size() > 0) {

                continueBtn1.get(0).click();

                System.out.println(
                        "Continue Button 1 Clicked"
                );

                Thread.sleep(2000);
            }

            // CONTINUE BUTTON 2
            List<WebElement> continueBtn2 =
                    driver.findElements(
                            By.xpath(
                                    "//*[contains(@text,'Continue')]"
                            )
                    );

            if (continueBtn2.size() > 0) {

                continueBtn2.get(0).click();

                System.out.println(
                        "Continue Button 2 Clicked"
                );

                Thread.sleep(3000);
            }

            // SUBSCRIPTION SCREEN
            List<WebElement> closeSubscription =
                    driver.findElements(
                            By.xpath(
                                    "//android.widget.ImageView"
                            )
                    );

            if (closeSubscription.size() > 0) {

                closeSubscription.get(0).click();

                System.out.println(
                        "Subscription Closed"
                );

                Thread.sleep(3000);
            }

        } catch (Exception e) {

            System.out.println(
                    "Onboarding Flow Not Found"
            );
        }
    }

    // CHECK HOME SCREEN
    public static boolean checkHomeScreen() {

        try {

            List<WebElement> chats =
                    driver.findElements(
                            By.xpath(
                                    "//*[contains(@text,'Chats')]"
                            )
                    );

            if (chats.size() > 0) {

                System.out.println(
                        "Home Screen Detected"
                );

                return true;
            }

            List<WebElement> home =
                    driver.findElements(
                            By.xpath(
                                    "//*[contains(@text,'Home')]"
                            )
                    );

            if (home.size() > 0) {

                System.out.println(
                        "Home Screen Detected"
                );

                return true;
            }

            System.out.println(
                    "Home Screen Not Found"
            );

            return false;

        } catch (Exception e) {

            return false;
        }
    }

    // CLOSE DRIVER
    public static void closeDriver() {

        try {

            if (driver != null) {

                driver.quit();

                System.out.println(
                        "Driver Closed Successfully"
                );
            }

        } catch (Exception e) {

            System.out.println(
                    "Driver Already Closed"
            );
        }
    }
}