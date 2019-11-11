Feature: Use of Statistic

Background:
    Given a Cookie Factory with some recipes and waiting orders

Scenario: Billy use Statistic of Cookie Factory
    When Billy get the Statistic
    Then The lowest percentage but nevertheless higher than 0 corresponds to the least ordered cookie 