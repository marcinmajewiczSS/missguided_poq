package tests.cucumber.steps;

import java.net.MalformedURLException;
import org.openqa.selenium.support.ui.WebDriverWait;
import screens.LoginRegisterScreen;
import screens.MissguidedSplashActivityScreen;
import screens.ShopScreen;

import tests.ThreadLocalDriver;

public class BaseSteps {

    protected MissguidedSplashActivityScreen missguidedSplashActivityScreen = null;
    protected ShopScreen shopScreen = null;
    protected LoginRegisterScreen loginRegisterScreen = null;
    protected WebDriverWait wait;


    //Screen Classes Initialization
    protected void setupCucumber () throws MalformedURLException {
        System.out.println("Cucumber Base Test Before-login-test-cucumber");

        wait = new WebDriverWait(ThreadLocalDriver.getTLDriver(), 10);
        missguidedSplashActivityScreen = new  MissguidedSplashActivityScreen(ThreadLocalDriver.getTLDriver()) ;
        shopScreen = new ShopScreen(ThreadLocalDriver.getTLDriver()) ;
        loginRegisterScreen = new LoginRegisterScreen(ThreadLocalDriver.getTLDriver()) ;

    }

    protected void teardown(){
        ThreadLocalDriver.getTLDriver().quit();
    }
}