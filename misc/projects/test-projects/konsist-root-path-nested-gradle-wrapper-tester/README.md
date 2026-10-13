# Project Root Path Resolver Nested Gradle Wrapper

This project is used to test root project folder resolution with
[Gradle](https://gradle.org/) build system, when a submodule contains its own Gradle wrapper.

Test project contains [root](.) module and [app](./app) submodule. The [app](./app) submodule contains
`gradlew` and `gradlew.bat` files, but no settings file, so it is not a project root.

For Konsist sample projects see
the [konsist-sample-projects](https://github.com/LemonAppDev/konsist-sample-projects/) repository.
