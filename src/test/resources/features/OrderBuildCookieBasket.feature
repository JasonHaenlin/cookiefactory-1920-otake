Feature: Manage Cookie Basket

Background:
    Given a basket containing cookies
Scenario: Billy manage his basket
    When Billy delete one cookie
    Then The cookie is not in the basket anymore
    When Billy add one cookie
    Then The cookie is added in the basket