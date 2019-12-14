Feature: Cookie use of statistic

Scenario: As an existing Cookie Factory with some recipes and waiting orders, I want to see the statistics.
    Given I get the statistics from the factory
    When I see the lowest percentage
    Then It should be the least ordered
