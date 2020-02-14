package tests;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import java.io.IOException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import screens.LoginRegisterScreen;
import screens.MissguidedSplashActivityScreen;
import screens.ShopScreen;

public class BaseTest {

    public WebDriverWait wait;
    // eneter your user/pass for browserstack AND app <hashed>
    protected static String userName = "EnterYourPasswordForBrowserStack";
    protected static String accessKey = "EnterYourPasswordForBrowserStack";

    //Base Screens for all cases
    protected MissguidedSplashActivityScreen splashScreen = null;
    //protected MissguidedSplashActivityScreen missguidedSplashActivityScreen = null;
    protected ShopScreen shopScreen = null;
    protected LoginRegisterScreen loginRegisterScreen = null;

    @BeforeMethod
    @Parameters({"deviceName", "platformVersion"})
    public void setup (String deviceName, String platformVersion) throws IOException {
        System.out.println("TestNG Before");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("device", deviceName);
        caps.setCapability("os_version", platformVersion);
        caps.setCapability("project", "My First Project");
        caps.setCapability("build", "My First Build");
        caps.setCapability("name", "Bstack-[Java] Sample Test");
        caps.setCapability("app", "bs://ab5a552c0be15753dc85eb4f11fa2eeea5e0cd0d");
        caps.setCapability("browserstack.networkLogs", "true");
        caps.setCapability("browserstack.video", "true");

        caps.setCapability("appPackage", "com.poqstudio.app.platform.missguided");
        caps.setCapability("appActivity", "com.poqstudio.app.client.view.splash.MissguidedSplashActivity");


        ThreadLocalDriver.setTLDriver(new AndroidDriver (new URL("https://" + userName + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub"),caps));
        wait = new WebDriverWait(ThreadLocalDriver.getTLDriver(), 10);

        //Base Screen Initialization
        splashScreen = new MissguidedSplashActivityScreen(ThreadLocalDriver.getTLDriver());
        shopScreen = new ShopScreen(ThreadLocalDriver.getTLDriver());
        loginRegisterScreen = new LoginRegisterScreen(ThreadLocalDriver.getTLDriver());
    }


    @AfterMethod
    public synchronized void teardown(){
        ThreadLocalDriver.getTLDriver().quit();
    }
}
