Feature: a shop can't accept an order which it can't satisfy

  Background:
    Given shops containing products and ingredients

  Scenario: a customer can chose only products available in at least one shop
    When the customer chose a cookie "Chocolalala"
    And the product is not available in any shop
    Then the customer can't add "Chocolalala" for sold-out reason

  Scenario: a customer can't choose a product from a closed shop
    When the customer chose a cookie "Dark Temptation"
    And hour is 23 and the only shop satisfying the product is closed
    Then the customer can't add "Dark Temptation" at 23 for hour reason

