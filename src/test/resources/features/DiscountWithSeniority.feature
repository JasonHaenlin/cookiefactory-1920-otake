Feature: Discount with seniority

Scenario: As an existing parent company and shops, I am a old time customer
    Given I want to make an order
    When I order with a 2 years account
    Then I should receive a 0.02 discount on the final price
