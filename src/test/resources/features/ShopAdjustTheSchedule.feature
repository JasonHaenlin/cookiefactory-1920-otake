Feature: Shop adjust the schedule

    Scenario: As an existing shop, I want to change the shop schedule.
        Given I setted the opening to 13 o'clock and the closing at 15 o'clock
        When I realized that 2 hours wasn't enough
        Then I change the opening to 8 o'clock
        And I change the closing to 20 o'clock
        When I am looking at the shop schedule
        Then I can see the updated hours
