Feature: Managing a registered customer

  Scenario: a customer wants to sign up
    Given a not registered customer opening the application
    When the customer creates an account
    Then the customer is registered in the company database