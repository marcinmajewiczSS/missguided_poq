package screens;

import static io.appium.java_client.touch.offset.PointOption.point;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import java.util.HashSet;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import org.testng.Assert;

public class BaseScreen {

    protected AndroidDriver driver;
    protected WebDriverWait wait;

    public BaseScreen (AndroidDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }

    String itemsCount = "com.poqstudio.app.platform.missguided:id/plp_items_count";
    String itemTextId = "product_item_view_title";


    protected void waitAndClick (By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by)).click();
    }

    protected void waitAndClickByAccessibilityId (String locator) {
        sleep(500);
        wait.until(ExpectedConditions.visibilityOf(driver.findElementByAccessibilityId(locator))).click();
    }

    protected void waitAndClickById (String locator) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElementById(locator))).click();
    }

    protected void waitAndClickByClassName (String locator) {
        wait.until(ExpectedConditions.visibilityOf(driver.findElementByClassName(locator))).click();
    }

    protected void waitScrollAndClickByClassNameAndText(String locator, String text) {
        String selector = "new UiSelector().text(\""+text+"\").className(\""+locator+"\")";
        //scroll
        MobileElement element = (MobileElement) driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"))");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(MobileBy.AndroidUIAutomator(selector)))).click();
    }

    protected void waitScrollAndClickByResourceIdAndText(String locator, String text) {
        String selector = "new UiSelector().text(\""+text+"\").resourceId(\""+locator+"\")";
        //scroll
        MobileElement element = (MobileElement) driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"))");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(MobileBy.AndroidUIAutomator(selector)))).click();
    }

    protected void waitScrollAndClickItemListById (String by, String locator) {
        scrollToElement(by,locator);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(by,locator))).click();
    }

    //disappear element scroll
    protected void waitScrollAndClickItemListByIdAndNo (String locator, Integer number) {
        List<AndroidElement> elementList = (List<AndroidElement>) driver.findElements("id", locator);
        Integer visibleElements = elementList.size();
        Integer totalItems = getItemsCount();
        if (number > totalItems)
            number = totalItems;
    while ((number) > visibleElements)
        {
            scrollTillDisappearElement(elementList.get(visibleElements-1));
            elementList = (List<AndroidElement>) driver.findElements("id", locator);
            visibleElements = elementList.size();
            number = number-visibleElements;
        }
        wait.until(ExpectedConditions.visibilityOf(elementList.get(number-1))).click();
    }

    protected void waitScrollAndClickItemListByIdAndNoSize (String locator, Integer number, String sizeLocator) {
        List<AndroidElement> elementList = (List<AndroidElement>) driver.findElements("id", locator);
        Integer visibleElements = elementList.size();
        Integer sizeToScroll = getHightOfElement(sizeLocator);
        System.out.println("sizeToScroll : "+ sizeToScroll );
        Integer totalItems = getItemsCount();
        if (number > totalItems)
            number = totalItems;
        while ((number) > visibleElements)
        {
            scrollBySizeElement(sizeToScroll);
            elementList = (List<AndroidElement>) driver.findElements("id", locator);
            visibleElements = elementList.size();
            number = number-visibleElements;
        }
        wait.until(ExpectedConditions.visibilityOf(elementList.get(number-1))).click();
    }

    protected void ScrollOnce() {
        Dimension size = driver.manage().window().getSize();
        int anchor = (int) (size.width / 2);
        // Swipe up to scroll down
        int startPoint = (int) (size.height - 1);
        int endPoint = 1;
                new TouchAction(driver)
                        .longPress(point(anchor, startPoint))
                        .moveTo(point(anchor, endPoint))
                        .release()
                        .perform();
    }

    protected Set<String> getTextFromItemList(List<AndroidElement> elementList){
        Set<String> newSetOfText = new HashSet<String>();
        for (AndroidElement elem:elementList){
            newSetOfText.add(elem.getText());
        }
        return newSetOfText;
    }


    protected Integer getItemsCount(){
        WebElement itemCountElement = driver.findElement(By.id(itemsCount));
        String[] temp = itemCountElement.getText().split(" ");
        return Integer.valueOf(temp[0]);
    }

    protected AndroidElement scrollToElement(String by, String using) {
        AndroidElement element = null;
        int numberOfTimes = 10;
        Dimension size = driver.manage().window().getSize();
        int anchor = (int) (size.width / 2);
        // Swipe up to scroll down
        int startPoint = (int) (size.height - 10);
        int endPoint = 10;

        for (int i = 0; i < numberOfTimes; i++) {
            try {
                new TouchAction(driver)
                        .longPress(point(anchor, startPoint))
                        .moveTo(point(anchor, endPoint))
                        .release()
                        .perform();
                element = (AndroidElement) driver.findElement(by, using);
                i = numberOfTimes;
            } catch (NoSuchElementException ex) {
                System.out.println(String.format("Element not available. Scrolling (%s) times...", i + 1));
            }
        }
        return element;
    }

    protected boolean scrollTillDisappearElement(AndroidElement elementList) {
        boolean isElementDisplayed = isElementPresentAndDisplayed(elementList);
        int numberOfTimes = 10;
        Dimension size = driver.manage().window().getSize();
        int anchor = (int) (size.width / 2);
        // Swipe up to scroll down
        int startPoint = (int) (size.height - 10);
        int endPoint = (int) (size.height*0.7) + 10;
        if(!isElementDisplayed)
            return false;
        for (int i = 0; i < numberOfTimes; i++) {
            try {
                new TouchAction(driver)
                        .longPress(point(anchor, startPoint))
                        .moveTo(point(anchor, endPoint))
                        .release()
                        .perform();
                isElementDisplayed = isElementPresentAndDisplayed(elementList);

                if(!isElementDisplayed)
                    break;

            } catch (NoSuchElementException ex) {
                System.out.println(String.format("Element still available. Scrolling (%s) times...", i + 1));
            }
        }
        return isElementDisplayed;
    }

    protected void scrollBySizeElement(Integer scrollSize) {
        Dimension size = driver.manage().window().getSize();
        int anchor = (int) (size.width / 2);
        // Swipe up to scroll down
        int startPoint = (int) (size.height - 10);
        int endPoint = size.height - scrollSize - 12;

                new TouchAction(driver)
                        .longPress(point(anchor, startPoint))
                        .moveTo(point(anchor, endPoint))
                        .release()
                        .perform();

    }

    protected int getHightOfElement(String locator){
        return driver.findElement(By.xpath(locator)).getSize().height;
    }
    protected static boolean isElementPresentAndDisplayed( final AndroidElement element) {
        try {
            return element.isDisplayed();
        } catch (StaleElementReferenceException e) {
            return false;
        }
    }

    protected void click (By by) {
        waitVisibility(by);
        driver.findElement(by).click();
    }

    protected void hideKeyboard(){
        driver.navigate().back();
    }

    protected boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected List<WebElement> waitAndFindElements (By by) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    protected WebElement waitAndFindElement (By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected String getText(By by) {
        return waitAndFindElement(by).getText();
    }

    protected void sendText (By by, String text) {
        waitAndFindElement(by).sendKeys(text);
    }

    protected void assertEquals (String actual, String expected) {
        Assert.assertEquals(actual,expected, "Two texts are not equal!" + "Actual: " + actual + " Expected: " + expected);
    }

    protected void waitVisibility (By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }


    protected void sleep (long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}