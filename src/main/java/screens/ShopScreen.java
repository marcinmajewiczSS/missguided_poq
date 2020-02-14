package screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class ShopScreen extends BaseScreen{

    public ShopScreen(AndroidDriver driver) {
        super(driver);
    }

    //Mobile Elements
    String top_missguided_image = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.ImageView";
    String mainMenuId = "Open navigation drawer";
    String menuListItemAccessibilityId = "android.widget.TextView";
    //String itemsListToBuyId = "component_progress_image_view_image_view";
    String itemsListToBuyId = "product_item_view_title";
    String itemTextId = "product_item_view_title";
    String addToBagId = "product_info_section_add_to_bag_button";
    String selectSizeResourceId = "com.poqstudio.app.platform.missguided:id/numberpicker_input";
    String bagResourceId = "com.poqstudio.app.platform.missguided:id/action_bag";
    String checkoutId = "com.poqstudio.app.platform.missguided:id/activity_modular_bag_checkout_button";
    String checkoutClass = "android.widget.Button";

    String wholeItemXpath = "//androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout/android.widget.LinearLayout";

    public void clickMenuBtn ()  {
        waitAndClickByAccessibilityId(mainMenuId);
    }

    public void selectFromMenuListByText (String text)  {
        waitScrollAndClickByClassNameAndText(menuListItemAccessibilityId, text);
    }

    public void selectItemFromListByIdAndNumber (Integer no)  {
        waitScrollAndClickItemListByIdAndNo(itemsListToBuyId,no);
        //waitScrollAndClickItemListByIdAndNoSize(itemsListToBuyId, no, wholeItemXpath);
    }

    public void clickAddToBag ()  {
        waitScrollAndClickItemListById("id", addToBagId);
    }

    public void selectSize (String size)  {
        waitScrollAndClickByResourceIdAndText(selectSizeResourceId, size);
    }

    public void clickBag ()  {
        waitAndClickById(bagResourceId);
    }

    public void clickCheckout ()  {
        sleep(500);
        waitAndClickById(checkoutId);
        //waitScrollAndClickByClassNameAndText(checkoutClass, "CHECKOUT");
    }







}
