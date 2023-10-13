# Konsist Sample

This a [Konsist](https://github.com/LemonAppDev/konsist) sample is configured using [Gradle](https://docs.gradle.org/current/userguide/userguide.html),
[Groovy](https://groovy-lang.org/) (build script language),
and [KoTest](https://kotest.io/).

Other samples are located [here](..).

The [SampleKonsistTest.kt](konsist_test/src/test/java/com/sample/SampleKonsistTest.kt) and
The [SampleDynamicKonsistTest.kt](konsist_test/src/test/java/com/sample/SampleDynamicKonsistTest.kt)
class containing Konsist Test is placed in `konsistTest` module 
(see [Isolate Konsist Tests](https://docs.konsist.lemonappdev.com/advanced/isolate-konsist-tests)).

To run tests use IDE UI or run gradle commands:
- `./gradlew konsist_test:test` - run tests in `konsist_test` module
- `./gradlew test  -x konsist_test:test` - all tests except tests in `konsist_test` module

> The --rerun-tasks Gradle flag is required - when Konsist tests are placed in a distinct module and there haven't been changes within that module, Gradle assumes the "unit tests" are up-to-date and might skip them. This can lead to misleading test outcomes, as Gradle isn't aware that these tests are actually evaluating other modules.

See:
- [Isolate Konsist tests](https://docs.konsist.lemonappdev.com/advanced/isolate-konsist-tests)
- [Dynamic Konsist tests](https://docs.konsist.lemonappdev.com/advanced/dynamic-konsist-tests)
