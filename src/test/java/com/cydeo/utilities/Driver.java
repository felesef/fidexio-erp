package com.cydeo.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Driver {

    /*
    Creating a private constructor, so we are closing
    access to the object of this class from outside the class
     */
    private Driver(){}

    /*
    We make WebDriver private, because we want to close access from outside the class.
    We make it static because we will use it in a static method.
     */
    //private static WebDriver driver; // value is null by default

    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();

    /*
    Create a re-usable utility method which will return same driver instance when we call it
     */
    public static WebDriver getDriver(){

        if (driverPool.get() == null){

            /*
            We read our browserType from configuration.properties.
            This way, we can control which browser is opened from outside our code, from configuration.properties.
             */
            String browserType = ConfigurationReader.getProperty("browser");


            /*
                Depending on the browserType that will be return from configuration.properties file
                switch statement will determine the case, and open the matching browser
            */
            switch (browserType){
                case "chrome":
//                    ChromeOptions chromeOptions = new ChromeOptions();
//                    chromeOptions.addArguments("--disable-gpu");
//                    chromeOptions.addArguments("--disable-extensions");
//                    chromeOptions.addArguments("--disable-popup-blocking");
//                    chromeOptions.addArguments("--disable-infobar");
////                    chromeOptions.addArguments("--window-size=1440,900");
//                    chromeOptions.addArguments("--disable-dev-shm-usage");
//                    chromeOptions.addArguments("--allow-insecure-localhost");
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;
//                case "chrome-headless":
//                    WebDriverManager.chromedriver().setup();
//                    driverPool.set( new ChromeDriver(new ChromeOptions().setHeadless(true)));
//                    break;
                case "chrome_notification_handled":
                    WebDriverManager.chromedriver().setup();
                    // Create a map to store  preferences
                    Map<String, Object> prefs = new HashMap<String, Object>();
                    // add key and value to map as follow to switch off browser notification
                    // Pass the argument 1 to allow and 2 to block
                    // 1-Allow, 2-Block, 0-default
                    prefs.put("profile.default_content_setting_values.notifications", 2);
                    //Create an instance of ChromeOptions
                    ChromeOptions options = new ChromeOptions();
                    // set ExperimentalOption - prefs
                    options.setExperimentalOption("prefs", prefs);
                    //Now Pass ChromeOptions instance to ChromeDriver Constructor to initialize chrome driver
                    // which will switch off this browser notification on the chrome browser
                    driverPool.set(new ChromeDriver(options));
                    break;
                case "remote-chrome":
                    try {
                        // assign your grid server address
                        String gridAddress = ConfigurationReader.getProperty("gridadress");
                        URL url = new URL("http://" + gridAddress + ":4444/wd/hub");
                        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setBrowserName("chrome");
                        driverPool.set(new RemoteWebDriver(url, desiredCapabilities));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                    break;

                case "chrome-headless":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--disable-gpu");
                    chromeOptions.addArguments("--window-size=1440,900");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("--allow-insecure-localhost");
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver(chromeOptions));
                    break;

            }
        }

        return driverPool.get();

    }

    /*
    This method will make sure our driver value is always null after using quit() method
     */
    public static void closeDriver(){
        if (driverPool.get() != null){
            driverPool.get().quit(); // this line will terminate the existing session. value will not even be null
            driverPool.remove();
        }
    }

}
