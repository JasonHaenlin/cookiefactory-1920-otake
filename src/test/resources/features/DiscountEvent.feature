Feature: Event Discount in shop

Background:
    Given an order with 100 cookies and a Discount code "EVENT" applied with similar cookies with a reduction of 0.1

Scenario: Event Discount
    When a customer enter a code "EVENT" the discount is applied
    Then the discount is on with a reduction of 0.1
    And his passing from 10.0 to 9.0
