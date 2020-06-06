Feature: Cinema

Scenario Outline: Login app blank field
    Given I open an app
    When I left blank <field> field
    Then I see <message> message

    Examples:
        | field    | message                            |
        | login    | Необходимо заполнить поле "E-mail" |
        | password | Необходимо заполнить поле "Пароль" |