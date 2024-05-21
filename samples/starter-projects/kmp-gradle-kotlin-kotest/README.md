# Project - KMP + Gradle Kotlin + Kotest

This a [Konsist](https://github.com/LemonAppDev/konsist) sample is configured using 
[Gradle](https://docs.gradle. org/current/userguide/userguide.html),
[Kotlin](https://kotlinlang.org/) (build script language),
[Kotlin Multiplatform (KMP)](https://kotlinlang.org/docs/multiplatform.html),
and [Kotest](https://kotest.io/).

This sample is derived from [kmm-basic-sample](https://github.com/Kotlin/kmm-basic-sample) project (orginal sample + Konsist tests).

Other samples are located [here](..).

Konsist tests are located in inside `konsistTest` module:
- [SampleKonsistTest.kt](src/jvmTest/kotlin/com/sample/SampleKonsistTest.kt) - single test for each Konsist check
- [SampleDynamicKonsistTest.kt](src/jvmTest/kotlin/com/sample/SampleDynamicKonsistTest.kt) - multiple tests for each Konsist check

To run tests use IDE UI or run gradle commands:
- `./gradlew konsistTest:test  --rerun-tasks` - run tests in `konsistTest` module

> The `--rerun-tasks` Gradle flag is required when Konsist tests are placed in a distinct module. When test module is
> unchanged Gradle assumes the tests are up-to-date, so these tests are skipped. This can lead to misleading test
> outcomes, as Gradle isn't aware that these tests are actually evaluating code in other modules.

See:
- [Isolate Konsist tests](https://docs.konsist.lemonappdev.com/advanced/isolate-konsist-tests)
- [Dynamic Konsist tests](https://docs.konsist.lemonappdev.com/advanced/dynamic-konsist-tests)
