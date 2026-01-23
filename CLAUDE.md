# CLAUDE.md - Konsist Codebase Guide

This document provides guidance for AI assistants working with the Konsist codebase.

## Project Overview

**Konsist** is a Kotlin static code analyzer/linter that enforces codebase consistency and architectural rules. Tests are written as JUnit or Kotest unit tests. It's published to Maven Central as `com.lemonappdev:konsist`.

- **Current Version**: 0.17.3
- **License**: Apache License 2.0
- **Kotlin Version**: 2.0.21
- **JDK**: 21 (for builds), bytecode targets Java 8

## Quick Commands

```bash
# Run all unit tests
./gradlew :lib:test

# Run Konsist's own consistency tests (self-validation)
./gradlew lib:konsistTest

# Run integration tests
./gradlew lib:integrationTest

# Run API tests
./gradlew lib:apiTest

# Run all linters
./gradlew spotlessCheck detektCheck

# Format code with ktlint via Spotless
./gradlew spotlessApply

# Build the library
./gradlew lib:assemble

# Publish to local Maven (~/.m2/repository)
./gradlew publishToMavenLocal -Pkonsist.releaseTarget=local
```

## Repository Structure

```
konsist/
├── lib/                          # Main library module
│   ├── src/
│   │   ├── main/kotlin/          # Production source code
│   │   ├── test/kotlin/          # Unit tests
│   │   ├── integrationTest/      # Integration tests
│   │   ├── konsistTest/          # Self-validation tests (Konsist testing itself)
│   │   ├── apiTest/              # API behavior tests
│   │   └── snippet/              # Documentation snippet tests
│   └── build.gradle.kts
├── buildSrc/                     # Gradle convention plugins
│   └── src/main/kotlin/
│       ├── local.base.gradle.kts      # Base plugin (combines kotlin, spotless, test)
│       ├── local.kotlin.gradle.kts    # Kotlin configuration
│       ├── local.spotless.gradle.kts  # ktlint formatting
│       ├── local.detekt.gradle.kts    # Detekt static analysis
│       ├── local.test.gradle.kts      # JUnit5 test configuration
│       └── local.publish.gradle.kts   # Maven publishing
├── samples/starter-projects/     # Example projects for users
├── test-projects/                # CI validation projects
├── scripts/                      # Python utility scripts
├── gradle/libs.versions.toml     # Version catalog
├── detekt.yml                    # Detekt rules configuration
└── settings.gradle.kts
```

## Source Code Architecture

The library is organized into two main packages under `lib/src/main/kotlin/com/lemonappdev/konsist/`:

### API Layer (`api/`)
Public interfaces and contracts. Users interact only with this layer.

- `api/Konsist.kt` - Main entry point singleton (delegates to `KoScopeCreatorCore`)
- `api/container/` - `KoScope`, `KoScopeCreator` for scope management
- `api/declaration/` - Declaration interfaces (`KoClassDeclaration`, `KoFunctionDeclaration`, etc.)
- `api/declaration/combined/` - Combined types (`KoClassAndInterfaceDeclaration`, etc.)
- `api/provider/` - ~143 provider interfaces for declaration properties
- `api/provider/modifier/` - Modifier providers (`KoAbstractModifierProvider`, etc.)
- `api/ext/` - ~148 extension function files
- `api/architecture/` - `Layer`, `LayerDependencies`, architecture validation
- `api/verify/` - Assertion functions (`assertTrue`, `assertFalse`, etc.)

### Core Layer (`core/`)
Internal implementations, not part of public API.

- `core/declaration/` - Declaration implementations (`*Core` suffix)
- `core/provider/` - Provider implementations
- `core/container/` - `KoScopeCreatorCore` implementation
- `core/cache/` - Caching mechanisms for performance
- `core/filesystem/` - File system traversal
- Uses IntelliJ PSI for Kotlin code parsing

## Naming Conventions

### Type Naming
- **`Ko` prefix** - All public API types (e.g., `KoScope`, `KoClassDeclaration`)
- **`Core` suffix** - Internal implementations (e.g., `KoClassDeclarationCore`)
- **`Provider` suffix** - Capability interfaces (e.g., `KoNameProvider`, `KoClassProvider`)
- **`Declaration` suffix** - Type nodes (e.g., `KoFileDeclaration`, `KoPropertyDeclaration`)
- **`And` for combined** - `KoClassAndInterfaceDeclaration`, `KoInterfaceAndObjectDeclaration`

### Method Naming for Providers (See DeveloperReadme.md for full details)

For properties with `List<KoXDeclaration>` type:

**If `KoXDeclaration` implements `KoNameProvider`:**
```kotlin
val items: List<KoXDeclaration>       // Property returning list
val numItems: Int                      // Count property
fun countItems(predicate): Int         // Conditional count
fun hasItems(): Boolean                // Has any items
fun hasItem(predicate): Boolean        // Has item matching predicate
fun hasAllItems(predicate): Boolean    // All items match predicate
fun hasItemWithName(name, vararg): Boolean      // Has item with name(s)
fun hasItemsWithAllNames(name, vararg): Boolean // Has items with all names
```

**Extension functions:**
```kotlin
fun List<T>.withItems()                // Filter: has any items
fun List<T>.withoutItems()             // Filter: has no items
fun List<T>.withItem(predicate)        // Filter: has item matching predicate
fun List<T>.withoutItem(predicate)     // Filter: no item matches predicate
fun List<T>.withItemNamed(name, vararg)         // Filter by name
fun List<T>.withoutItemNamed(name, vararg)      // Filter excluding name
fun List<T>.withAllItemsNamed(name, vararg)     // Filter: has all names
fun List<T>.withoutAllItemsNamed(name, vararg)  // Filter: missing any name
```

