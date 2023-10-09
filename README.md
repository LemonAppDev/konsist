![Konsist](misc/konsist-logo/logo.png)
==========

[![Kotlin](https://img.shields.io/badge/Kotlin-blue.svg?style=flat&logo=kotlin)](https://kotlinlang.org)
![Check Workflow](https://github.com/LemonAppDev/konsist/actions/workflows/check.yml/badge.svg)
[<img src="https://img.shields.io/maven-central/v/com.lemonappdev/konsist?label=Release"/>](https://central.sonatype.com/artifact/com.lemonappdev/konsist)

Konsist is a library that guards the consistency of [Kotlin](https://kotlinlang.org/) projects by promoting the
standardization of the Kotlin codebase. It enforces coding conventions and project architecture. Konsist
tests are written in Kotlin and can be easily integrated with popular testing frameworks such as 
[JUnit4](https://junit.org/junit4/), [JUnit5](https://junit.org/junit5/).

## Dependencies

```kotlin
// Gradle Kotlin:
testImplementation("com.lemonappdev:konsist:0.13.0")

// Gradle Groovy:
testImplementation "com.lemonappdev:konsist:0.13.0"

// Maven:
<dependency>
    <groupId>com.lemonappdev</groupId>
    <artifactId>konsist</artifactId>
    <version>0.13.0</version>
    <scope>test</scope>
</dependency>
```

Check the [Konsist documentation](https://docs.konsist.lemonappdev.com/) to learn more about Konsist and take a 
look at [getting started guide](https://docs.konsist.lemonappdev.com/getting-started/gettingstarted).

## Examples

Konsist API reflects the structure of Kotlin code. Konsist guards are written in form of unit tests.

### General Kotlin Check

```kotlin
@Test
fun `classes with 'UseCase' suffix should reside in 'usecase' package`() {
    Konsist.scopeFromProject()
        .classes()
        .withNameEndingWith("UseCase")
        .assertTrue { it.resideInPackage("..usecase..") }
}
```

### Android Specific Check

```kotlin
@Test
fun `classes extending 'ViewModel' should have 'ViewModel' suffix`() {
    Konsist.scopeFromProject()
        .classes()
        .withAllParentsOf(ViewModel::class)
        .assertTrue { it.name.endsWith("ViewModel") }
}
```

### Spring Specific Check

```kotlin
@Test
fun `interfaces with 'Repository' annotation should have 'Repository' suffix`() {
    Konsist
        .scopeFromProject()
        .interfaces()
        .withAllAnnotationsOf(Repository::class)
        .assertTrue { it.hasNameEndingWith("Repository") }
}
```

### Architecture Layers Check

```kotlin
@Test
fun `clean architecture layers have correct dependencies`() {
    Konsist
        .scopeFromProduction()
        .assertArchitecture {
            // Define layers
            val domain = Layer("Domain", "com.myapp.domain..")
            val presentation = Layer("Presentation", "com.myapp.presentation..")
            val data = Layer("Data", "com.myapp.data..")

            // Define architecture assertions
            domain.dependsOnNothing()
            presentation.dependsOn(domain)
            data.dependsOn(domain)
        }
}
```
    
Check out our [snippet](https://docs.konsist.lemonappdev.com/inspiration/snippets) page for a feast of examples!

### Articles

- [Introducing Konsist: A Cutting-Edge Kotlin Linter](https://blog.kotlin-academy.com/introducing-konsist-a-cutting-edge-kotlin-linter-d3ab916a5461)
- [Refactoring Multi-Module Kotlin Project With Konsist](https://proandroiddev.com/refactoring-multi-module-kotlin-project-with-konsist-f0de0de59a3d)
- [ArchUnit vs. Konsist. Why Did We Need Another Kotlin Linter?](https://proandroiddev.com/archunit-vs-konsist-why-did-we-need-another-linter-972c4ff2622d)
- [Protect Kotlin Project Architecture Using Konsist](https://proandroiddev.com/protect-kotlin-project-architecture-using-konsist-3bfbe1ad0eea)

### Star History

[![Star History Chart](https://api.star-history.com/svg?repos=LemonAppDev/konsist&type=Date)](https://star-history.com/#LemonAppDev/konsist&Date)

### Community & Support

Write a message on the  [#konsist channel](https://kotlinlang.slack.com/archives/C05QG9FD6KS) at kotlinlang Slack 
Workspace (preferred) or start a [GitHub discussion](https://github.com/LemonAppDev/konsist/discussions).

## Contributing

Please be sure to review Konsist [contributing guidelines](https://docs.konsist.lemonappdev.com/help/contributing)
to learn how to support the project.

## Licence

Konsist is distributed under the terms of the Apache License (Version 2.0). See [LICENSE.md](LICENSE) for details.
