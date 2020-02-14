# missguided_poq
API import poq.postman_collection.json

FE:
eneter your user/pass for browserstack AND app <hash>
in src/test/java/tests/BaseTest.java
run TestNG.xml (multiple devices)

Unfortunately there is no way to use Appium with iOS application installed locally. Here’s some explanations:
1. Appium is running locally on machine and we need to use iOS Simulator.
2. iOS simulator does not have Apple store application that’s why we can’t install it this way.
3. Nether Appstore nor any 3rd party website (like for android) does not provide .ipa binary that could be installed on simulator and signed as development.

So to demonstrate how Appium works with iOS we really need more data to create reusable and stable demo.

Also, we want to mention that it’s a really good practice to use native automation testing frameworks for mobile apps that are developed natively: Espresso for Android and XCUITest for iOS. It requires a bit more effort to support but provides more flexibility and a lot of useful native instruments to create those tests.
We can compare both ways Appium and Native to provide you with more details.
