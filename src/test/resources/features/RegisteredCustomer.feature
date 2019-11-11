Feature: Managing a registered customer

  Scenario: a customer wants to sign up
    Given a not registered customer opening the application
    When the customer creates an account
    Then the customer is registered in the company database

  Scenario: a customer wants to adhere to the fidelity program
    Given a registered customer with id of "èlémènopé"
    When the registered customer choose to adhere to the fidelity program
    Then the registered customer is now part of the fidelity program

  Scenario: an adherent of fidelity program obtains cookie points
    Given an adherent to the fidelity program with id of "raiton"
    When the adherent order 10 cookies
    Then the adherent's cookiePoints increase


  Scenario: an adherent of fidelity program obtains a discount
    Given an adherent to the fidelity program with id of "suiton"
    When the adherent order 40 cookies
    Then the adherent pays full price
    Then the adherent will get discount on next purchase
    Then the adherent order 10 cookies
    Then the adherent pays with 10.0 percent discount on their purchase
    Then the adherent now has 10 cookiePoints in his account
    Then the adherent order 10 cookies
    Then the adherent pays full price

