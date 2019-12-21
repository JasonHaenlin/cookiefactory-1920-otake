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

Scenario: The company can add new Ingredients
    Given an Ingredient called "banana" of type "flavour"
    And the ingredient "banana" doesn't already exist in company's recipe book
    When the company add the ingredient to his ingredients
    Then a new ingredient "banana" is added

Scenario: The company can change margin on Ingredients
    Given an existing ingredient called "chocolate"
    When the company want to change the ingredient "chocolate" price to 4.99
    Then the ingredient "chocolate" price be 4.99

#Scenario: As an existing ingredient, I want to change the margin on it.
#    Given I want to update a ingredient named "chocolate"
#    When I retrieve the ingredient by his name
#    Then I can update the price to 4.99 €
#    When I make a new cookie with "chocolate" in it
#    Then The price should be higher

#Scenario: The company can change margin on Custom cookies
