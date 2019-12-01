Feature: a shop can't accept an order which it can't satisfy

  Background:
    Given shops containing products and ingredients

  Scenario: a customer can chose only products available in at least one shop
    When the customer want to see a product
    And the product is not available in any shop
    Then the customer will not see this product

  Scenario: a customer can't choose a product from a closed shop
    When the customer want to see a product
    And the shop satisfying the product is closed
    Then the customer will not see this product

  Scenario: a customer is warned when his order could not be retrieved in the same shop
    When the customer choose many products
    And the products can't be retrieved in the same shop
    Then the customer get a warning
    Then the customer delete one order
