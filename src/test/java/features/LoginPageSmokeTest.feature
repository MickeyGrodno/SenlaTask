Feature: login page and home page test.

  Scenario: Unsuccessful login with incorrect login and incorrect password

    Given open "http://the-internet.herokuapp.com/login"
    When type to input with name "Username" text: "incorrectName"
    And type to input with name "Password" text: "incorrectPassword"
    And press button with text "Login"
    Then element with text "Your username is invalid!" should exist

  Scenario: Unsuccessful login with correct login and incorrect password

    Given open "http://the-internet.herokuapp.com/login"
    When type to input with name "Username" text: "tomsmith"
    And type to input with name "Password" text: "incorrectPassword"
    And press button with text "Login"
    Then element with text "Your password is invalid!" should exist

  Scenario: Successful login with correct login and correct password

    Given open "http://the-internet.herokuapp.com/login"
    When type to input with name "Username" text: "tomsmith"
    And type to input with name "Password" text: "SuperSecretPassword!"
    And press button with text "Login"
    Then verify that page with url "http://the-internet.herokuapp.com/secure" is opened and element with text "You logged into a secure area!" should exist

  Scenario: Successful logout with correct login and correct password

    Given open "http://the-internet.herokuapp.com/login"
    When type to input with name "Username" text: "tomsmith"
    And type to input with name "Password" text: "SuperSecretPassword!"
    And press button with text "Login"
    And press button with text "Logout"
    Then verify that page with url "http://the-internet.herokuapp.com/login" is opened and element with text "You logged out of the secure area!" should exist