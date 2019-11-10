Feature: Creation of cookies

Background:
    Given a list of ingredients for a cookie named "WHITECHOCOLATE"

Scenario: Creation
    When the cook add a "VANILLA" flavour in the cookie
    Then the Cookie include the "VANILLA" flavour
