# Introduction

Konsist helps to guard [Kotlin](https://kotlinlang.org/) project consistency. It allows to standardise Kotlin codebase
and enforce coding conventions tailored for given project.

## Under The Hood

Konsist is built on top of [Kotlin Compiler Psi](https://github.com/JetBrains/kotlin/tree/master/compiler/psi/src/org/jetbrains/kotlin/psi).
It wraps Kotlin compiler parser and provides a simple API to access Kotlin code declarations. 
Declarations tree mimics the Kotlin code structure:

```mermaid
---
title: Project code base representation
---

flowchart TD
    KoScope
    KoScope---KoFile
    KoFile---KoClass
    KoFile---KoInterface
    KoFile---KoObject
    KoFile---Other["..."]
    KoClass---KoProperty
    KoClass---KoFunction
```

To build declaration tree create instance of the [KoScope.md](KoScope.md) class.

## Project Status

Project is in early stage. it has been used in production, however there are still some minor features missing and API
is not stable yet. 

Konsist roadmap:

```mermaid
%%{init: { 'theme': 'forest', 'timeline': {'disableMulticolor': true} , 'themeVariables': {
'cScale0': '#139113',
'cScale1': '#00ff00',
'cScale2': '#0000ff'
} } }%%
timeline
    title Konsist Roadmap
    Q1 2023 (Canary): Design base APIs
    : Core Library developement
    Q2 2023 Alpha: Implement features
            : Stabilise APIs
            : Create documentation
            : Implement features
    Q3 2023 Beta: Bug fixes
            : Polish documentation
            : API tinkering (minimal changes)
    Q4 2023 (1.0 Release): Release 1.0
    : Wide community driven testing
    2024 (1.x): Implement new Features
    : API improvements 
```
