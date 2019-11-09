Feature: Get One and All Cookies

Background:
    Given a Cookie Factory with some recipes
Scenario: Billy get one and more cookies
    When Billy get all Cookies from Cookies Factory
    Then Billy has all Cookies
    When Billy get a Cookie from Cookies Factory
    Then Billy has one cookie