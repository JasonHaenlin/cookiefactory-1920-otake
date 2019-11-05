Feature: Cookie Ordering


  Background:
    Given a Cookie "CookieTest" and an Order

  Scenario: Ordering
    When adding the cookie in the order
    Then Cookie is add in the order
