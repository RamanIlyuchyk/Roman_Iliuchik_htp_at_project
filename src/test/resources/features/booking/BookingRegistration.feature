Feature: Registration new user in booking
Scenario: create trash email and registration on booking

Given I go to trashmail.com
Then I get new trash mail
And I go to booking.com
Then I create new user
And I go to yandex.ru
Then I confirm email
And I again go to booking.com
Then I go to my dashboard
And I check lack of banner