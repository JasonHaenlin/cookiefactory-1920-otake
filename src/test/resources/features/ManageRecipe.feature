Feature: Manage Recipe

Background:
    Given a Cookie Factory with some recipes

Scenario: Billy manage recipe in Cookie Factory
    When Billy delete one recipe
    Then The recipe is not in the recipe list of the cookie factory anymore
    When Billy add one recipe
    Then The recipe is added in the recipe list of the cookie factory

