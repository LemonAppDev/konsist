# Konsist Sample

This a Konsist sample is configured using [Gradle](https://docs.gradle.org/current/userguide/userguide.html),
[Groovy](https://groovy-lang.org/) (build script language),
[Spring](https://spring.io/),
and [Junit 5](https://junit.org/junit5/).
Other samples are located [here](..).

The [SampleKonsistTest.kt](src/konsistTest/kotlin/com/sample/SampleKonsistTest.kt) class is placed in `konsistTest`
test directory defined by the
[JVM Test Suite Plugin](https://docs.gradle.org/current/userguide/jvm_test_suite_plugin.html).

To run tests use IDE UI or run gradle commands:
- `./gradlew konsistTest:test` - run tests in `konsist_test` module
- `./gradlew test  -x konsist_test:test` - all tests except tests in `konsist_test` module

See [Isolate Konsist tests](https://docs.konsist.lemonappdev.com/advanced/isolate-konsist-tests).
