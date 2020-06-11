Feature: Finding hotels in Moscow
Scenario: Finding hotel with minimal budget in Moscow

Given I go to booking.com
Then I set data for search
Then I perform actions with data
And I choose hotels from min budget
And I compare price of hotel and price in filters