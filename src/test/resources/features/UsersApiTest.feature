Feature: Users API Test

Scenario: Check User by full name
    Given I start execution
    When I search user by "Vova" name
    Then I verify that I got "Vova"

Scenario: Check User by partial name
    Given I start execution
    When I search user by "Vl" name
    Then I verify that I got "Vladimir"