@Missguided
Feature: Missguided Open App Feature

  Scenario: POQ Missguided app successfully
    Given Misguided app installed
    And Navigate to shop
    When Select "Clearance" category
    And Select the 6 item displayed
    And Add to Bag
    And Select Size "10"
    #7. Allow Notifications // did not see this
    And Select the Bag
    And Select Pay
    Then Sign In and Register screen is displayed
  #Download the misguided app from the playstore
