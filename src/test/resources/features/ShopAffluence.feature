Feature: Getting statistics on a shop
  Background:
    Given A shop named "Nice-Cookie" in "Nice"
    And 1 order retrieved at 14h and 3 orders retrieved at 18h

  Scenario: A Manager want to see at which hour in the day, the shop is having less customers
    When A Manager ask for statistics about his shop
    Then He can see that there have been less orders at 14h compared to 18h