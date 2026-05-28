package automationtest;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class FirstAutomate {

    public static void main(String[] args) {

        AndroidDriver driver = null;

        try {

            DesiredCapabilities caps =
                    new DesiredCapabilities();

            // =====================================
            // PLATFORM CONFIG
            // =====================================

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

            // =====================================
            // APP CONFIG
            // =====================================

            caps.setCapability(
                    "appium:appPackage",
                    "com.web.messenger.dual.chat"
            );

            // Let Appium auto detect activity

            caps.setCapability(
                    "appium:noReset",
                    false
            );

            caps.setCapability(
                    "appium:autoGrantPermissions",
                    true
            );

            // =====================================
            // STABILITY FIXES
            // =====================================

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

            Thread.sleep(7000);

            // =====================================
            // STEP 1 : LANGUAGE SELECT
            // =====================================

            List<WebElement> languageSelect =
                    driver.findElements(
                            By.xpath(
                                    "(//android.widget.ImageView[@resource-id='com.web.messenger.dual.chat:id/ivSelect'])[1]"
                            )
                    );

            if (languageSelect.size() > 0) {

                WebElement lang =
                        languageSelect.get(0);

                int centerX =
                        lang.getRect().x +
                        (lang.getRect().width / 2);

                int centerY =
                        lang.getRect().y +
                        (lang.getRect().height / 2);

                driver.executeScript(
                        "mobile: clickGesture",
                        Map.of(
                                "x", centerX,
                                "y", centerY
                        )
                );

                System.out.println(
                        "Language Selected"
                );

                Thread.sleep(3000);

            } else {

                System.out.println(
                        "Language Option Not Found"
                );
            }

            // =====================================
            // STEP 2 : DONE BUTTON
            // =====================================

            List<WebElement> doneBtn =
                    driver.findElements(
                            By.xpath(
                                    "//android.widget.ImageView[@resource-id='com.web.messenger.dual.chat:id/btnDone']"
                            )
                    );

            if (doneBtn.size() > 0) {

                WebElement done =
                        doneBtn.get(0);

                int x =
                        done.getRect().x +
                        (done.getRect().width / 2);

                int y =
                        done.getRect().y +
                        (done.getRect().height / 2);

                driver.executeScript(
                        "mobile: clickGesture",
                        Map.of(
                                "x", x,
                                "y", y
                        )
                );

                System.out.println(
                        "Done Button Clicked"
                );

                Thread.sleep(3000);
            }

            // =====================================
            // STEP 3 : NEXT BUTTONS
            // =====================================

            for (int i = 1; i <= 3; i++) {

                List<WebElement> nextBtns =
                        driver.findElements(
                                By.xpath(
                                        "//android.widget.Button[@resource-id='com.web.messenger.dual.chat:id/lvBtnNext']"
                                )
                        );

                if (nextBtns.size() > 0) {

                    nextBtns.get(0).click();

                    System.out.println(
                            "Next Button " + i + " Clicked"
                    );

                    Thread.sleep(3000);
                }
            }

            // =====================================
            // STEP 4 : PHONE NUMBER INPUT
            // =====================================

            List<WebElement> phoneInput =
                    driver.findElements(
                            By.xpath(
                                    "//android.widget.EditText[@resource-id='com.web.messenger.dual.chat:id/etPhoneNumber']"
                            )
                    );

            if (phoneInput.size() > 0) {

                phoneInput.get(0).click();

                Thread.sleep(1000);

                phoneInput.get(0)
                        .sendKeys("9876543210");

                System.out.println(
                        "Phone Number Entered"
                );

                Thread.sleep(2000);

                // =====================================
                // HIDE KEYBOARD
                // =====================================

                try {

                    driver.hideKeyboard();

                    System.out.println(
                            "Keyboard Hidden"
                    );

                } catch (Exception e) {

                    System.out.println(
                            "Keyboard Already Hidden"
                    );
                }

                Thread.sleep(2000);

                // =====================================
                // TAP OUTSIDE
                // =====================================

                try {

                    List<WebElement> outside =
                            driver.findElements(
                                    By.xpath(
                                            "(//android.widget.FrameLayout[@resource-id='com.web.messenger.dual.chat:id/nav_host_fragment_welcome'])[2]/android.view.ViewGroup"
                                    )
                            );

                    if (outside.size() > 0) {

                        outside.get(0).click();

                        System.out.println(
                                "Tapped Outside Keyboard"
                        );
                    }

                } catch (Exception e) {

                    System.out.println(
                            "Outside Area Not Found"
                    );
                }

                Thread.sleep(3000);
            }

            // =====================================
            // STEP 5 : CONTINUE BUTTON
            // =====================================

            List<WebElement> continueBtn =
                    driver.findElements(
                            By.xpath(
                                    "//android.widget.Button[@resource-id='com.web.messenger.dual.chat:id/btnContinue']"
                            )
                    );

            if (continueBtn.size() > 0) {

                continueBtn.get(0).click();

                System.out.println(
                        "Continue Button Clicked"
                );

                Thread.sleep(5000);

            } else {

                System.out.println(
                        "Continue Button NOT Found"
                );
            }

            // =====================================
            // STEP 6 : FIRST CLOSE BUTTON
            // =====================================

            List<WebElement> closeBtn1 =
                    driver.findElements(
                            By.xpath(
                                    "//android.widget.ImageView[@resource-id='com.web.messenger.dual.chat:id/ivClose']"
                            )
                    );

            if (closeBtn1.size() > 0) {

                closeBtn1.get(0).click();

                System.out.println(
                        "First Close Clicked"
                );

                Thread.sleep(3000);
            }

            // =====================================
            // STEP 7 : HIDE OPTIONS
            // =====================================

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

                Thread.sleep(3000);
            }

            // =====================================
            // STEP 8 : SECOND CLOSE BUTTON
            // =====================================

            List<WebElement> closeBtn2 =
                    driver.findElements(
                            By.xpath(
                                    "//android.widget.ImageView[@resource-id='com.web.messenger.dual.chat:id/ivClose']"
                            )
                    );

            if (closeBtn2.size() > 0) {

                closeBtn2.get(0).click();

                System.out.println(
                        "Second Close Clicked"
                );

                Thread.sleep(3000);
            }

            // =====================================
            // STEP 9 : ALLOW PERMISSION
            // =====================================

            List<WebElement> allowBtn =
                    driver.findElements(
                            By.xpath(
                                    "//android.widget.Button[@resource-id='com.android.permissioncontroller:id/permission_allow_button']"
                            )
                    );

            if (allowBtn.size() > 0) {

                allowBtn.get(0).click();

                System.out.println(
                        "Permission Allowed"
                );

                Thread.sleep(5000);
            }

            // =====================================
            // FINAL
            // =====================================

            System.out.println(
                    "Reached Home Screen Successfully"
            );

            Thread.sleep(300000);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                if (driver != null
                        && driver.getSessionId() != null) {

                    driver.quit();
                }

            } catch (Exception e) {

                System.out.println(
                        "Driver Session Already Ended"
                );
            }

                driver.quit();
            }
        }
    }