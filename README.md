# Mango

## Linters

Run spotless:

- `./gradlew spotlessCheck` - checks that sourcecode satisfies formatting steps.
- `./gradlew spotlessApply` - applies code formatting steps to sourcecode in-place.

Run detekt:

- `./gradlew detektCheck` - checks that sourcecode satisfies detekt rules.
- `./gradlew detektApply` - applies detekt code formatting rules to sourcecode in-place.


Run tests (including arch-unit):

- `./gradlew test` - run all tests.

## Architecture

[Diagram](https://www.figma.com/file/NiWGr0P77zPfTxJGlDxngQ/Mango-Architecture?node-id=0%3A1&t=ThfL1SACFRYtODpq-1)

Layers:

1. Presentation Layer (we will rename Api Layer)
   The presentation layer is the top layer of the spring boot architecture. It consists of Views. i.e., the front-end
   part of the application. It handles the HTTP requests and performs authentication. It is responsible for converting
   the JSON field’s parameter to Java Objects and vice-versa. Once it performs the authentication of the request it
   passes it to the next layer. i.e., the business layer.
2. Business Layer ()
   The business layer contains all the business logic. It consists of services classes. It is responsible for validation
   and authorization.
3. Persistence Layer
   The persistence layer contains all the database storage logic. It is responsible for converting business objects to
   the database row and vice-versa.
4. Database Layer
   The database layer contains all the databases such as MySql, MongoDB, etc. This layer can contain multiple databases.
   It is responsible for performing the CRUD operations.

## Enable Live Reload

1. Install
   chrome [Live Reload](https://chrome.google.com/webstore/detail/livereload/jnihajbhpnppcggbcgedagnkighmdlei?hl=en)
   plugin
2. IntelliJ IDEA
   1. Update run configuration - add `On frame deactivation` option with `Updated classes and resources`
   2. `Advanced Settings` → select `Allow auto-make to start even if developed application is currently running`
