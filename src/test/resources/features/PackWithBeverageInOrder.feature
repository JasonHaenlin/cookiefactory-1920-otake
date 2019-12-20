Feature: Pack with beverage in order

    Background: As an existing parent company and shops, I want to make an order.
        Given I am making my order

    Scenario: With a list of cookies and Beverages, I want to order a pack only
        Given I am making my order with a pack of 15 cookies at 15 € and a beverage at 6 €
        When I am validating my order
        Then I should have paid for my pack only

    Scenario: With a list of cookies and Beverages, I want to order a pack and some cookies
        Given I am making my order with 15 cookies a beverage and 5 more cookies
        When I am validating my order
        Then I should have paid for my pack and for 5 other cookies
