package tests.cucumber.tests;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.BaseTest;

@CucumberOptions(
        monochrome = true,
        tags = "@Missguided",
        features = "src/test/java/tests/cucumber/features/",

        glue = "tests.cucumber.steps" ,
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/CucumberTestReport.json",
                "junit:target/cucumber-reports/Cucumber.xml",
                "rerun:target/cucumber-reports/rerun.txt",
        }
)
public class RunCucumberFeatures extends BaseTest {

    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        System.out.println("Cucumber Test Class Before");
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        System.out.println("Cucumber Test Class Inside Test");
        System.out.println(cucumberFeature.getCucumberFeature());
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider
    public Object[][] features() {
        System.out.println("Data Provider test Class");
        return testNGCucumberRunner.provideFeatures();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
    }
}