# Konsist Starter Project - Android + Gradle Kotlin + Kotest

This a [Konsist](https://github.com/LemonAppDev/konsist) sample is configured using 
[Gradle](https://docs.gradle.org/current/userguide/userguide.html),
[Groovy](https://groovy-lang.org/) (build script language),
and [KoTest](https://kotest.io/).

Other samples are located [here](..).
Konsist tests are located in inside `konsistTest` module:
- [SampleKonsistTest.kt](konsistTest/src/test/kotlin/com/sample/SampleKonsistTest.kt) - single test for each Konsist check
- [SampleDynamicKonsistTest.kt](konsistTest/src/test/kotlin/com/sample/SampleDynamicKonsistTest.kt) - multiple tests for each Konsist check

To run tests use IDE UI or run gradle commands:
- `./gradlew konsistTest:test --rerun-tasks` - run tests in `konsistTest` module

> The `--rerun-tasks` Gradle flag is required when Konsist tests are placed in a distinct module. When test module is 
> unchanged Gradle assumes the tests are up-to-date, so these tests are skipped. This can lead to misleading test 
> outcomes, as Gradle isn't aware that these tests are actually evaluating code in other modules.

See:
- [Isolate Konsist tests](https://docs.konsist.lemonappdev.com/advanced/isolate-konsist-tests)
- [Dynamic Konsist tests](https://docs.konsist.lemonappdev.com/advanced/dynamic-konsist-tests)
