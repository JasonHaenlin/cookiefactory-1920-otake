Feature: Making a basic order

Scenario Outline: As an existing parent company and shops, I want to make a full order.
    Given I want to make an order without any account
    When I retrieve the list of recipes
    Then I can see them
    And add <amount> named "<name>" cookies
    When I validate my order
    Then I can retrieve the list of shop
    And set an appointment for a shop in "Nice"
    When validate my paiement
    Then the order is correctly build without any discounts

Examples:
    | amount    | name              |
    | 10        | Chocolalala       |
    | 5         | Soooo Chocolate   |
    | 15        | Dark Temptation   |
