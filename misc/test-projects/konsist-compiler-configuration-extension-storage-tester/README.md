# Konsist Compiler Configuration Extension Storage Tester

This project is a regression test for running Konsist with a Kotlin compiler (`kotlin-compiler-embeddable`) prior
to Kotlin 2.4.

## Issue

Konsist uses `kotlin-compiler-embeddable` to parse Kotlin files. Konsist is built with Kotlin 2.4, but the
`kotlin-compiler-embeddable` version used at runtime is decided by the consumer project. For example, Spring Boot
dependency management (`spring-boot-starter-parent` in Maven) imports `kotlin-bom` aligned with the `kotlin.version`
property, so a project using Kotlin 2.3 runs Konsist with `kotlin-compiler-embeddable` 2.3.

Kotlin 2.4 requires the extension storage to be set in `CompilerConfiguration` before creating
`KotlinCoreEnvironment`. Kotlin 2.3 does not provide this API, so calling it directly fails at runtime:

```text
java.lang.NoSuchMethodError: 'void org.jetbrains.kotlin.cli.FrontendConfigurationKeysKt.setExtensionsStorage(
    org.jetbrains.kotlin.config.CompilerConfiguration,
    org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar$ExtensionStorage)'
```

To support both versions [KotlinFileParser](../../../lib/src/main/kotlin/com/lemonappdev/konsist/core/util/KotlinFileParser.kt)
resolves the setter via reflection and sets the extension storage only when the API is available (Kotlin 2.4+).

## Test

This project uses Kotlin 2.3 and enforces `kotlin-bom` 2.3 (mimicking Spring Boot dependency management), so Konsist
runs with `kotlin-compiler-embeddable` 2.3. Tests verify that:
- Kotlin compiler used at runtime is prior to Kotlin 2.4
- Konsist parses Kotlin files

This project covers only legacy Kotlin versions (prior to Kotlin 2.4). Kotlin 2.4+ is tested with the current
[starter projects](../../../samples/starter-projects). Kotlin version in this project is locked (Renovate updates are
disabled in [renovate.json](../../../renovate.json)).

## Run

```bash
# Konsist root directory
./gradlew publishToMavenLocal -P konsist.releaseTarget=local

cd misc/test-projects/konsist-compiler-configuration-extension-storage-tester
./gradlew test
```
