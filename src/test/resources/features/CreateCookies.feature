Feature: Creation of cookies

Background:
    Given a list of ingredients

Scenario: Creation
    When the cook want to create a Cookie named "WhiteChocolate"
    Then a new cookie with the selected ingredients is created
    When the cook add a "Vanilla" flavour in the cookie
    Then the Cookie include the "Vanilla" flavour
