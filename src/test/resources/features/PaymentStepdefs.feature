Feature: Use payment method

  Scenario: A customer proceed to their order s payment
    Given A complete order
    When The customer begin the validation process
    Then The customer pay the due price