Feature: Check Ticket Cinema

Background:
    Given a customer who went to the cinema today and want 2 cookies of the day at the shop

Scenario: get 2 free cookies of the day with a cinema ticket
    When the customer enter in the shop named "cookieGostiere" near the cinema
    Then the customer get 2 free cookies
    When the customer enter in the shop named "cookieWarrior" with no partnership
    Then no cookies is given to the customer
    When a customer try to get free cookies with an old tickets
    Then no cookies is also given to the customer
