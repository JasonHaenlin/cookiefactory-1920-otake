Feature: Discount Event in shop

Scenario: As an existing parent company and shop, I want to get a discount on my order.
    Given I order 100 cookies
    When I enter the discount code "EVENT"
    Then a discount of 0.1 is applied on my order with the "EVENT" code on 100 cookies
    And I have to pay 0.1 less euro than before the discount
