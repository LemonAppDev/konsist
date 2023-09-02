![Konsist](misc/page-konsist-logo.png)
==========

[![Kotlin](https://img.shields.io/badge/Kotlin-blue.svg?style=flat&logo=kotlin)](https://kotlinlang.org)
![Check Workflow](https://github.com/LemonAppDev/konsist/actions/workflows/check.yml/badge.svg)
[<img src="https://img.shields.io/maven-central/v/com.lemonappdev/konsist?label=Release"/>](https://central.sonatype.com/artifact/com.lemonappdev/konsist)

Konsist is a library that guards the consistency of [Kotlin](https://kotlinlang.org/) projects by promoting the
standardization of the Kotlin codebase. It enforces coding conventions and project architecture. Konsist
tests are written in Kotlin and can be easily integrated with popular testing frameworks such as 
[JUnit4](https://junit.org/junit4/), [JUnit5](https://junit.org/junit5/).

See [Konsist documentation](http://docs.konsist.lemonappdev.com/) for more information.

# Examples

Konsist API reflects the structure of Kotlin code. Konsist guards are written in form of unit tests.

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
@Test
fun `classes extending 'ViewModel' should have 'ViewModel' suffix`() {
    Konsist.scopeFromProject()
        .classes()
        .withAllParentsOf(ViewModel::class)
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

See [Konsist Getting Started](https://docs.konsist.lemonappdev.com/getting-started/gettingstarted) for quick start page
guide and [Snippet](https://docs.konsist.lemonappdev.com/inspiration/snippets) page for more examples.

## Articles

- [Introducing Konsist: A Cutting-Edge Kotlin Linter](https://blog.kotlin-academy.com/introducing-konsist-a-cutting-edge-kotlin-linter-d3ab916a5461)
- [Refactoring Multi-Module Kotlin Project With Konsist](https://medium.com/p/f0de0de59a3d)

## Community & Support

Start the [discussion here on GitHub](https://github.com/LemonAppDev/konsist/discussions) or 
use [#konsist channel](https://kotlinlang.slack.com/archives/C05QG9FD6KS) at kotlinlang Slack Workspace.

# Contributing

Please be sure to review Konsist [contributing guidelines](https://docs.konsist.lemonappdev.com/help/contributing)
to learn how to support the project.

# Licence

Konsist is distributed under the terms of the Apache License (Version 2.0). See [LICENSE.md](LICENSE) for details.
