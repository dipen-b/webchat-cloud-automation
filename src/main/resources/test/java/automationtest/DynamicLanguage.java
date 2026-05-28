package automationtest;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;

public class DynamicLanguage {

    public static void main(String[] args) {

        AndroidDriver driver = null;

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
                    "appium:udid",
                    "emulator-5554"
            );

            caps.setCapability(
                    "appium:appPackage",
                    "com.web.messenger.dual.chat"
            );

            caps.setCapability(
                    "appium:noReset",
                    false
            );

            caps.setCapability(
                    "appium:autoGrantPermissions",
                    true
            );

            caps.setCapability(
                    "appium:newCommandTimeout",
                    300
            );

            // =========================================
            // DRIVER START
            // =========================================

            driver =
                    new AndroidDriver(
                            new URL(
                                    "http://127.0.0.1:4723/"
                            ),
                            caps
                    );

            driver.manage()
                    .timeouts()
                    .implicitlyWait(
                            Duration.ofSeconds(10)
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

            Thread.sleep(8000);

            // =========================================
            // STORE ALL 16 LANGUAGES
            // =========================================

            LinkedHashSet<String> allLanguages =
                    new LinkedHashSet<>();

            boolean endReached = false;

            while (!endReached) {

                // =====================================
                // GET VISIBLE LANGUAGES
                // =====================================

                List<WebElement> languages =
                        driver.findElements(
                                By.xpath(
                                        "//android.widget.TextView[@resource-id='com.web.messenger.dual.chat:id/tvLanguageName']"
                                )
                        );

                // =====================================
                // STORE LANGUAGE NAMES
                // =====================================

                for (WebElement lang : languages) {

                    String langName =
                            lang.getText().trim();

                    if (!langName.isEmpty()) {

                        allLanguages.add(langName);
                    }
                }

                // =====================================
                // PRINT CURRENT COUNT
                // =====================================

                System.out.println(
                        "TOTAL LANGUAGES CAPTURED : "
                                + allLanguages.size()
                );

                // =====================================
                // STOP IF 16 LANGUAGES FOUND
                // =====================================

                if (allLanguages.size() >= 16) {

                    break;
                }

                // =====================================
                // SCROLL DOWN
                // =====================================

                HashMap<String, Object> scroll =
                        new HashMap<>();

                scroll.put("left", 500);
                scroll.put("top", 1600);
                scroll.put("width", 300);
                scroll.put("height", 1000);
                scroll.put("direction", "down");
                scroll.put("percent", 0.9);

                Boolean canScroll =
                        (Boolean) driver.executeScript(
                                "mobile: scrollGesture",
                                scroll
                        );

                Thread.sleep(2500);

                if (!canScroll) {

                    endReached = true;
                }
            }

            // =========================================
            // FINAL LANGUAGE LIST
            // =========================================

            List<String> finalLanguages =
                    new ArrayList<>(allLanguages);

            System.out.println(
                    "====================================="
            );

            System.out.println(
                    "FINAL LANGUAGE COUNT : "
                            + finalLanguages.size()
            );

            System.out.println(
                    "====================================="
            );

            // =========================================
            // PRINT ALL LANGUAGES
            // =========================================

            for (String lang : finalLanguages) {

                System.out.println(
                        "LANGUAGE : " + lang
                );
            }

            // =========================================
            // RANDOM LANGUAGE SELECTION
            // =========================================

            Random random =
                    new Random();

            int randomIndex =
                    random.nextInt(
                            finalLanguages.size()
                    );

            String selectedLanguage =
                    finalLanguages.get(
                            randomIndex
                    );

            System.out.println(
                    "====================================="
            );

            System.out.println(
                    "RANDOM LANGUAGE SELECTED : "
                            + selectedLanguage
            );

            System.out.println(
                    "====================================="
            );

            // =========================================
            // GO BACK TO TOP
            // =========================================

            for (int i = 0; i < 5; i++) {

                HashMap<String, Object> scrollUp =
                        new HashMap<>();

                scrollUp.put("left", 500);
                scrollUp.put("top", 800);
                scrollUp.put("width", 300);
                scrollUp.put("height", 1200);
                scrollUp.put("direction", "up");
                scrollUp.put("percent", 1.0);

                driver.executeScript(
                        "mobile: scrollGesture",
                        scrollUp
                );

                Thread.sleep(1000);
            }

            // =========================================
            // FIND RANDOM LANGUAGE
            // =========================================

            boolean languageSelected = false;

            while (!languageSelected) {

                try {

                    WebElement selectedLang =
                            driver.findElement(
                                    By.xpath(
                                            "//android.widget.TextView[@text='"
                                                    + selectedLanguage +
                                                    "']"
                                    )
                            );

                    // =================================
                    // CLICK LANGUAGE
                    // =================================

                    selectedLang.click();

                    Thread.sleep(2000);

                    // =================================
                    // VERIFY SELECTED LANGUAGE
                    // =================================

                    WebElement verifyLanguage =
                            driver.findElement(
                                    By.xpath(
                                            "//android.widget.TextView[@text='"
                                                    + selectedLanguage +
                                                    "']"
                                    )
                            );

                    System.out.println(
                            "SELECTED LANGUAGE VERIFIED : "
                                    + verifyLanguage.getText()
                    );

                    languageSelected = true;

                } catch (Exception e) {

                    // =============================
                    // SCROLL DOWN AGAIN
                    // =============================

                    HashMap<String, Object> scrollDown =
                            new HashMap<>();

                    scrollDown.put("left", 500);
                    scrollDown.put("top", 1600);
                    scrollDown.put("width", 300);
                    scrollDown.put("height", 1000);
                    scrollDown.put("direction", "down");
                    scrollDown.put("percent", 0.8);

                    Boolean moreScroll =
                            (Boolean) driver.executeScript(
                                    "mobile: scrollGesture",
                                    scrollDown
                            );

                    Thread.sleep(2000);

                    if (!moreScroll) {

                        break;
                    }
                }
            }

            // =========================================
            // CLICK DONE BUTTON
            // =========================================

            List<WebElement> doneBtn =
                    driver.findElements(
                            By.xpath(
                                    "//android.widget.ImageView[@resource-id='com.web.messenger.dual.chat:id/btnDone']"
                            )
                    );

            if (doneBtn.size() > 0) {

                doneBtn.get(0).click();

                System.out.println(
                        "DONE BUTTON CLICKED"
                );

            } else {

                System.out.println(
                        "DONE BUTTON NOT FOUND"
                );
            }

            Thread.sleep(5000);

            // =========================================
            // VERIFY NEXT SCREEN
            // =========================================

            List<WebElement> continueBtn =
                    driver.findElements(
                            By.xpath(
                                    "//android.widget.ImageView[@resource-id='com.web.messenger.dual.chat:id/imgContinue']"
                            )
                    );

            if (continueBtn.size() > 0) {

                System.out.println(
                        "NEXT SCREEN OPENED SUCCESSFULLY"
                );

            } else {

                System.out.println(
                        "FAILED TO OPEN NEXT SCREEN"
                );
            }

            Thread.sleep(10000);

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                if (driver != null) {

                    driver.quit();

                    System.out.println(
                            "DRIVER CLOSED SUCCESSFULLY"
                    );
                }

            } catch (Exception e) {

                System.out.println(
                        "DRIVER ALREADY CLOSED"
                );
            }
        }
    }
}