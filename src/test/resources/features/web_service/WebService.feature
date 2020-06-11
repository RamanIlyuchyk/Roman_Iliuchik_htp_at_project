Feature: Finding users
Scenario Outline: Finding users by username

Given I search by <order> condition
When I get response
And I get <condition> names for comparison
Then I compare response and preliminary data

Examples:
| order | condition       |
| 0     | "ALL_USERS"     |
| 1     | "PARTIAL_SHORT" |
| 2     | "FULL_SHORT"    |
| 3     | "PARTIAL_LONG"  |
| 4     | "FULL_LONG"     |