### Standard Parameters
- `includeNested: Boolean = true` - Include nested declarations
- `includeLocal: Boolean = true` - Include local declarations
- `predicate: (T) -> Boolean` - For filtering operations
- `indirectParents: Boolean = false` - Include indirect inheritance

## Test Organization

### Test Types

| Test Suite | Command | Purpose |
|------------|---------|---------|
| `test` | `./gradlew :lib:test` | Unit tests with mocks |
| `konsistTest` | `./gradlew lib:konsistTest` | Konsist self-validation (API consistency) |
| `integrationTest` | `./gradlew lib:integrationTest` | Integration tests against real code |
| `apiTest` | `./gradlew lib:apiTest` | API behavior verification |
| `snippet` | `./gradlew lib:snippet` | Documentation snippet validation |

### Test Frameworks
- **JUnit 5** (`junit-jupiter`) - Primary test framework
- **Kluent** - Assertion library (`shouldBe`, `shouldThrow`)
- **MockK** - Mocking library
- **Kotest** - Used in some tests

### Test File Naming
Tests mirror source structure:
- `KoClassDeclarationForKoNameProviderTest.kt` - Tests `KoNameProvider` on `KoClassDeclaration`
- Declaration tests are split by provider functionality

## CI/CD Pipeline

GitHub Actions workflows in `.github/workflows/`:

- **check.yml** - Main CI workflow
  - `konsist-junit-test` - Unit tests
  - `konsist-konsist-test` - Self-validation
  - `konsist-integration-test` - Integration tests
  - `api-test` - API tests
  - `konsist-spotless` - ktlint check
  - `konsist-detekt` - Detekt analysis
  - `declaration-tester-project-*` - Cross-platform validation (macOS, Windows)
  - `konsist-artifact-check` - Artifact validation

## Code Quality Tools

### Spotless (ktlint)
- Configured in `buildSrc/src/main/kotlin/local.spotless.gradle.kts`
- ktlint version from `gradle/libs.versions.toml`
- Run: `./gradlew spotlessCheck` or `./gradlew spotlessApply`

### Detekt
- Configuration in `detekt.yml`
- Key settings:
  - Max line length: 140 characters
  - LargeClass threshold: 800 lines
  - LongMethod threshold: 60 lines
  - CyclomaticComplexity threshold: 15
  - TooManyFunctions threshold: 25
- Run: `./gradlew detektCheck`

## Publishing

Three release targets (set via `-Pkonsist.releaseTarget=`):

| Target | Command | Destination |
|--------|---------|-------------|
| `local` | `./gradlew publishToMavenLocal -Pkonsist.releaseTarget=local` | `~/.m2/repository` |
| `snapshot` | `./gradlew publish -Pkonsist.releaseTarget=snapshot` | Sonatype snapshots |
| `release` | `./gradlew publish -Pkonsist.releaseTarget=release` | Sonatype releases -> Maven Central |

## Key API Usage Patterns

### Creating Scopes
```kotlin
Konsist.scopeFromProject()                    // All project files
Konsist.scopeFromModule("app")                // Specific module
Konsist.scopeFromPackage("com.example..")     // Package (.. = recursive)
Konsist.scopeFromSourceSet("main")            // Source set
Konsist.scopeFromProduction()                 // Production code only
Konsist.scopeFromTest()                       // Test code only
```

### Querying Declarations
```kotlin
scope.classes()                               // All classes
scope.functions()                             // All functions
scope.interfaces()                            // All interfaces
scope.properties()                            // All properties
scope.files                                   // All files
```

### Filtering with Extensions
```kotlin
scope.classes()
    .withNameEndingWith("UseCase")
    .withAnnotationOf(Service::class)
    .withoutModifier(KoModifier.ABSTRACT)
```

### Assertions
```kotlin
scope.classes()
    .assertTrue { it.resideInPackage("..domain..") }

scope.classes()
    .assertFalse { it.hasPublicModifier }
```

### Architecture Validation
```kotlin
scope.assertArchitecture {
    val domain = Layer("Domain", "com.app.domain..")
    val presentation = Layer("Presentation", "com.app.presentation..")
    val data = Layer("Data", "com.app.data..")

    domain.dependsOnNothing()
    presentation.dependsOn(domain)
    data.dependsOn(domain)
}
```

## Development Guidelines

1. **API Changes**: All public API must be in `api/` package with interfaces only
2. **Implementations**: Use `Core` suffix in `core/` package
3. **New Providers**: Follow naming conventions in DeveloperReadme.md
4. **Tests**: Add tests in appropriate test suite (unit vs integration)
5. **Extensions**: Place in `api/ext/` with corresponding list extensions
6. **Deprecation**: Use `@Deprecated` annotation with migration path
7. **Documentation**: KDoc for all public API members

## Common Issues

### Build Error: No matching variant
```
No matching variant of project :buildSrc was found
```
**Fix**: Change Gradle JDK in IDE settings to match project requirements (JDK 21)

### Test Method Name Resolution
The `assertTrue`/`assertFalse` methods extract test names from stack trace. Index varies based on whether default parameters are used. See DeveloperReadme.md for details.

## Resources

- [Konsist Documentation](https://docs.konsist.lemonappdev.com/)
- [API Reference](https://lemonappdev.github.io/konsist/)
- [GitHub Discussions](https://github.com/LemonAppDev/konsist/discussions)
- [Slack #konsist](https://kotlinlang.slack.com/archives/C05QG9FD6KS)
