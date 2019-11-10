Feature: Managing a registered customer

  Scenario: a customer wants to sign up
    Given a not registered customer opening the application
    When the customer creates an account
    Then the customer is registered in the company database

  Scenario: a customer wants to adhere to the fidelity program
    Given a registered customer with id of "èlémènopé"
    When the registered customer choose to adhere to the fidelity program
    Then the registered customer is now part of the fidelity program

  Scenario: an adherent of fidelity program wants to buy a cookie
    Given an adherent to the fidelity program with id of "raiton"
    When the adherent order 10 cookies
    Then the adherent's cookiePoints increase

