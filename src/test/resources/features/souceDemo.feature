Feature: Selenium for SauceDemo website automation

#Will run before each case
  Background:
    Given baseUrl is login page "https://www.saucedemo.com/"



#   Scenario:
#      When valid username is "standard_user" and password is "secret_sauce"
#      And click to the login button
#      Then "https://www.saucedemo.com/inventory.html" page should display


  Scenario Outline:
    When valid username is "<username>" and password is "<password>"
    And click to the login button
    Then "https://www.saucedemo.com/inventory.html" page should display
    And user adds "<product>" to the cart
    And navigate to the cart page
    And "<product>" product should be selected in cart
    Examples:
      | username | password | product |
      | standard_user | secret_sauce | Sauce Labs Backpack |
    | locked_out_user | secret_sauce| Sauce Labs Bike Light |
    | error_user | secret_sauce | Sauce Labs Bolt T-Shirt |
      | standard_user | secret_sauce | Sauce Labs Bike Light |
