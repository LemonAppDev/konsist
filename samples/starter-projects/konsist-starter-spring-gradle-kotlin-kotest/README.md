# Konsist Starter Project - Spring + Gradle Kotlin + Kotest

This a [Konsist](https://github.com/LemonAppDev/konsist) sample is configured using 
[Gradle](https://docs.gradle.org/current/userguide/userguide.html),
[Kotlin](https://kotlinlang.org/) (build script language), 
[Spring](https://spring.io/),
and [Kotest](https://kotest.io).

Other samples are located [here](..).

Konsist tests are located in inside `konsistTest` module:
- [SampleKonsistTest.kt](src/konsistTest/kotlin/com/sample/SampleKonsistTest.kt) - single test for each Konsist check
- [SampleDynamicKonsistTest.kt](src/konsistTest/kotlin/com/sample/SampleDynamicKonsistTest.kt) - multiple tests for each Konsist check

The `konsistTest` source set defined by the [JVM Test Suite Plugin](https://docs.gradle.org/current/userguide/jvm_test_suite_plugin.html).

To run tests use IDE UI or run gradle commands:
- `./gradlew konsistTest --rerun-tasks` - run tests in `konsistTest` source set

> The `--rerun-tasks` Gradle flag is required when Konsist tests are placed in a distinct module. When test module is 
> unchanged Gradle assumes the tests are up-to-date, so these tests are skipped. This can lead to misleading test 
> outcomes, as Gradle isn't aware that these tests are actually evaluating code in other modules.


See:
- [Isolate Konsist tests](https://docs.konsist.lemonappdev.com/advanced/isolate-konsist-tests)
- [Dynamic Konsist tests](https://docs.konsist.lemonappdev.com/advanced/dynamic-konsist-tests)
