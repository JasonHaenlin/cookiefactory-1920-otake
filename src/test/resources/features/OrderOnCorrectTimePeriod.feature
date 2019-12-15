Feature: order on correct time period

    Background: As an existing parent company and shops, I want to make an order.
        Given I am making an order

    Scenario: With an almost complete order, I want to define the appointment date.
        Given I set an appointment date
        When I create my order
        Then I can validate the order in the shop
        But If the date is not correct
        Then I will have to try again

    Scenario: With a complete order, I would like to retrieve my order.
        Given A ready order
        When I want to retrieve it
        Then With the correct id, I can retrieve it
        But if I retrieve my order too soon, I'll not be able to get it
