Feature: Finding hotels in Paris
Scenario: Finding hotel with minimal price but from max budget in Paris

Given I go to booking.com
Then I set data for search
And I sort hotels by ascending with max budget
And I compare price of hotel and price in filters