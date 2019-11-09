Feature: Managing shop taxes
  Background:
    Given the shop "Nice Cookie Chef" of "Nice" has taxes of 0.20

  Scenario: Buying cookies on taxes
    When a customer makes an order of 10 of his favourite cookie
    Then the price is calculated according to the shop taxes policy

  Scenario: Changing taxes
    When the store manager wants to change the store taxes to 0.40
    And a customer order 4 cookie(s)
    Then the new taxes applies to the cookie(s) ordering