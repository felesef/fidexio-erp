@FIDE-968
Feature: Fidexio Logout feature

  Background: User in "url" page
    Given user is on the login page of fidexio page

  Scenario Outline: Positive Logout Scenarios
    When user enters username "<Username>"
    And user enters password "<Password>"
    And user clicks to login button
    And user sees "<UserID>" in the title
    And user clicks UserID button
    And user clicks to Logout button
    And user clicks back arrow button
    Then  user sees "Odoo Session Expired" alert

    @Positive
    Examples: Search values we are going to be using in this scenario is as below table
      | Username                | Password     | UserID         |
      | salesmanager35@info.com | salesmanager | salesmanager35 |
      | salesmanager25@info.com | salesmanager | salesmanager25 |
      | salesmanager55@info.com | salesmanager | salesmanager55 |
      | posmanager35@info.com   | posmanager   | posmanager35   |
      | posmanager75@info.com   | posmanager   | posmanager75   |
      | posmanager101@info.com  | posmanager   | posmanager101  |
