# Simple Pronouns

Basic pronoun plugin that allows users to choose pronouns to be used in various Placeholders.

## Placeholders
There are 15 placeholders that you can use to check a player's pronouns.
(For reference, people usually put the subjective and objective pronouns in their bios)

| ----                 | Basic         | Title Case          | ALL CAPS           |
|----------------------|---------------|---------------------|--------------------|
| Subjective           | `%sp_sub%`    | `%sp_sub-title%`    | `%sp_sub-caps%`    |
| Objective            | `%sp_obj%`    | `%sp_obj-title%`    | `%sp_obj-caps%`    |
| Possessive           | `%sp_pos%`    | `%sp_pos-title%`    | `%sp_pos-caps%`    |
| Possessive-Adjective | `%sp_posadj%` | `%sp_posadj-title%` | `%sp_posadj-caps%` |
| Reflexive            | `%sp_ref%`    | `%sp_ref-title%`    | `%sp_ref-caps%`    |

## Permissions
| Permission              | Description                                                 | Default |
|-------------------------|-------------------------------------------------------------|---------|
| **`pronouns`**          | Base permission required for using the plugin               | op      | 
| `pronouns.set`          | Allows the user to set their pronouns                       | op      |
| `pronouns.list`         | Allows the user to see a list of all pronouns               | op      |
| `pronouns.get`          | Allows the user to see what pronouns other people are using | op      |
| `pronouns.clear`        | Allows the user to remove their pronouns                    | op      |
| `pronouns.custom`       | Allows the user to set custom pronouns for themselves       | op      |
| `pronouns.admin`        | Allows the user to use administrative commands              | op      |
| `pronouns.admin.set`    | Allows the user to set other users' pronouns                | op      |
| `pronouns.admin.clear`  | Allows the user to clear other users' pronouns              | op      |
| `pronouns.admin.custom` | Allows the user to set custom pronouns on other users       | op      |
| `pronouns.reload`       | Allows the user to reload the plugin                        | op      |

## Commands
| Subcommand | Permission        | Usage                                                  |
|------------|-------------------|--------------------------------------------------------|
| set        | `pronouns.set`    | `/pronouns set <pronouns>`                             |
| list       | `pronouns.list`   | `/pronouns list`                                       |
| help       | `pronouns`        | `/pronouns help`                                       |
| get        | `pronouns.get`    | `/pronouns get [player]`                               |
| clear      | `pronouns.clear`  | `/pronouns clear`                                      |
| custom     | `pronouns.custom` | `/pronouns custom <args>`                              |
| admin      | `pronouns.admin`  | `/pronouns admin <set\|clear\|custom> <player> <args>` |

