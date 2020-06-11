Feature: Registration new user in booking
Scenario: create trash email and registration on booking

Given I go to booking.com
Then I create new user
And I go to mail.yandex.ru
Then I again go to booking.com
And I go to my dashboard
And I check lack of banner