![Konsist](misc/page-konsist-logo.png)
==========

[![Kotlin](https://img.shields.io/badge/Kotlin-blue.svg?style=flat&logo=kotlin)](https://kotlinlang.org)
![Check Workflow](https://github.com/LemonAppDev/konsist/actions/workflows/check.yml/badge.svg)
[<img src="https://img.shields.io/maven-central/v/com.lemonappdev/konsist?label=Release"/>](https://central.sonatype.com/artifact/com.lemonappdev/konsist)

Konsist is a library that guards the consistency of [Kotlin](https://kotlinlang.org/) projects by promoting the
standardization of the Kotlin codebase. It enforces coding conventions and project architecture. Konsist
tests are written in Kotlin and can be easily integrated with popular testing frameworks such as 
[JUnit4](https://junit.org/junit4/), [JUnit5](https://junit.org/junit5/).

Konsist API reflects the structure of Kotlin code. Konsist guards are written in form of unit tests:

# Examples

## General Kotlin Check

```kotlin
@Test
fun `classes with 'UseCase' suffix should reside in 'usecase' package`() {
    Konsist.scopeFromProject()
        .classes()
        .withNameEndingWith("UseCase")
        .assert { it.resideInPackage("..usecase..") }
}
```

## Android Specific Check

```kotlin
fun `classes extending 'ViewModel' should have 'ViewModel' suffix`() {
    Konsist.scopeFromProject()
        .classes()
        .withParentClassOf(ViewModel::class)
        .assert { it.name.endsWith("ViewModel") }
}
```

## Spring Specific Check

```kotlin
@Test
fun `interfaces with 'Repository' annotation should have 'Repository' suffix`() {
    Konsist
        .scopeFromProject()
        .interfaces()
        .withAllAnnotationsOf(Repository::class)
        .assert { it.hasNameEndingWith("Repository") }
}
```

# Getting Started

See [Konsist documentation](http://docs.konsist.lemonappdev.com/) for quick start guide and 
[more examples](https://docs.konsist.lemonappdev.com/inspiration/snippets).

## Contributing

Please be sure to review Konsist's [contributing guidelines](https://docs.konsist.lemonappdev.com/help/contributing)
to learn how to support the project.

## Licence

Konsist is distributed under the terms of the Apache License (Version 2.0). See [LICENSE.md](LICENSE) for details.
