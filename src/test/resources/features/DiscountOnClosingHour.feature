Feature: Discount on closing hour

  Scenario: As an existing parent company and shop, I want to get a discount if I order a cookie on the last hour.
    Given I order some cookies
    When I pass an order at 17 o'clock to a shop closing at 18 o'clock
    Then I get a 0.3 discount on my order only on the basics cookies
