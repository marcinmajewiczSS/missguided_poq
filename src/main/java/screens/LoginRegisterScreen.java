package screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginRegisterScreen extends BaseScreen{

    public LoginRegisterScreen(AndroidDriver driver) {
        super(driver);
    }

    //Mobile Elements
    String loginRegisterViewId = "com.poqstudio.app.platform.missguided:id/content_block_login_view_image_view";

    public boolean isPageDisplayed () {
        waitVisibility(By.id(loginRegisterViewId));
        return isElementPresent(By.id(loginRegisterViewId));
    }




}
