# Konsist Sample

This a [Konsist](https://github.com/LemonAppDev/konsist) sample is configured using [Gradle](https://docs.gradle.org/current/userguide/userguide.html),
[Groovy](https://groovy-lang.org/) (build script language),
[Spring](https://spring.io/),
and [Junit 5](https://junit.org/junit5/).

Other samples are located [here](..).

The [SampleKonsistTest.kt](src/konsistTest/kotlin/com/sample/SampleKonsistTest.kt) and
[SampleDynamicKonsistTest.kt](src/konsistTest/kotlin/com/sample/SampleDynamicKonsistTest.kt) classes is placed in
`konsistTest`
test directory defined by the
[JVM Test Suite Plugin](https://docs.gradle.org/current/userguide/jvm_test_suite_plugin.html).

To run tests use IDE UI or run gradle commands:
- `./gradlew konsistTest` - run tests in `konsistTest` source set

See:
- [Isolate Konsist tests](https://docs.konsist.lemonappdev.com/advanced/isolate-konsist-tests)
- [Dynamic Konsist tests](https://docs.konsist.lemonappdev.com/advanced/dynamic-konsist-tests)
