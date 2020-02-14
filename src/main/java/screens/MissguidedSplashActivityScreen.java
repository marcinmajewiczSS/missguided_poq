package screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class MissguidedSplashActivityScreen extends BaseScreen{

    public MissguidedSplashActivityScreen(AndroidDriver driver) {
        super(driver);
    }

    //Mobile Elements
    String get_started_btn = "com.poqstudio.app.platform.missguided:id/onboarding_activity_get_started_btn";

    public boolean isPageDisplayed () {
        waitVisibility(By.id(get_started_btn));
        return isElementPresent(By.id(get_started_btn));
    }

    public void clickGetStartedBtn ()  {
        waitAndClick(By.id(get_started_btn));
    }



}
