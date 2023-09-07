# Konsist Sample

This a Konsist sample is configured using [Gradle](https://docs.gradle.org/current/userguide/userguide.html),
[Kotlin](https://kotlinlang.org/) (build script language), and [Junit 5](https://junit.org/junit5/).
Other samples are located [here](..).

The [SampleKonsistTest.kt](konsist_test/src/test/java/com/sample/SampleKonsistTest.kt) class containing Konsist Test is placed in `konsistTest`
module.

To run tests use IDE UI or run gradle commands:
- `./gradlew konsistTest:test` - run tests in `konsist_test` module
- `./gradlew test  -x konsist_test:test` - all tests except tests in `konsist_test` module

See [Isolate Konsist tests](https://docs.konsist.lemonappdev.com/advanced/isolate-konsist-tests).
