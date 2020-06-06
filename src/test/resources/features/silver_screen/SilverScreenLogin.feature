Feature: Cinema

Scenario: Login app
    Given I open an app
    When I login with <login> and <password>
    Then I can see Red Carpet Club <level> in upper right corner