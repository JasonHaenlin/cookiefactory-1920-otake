Feature: Manage Recipes

Scenario: As an existing recipe book, I want to manage the recipes.
    Given I want to update the recipe book
    When I delete a recipe
    Then I can not get that recipe again
    When I add a new recipe
    Then the recipe book is updated correctly
    When I want to add an already added recipe
    Then the recipe is not added
    When I add a custom cookie
    Then the recipe book is updated
    But I can not retrieve it with the other cookies
