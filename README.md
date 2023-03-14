# Mango

## Wiki

[Wiki](https://github.com/LemonAppDev/mango/wiki) contains all the information about the project.

## Linters

Run spotless (ktlint):

- `./gradlew spotlessCheck` - checks that sourcecode satisfies formatting steps.
- `./gradlew spotlessApply` - applies code formatting steps to sourcecode in-place.

Run detekt:

- `./gradlew detektCheck` - checks that sourcecode satisfies detekt rules.
- `./gradlew detektApply` - applies detekt code formatting rules to sourcecode in-place.

Run tests (unit and arch-unit tests):

- `./gradlew test` - run all tests.

Run integration tests (including arch-unit):

- `./gradlew integrationTest` - run all tests.
