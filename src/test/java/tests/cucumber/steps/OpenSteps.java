package tests.cucumber.steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.net.MalformedURLException;
import screens.LoginRegisterScreen;
import screens.MissguidedSplashActivityScreen;
import screens.ShopScreen;
import tests.ThreadLocalDriver;

public class OpenSteps extends BaseSteps{
    //Instantiations
     MissguidedSplashActivityScreen missguidedSplashActivityScreen ;
     ShopScreen shopScreen ;
     LoginRegisterScreen loginRegisterScreen ;

    //Screen Classes Initialization
    @Before
    public void setupLoginSteps () throws MalformedURLException {
        System.out.println("Cucumber Before-login-test-cucumber");
        setupCucumber();
        missguidedSplashActivityScreen = new MissguidedSplashActivityScreen(ThreadLocalDriver.getTLDriver());
        shopScreen = new ShopScreen(ThreadLocalDriver.getTLDriver());
        loginRegisterScreen = new LoginRegisterScreen(ThreadLocalDriver.getTLDriver());

    }

    @Given("^Misguided app installed$")
    public void misguidedAppInstalled() {
        System.out.println("Cucumber Given");

    }

    @When("^Navigate to shop$")
    public void navigateToShop() {
        if(missguidedSplashActivityScreen.isPageDisplayed())
            missguidedSplashActivityScreen.clickGetStartedBtn();
    }

    @When("^Select \"([^\"]*)\" category$")
    public void selectCategory(String category)  {
        shopScreen.clickMenuBtn();
        shopScreen.selectFromMenuListByText(category);
    }

    @And("^Select the (\\d+) item displayed$")
    public void selectTheItemDisplayed(int no) {
        shopScreen.selectItemFromListByIdAndNumber(no);
    }

    @And("^Add to Bag$")
    public void addToBag() {
        shopScreen.clickAddToBag();
    }

    @And("^Select Size \"([^\"]*)\"$")
    public void selectSize(String size)  {
        shopScreen.selectSize(size);
    }

    @And("^Select the Bag$")
    public void selectTheBag() {
        shopScreen.clickBag();
    }

    @And("^Select Pay$")
    public void selectPay() {
        shopScreen.clickCheckout();
    }

    @Then("^Sign In and Register screen is displayed$")
    public void signInAndRegisterScreenIsDisplayed() {
        assert loginRegisterScreen.isPageDisplayed();
    }

    @After
    public synchronized void teardown () {
        // teardown();
        System.out.println("teardown'a girdi!");
    }
}