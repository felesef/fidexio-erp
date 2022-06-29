@FIDE-967
Feature: Fidexio Login feature

  Background: User in "url" page
    Given user is on the login page of fidexio page

#  Scenario: Positive login scenario1
#    When user enters username "salesmanager35@info.com"
#    And user enters password "salesmanager"
#    And user clicks to login button
#    Then user sees "SalesManager35" in the title
#
#  Scenario: Positive login scenario2
#    When user enters username "posmanager35@info.com"
#    And user enters password "posmanager"
#    And user clicks to login button
#    Then user sees "POSmanager35" in the title

  Scenario Outline: Positive Login Scenarios
    When user enters username "<Username>"
    And user enters password "<Password>"
    And user clicks to login button
    Then user sees "<UserID>" in the title

    @Positive
    Examples: Search values we are going to be using in this scenario is as below table
      | Username                | Password     | UserID         |
      | salesmanager35@info.com | salesmanager | salesmanager35 |
      | salesmanager25@info.com | salesmanager | salesmanager25 |
      | salesmanager55@info.com | salesmanager | salesmanager55 |
      | posmanager35@info.com   | posmanager   | posmanager35   |
      | posmanager75@info.com   | posmanager   | posmanager75   |
      | posmanager101@info.com  | posmanager   | posmanager101  |


  Scenario Outline: Negative Login Scenario with wrong email credentials
    When user enters username "<Username>"
    And user enters password "<Password>"
    And user clicks to login button
    Then user sees "<AlertMessage>" above Login Button

    @Negative1 #user enters wrong email
    Examples: Search values we are going to be using in this scenario is as below table
      | Username                 | Password     | AlertMessage         |
      | salesmanagerx35@info.com | salesmanager | Wrong login/password |
      | xsalesmanager25@info.com | salesmanager | Wrong login/password |
      | Salesmanager55@info.com  | salesmanager | Wrong login/password |
      | posmanager35x@info.com   | posmanager   | Wrong login/password |
      | Posmanager75@info.com    | posmanager   | Wrong login/password |
      | posmanager101x@info.com  | posmanager   | Wrong login/password |

  Scenario Outline: Negative Login Scenario with wrong password credentials
    When user enters username "<Username>"
    And user enters password "<Password>"
    And user clicks to login button
    Then user sees "<AlertMessage>" above Login Button

    @Negative2 #user enters wrong password
    Examples: Search values we are going to be using in this scenario is as below table
      | Username                | Password      | AlertMessage         |
      | salesmanager35@info.com | salesmanagerx | Wrong login/password |
      | salesmanager25@info.com | Salesmanager  | Wrong login/password |
      | salesmanager55@info.com | sallesmanager | Wrong login/password |
      | posmanager35@info.com   | posmanagerx   | Wrong login/password |
      | posmanager75@info.com   | posmanager75  | Wrong login/password |
      | posmanager101@info.com  | Posmanager    | Wrong login/password |

  Scenario Outline: Negative Login Scenario with empty username credentials
    When user enters username "<Username>"
    And user enters password "<Password>"
    And user clicks to login button
    Then user stays in same page and sees "Login | Best solution for startups" as title

    @Negative3 #user enters empty username
    Examples: Search values we are going to be using in this scenario is as below table
      | Username | Password     | URL                              |
      |          | salesmanager | https://qa.fidexio.com/web/login |
      |          | posmanager   | https://qa.fidexio.com/web/login |

  Scenario Outline: Negative Login Scenario with empty password credentials
    When user enters username "<Username>"
    And user enters password "<Password>"
    And user clicks to login button
    Then user stays in same page and sees "Login | Best solution for startups" as title

    @Negative5 #user enters empty password
    Examples: Search values we are going to be using in this scenario is as below table
      | Username                | Password |
      | salesmanager35@info.com |          |
      | posmanager35@info.com   |          |

  Scenario Outline: Negative Login Scenario with empty credentials
    When user enters username "<Username>"
    And user enters password "<Password>"
    And user clicks to login button
    Then user stays in same page and sees "Login | Best solution for startups" as title

    @Negative6 #user enters empty username and password
    Examples: Search values we are going to be using in this scenario is as below table
      | Username | Password |
      |          |          |
