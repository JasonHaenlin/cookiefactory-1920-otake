Feature: Cook preparing an order

Scenario: As an existing Shop with orders in it, I want to prepare a order and go to the next one.
    Given I retrieve a waiting order
    When I want to make a cookie
    Then I can see the ingredients
    When I finished the first one
    Then I can retrieve the next waiting order
