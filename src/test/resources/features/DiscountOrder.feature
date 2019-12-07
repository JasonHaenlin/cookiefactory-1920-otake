Feature: Adding a discount on closing time

  Background:
    Given a customer that order at the end of the day

  Scenario: End of the day discount
    When a customer create an order with cookies at 18 o'clock
    Then if the closing hour is almost time, a discount of 0.3 is apply on basics cookies
