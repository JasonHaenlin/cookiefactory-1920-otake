Feature: Discount for companies

Scenario: As an existing parent company and shops, I want to make an order using my company discount.
    Given I am creating an order
    When I add the code "CE_POLYTECH"
    Then A discount will be added on the final price
