Feature: Creation of cookies

Background:
    Given a list of ingredients for a cookie named "WHITECHOCOLATE"

Scenario: Creation
    When the cook want to add "MMS" topping
    Then the cookie is updated with "MMS"
    When the cook add a "VANILLA" flavour in the cookie
    Then the Cookie include the "VANILLA" flavour
