# Konsist Kotlin Compiler Classpath Conflict Tester

This project is a regression test for running Konsist with another Kotlin compiler on the classpath.

## Issue

Konsist uses `kotlin-compiler-embeddable` to parse Kotlin files. This artifact contains IntelliJ classes relocated to
`org.jetbrains.kotlin.com.intellij` package.

Other Kotlin compiler copies (`kotlin-compiler`, Android Lint `intellij-core`, IDE test runner) contain classes with the
same names, but depend on non-relocated IntelliJ classes. When such copy is loaded first, parsing fails, e.g.:

```text
java.lang.ClassCastException: class org.jetbrains.kotlin.idea.KotlinFileType cannot be cast to class
    org.jetbrains.kotlin.com.intellij.openapi.fileTypes.FileType
```

The failure depends on the classpath order, so it may appear only in some environments.
[KotlinFileParser](../../../lib/src/main/kotlin/com/lemonappdev/konsist/core/util/KotlinFileParser.kt) detects
multiple Kotlin compilers on the classpath and lists them in the error message.

## Test

This project declares `kotlin-compiler` before Konsist, so it is loaded before `kotlin-compiler-embeddable`. Tests
verify that Konsist throws an exception listing both Kotlin compilers.

## Run

```bash
# Konsist root directory
./gradlew publishToMavenLocal -P konsist.releaseTarget=local

cd misc/test-projects/konsist-kotlin-compiler-classpath-conflict-tester
./gradlew test
```
