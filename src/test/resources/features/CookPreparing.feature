Feature: Cooke preapring order

Background:
    Given orders in a shop

Scenario: Cooking
    When the cook want to prepare the first order
    Then the cook can retrieve the first order in the queue
    When the cook finish the preparation
    Then the cook can go to the next order
    When an order is retrieve by his id
    Then the order is archive in the queue
