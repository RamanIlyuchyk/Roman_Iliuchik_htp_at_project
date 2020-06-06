Feature: Cinema

Scenario: Login app no such user
    Given I open an app
    When I login as unregistered user
    Then I see validation message