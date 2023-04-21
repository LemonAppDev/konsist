---
description: Access the Kotlin files
---

# Scope

The [KoScope](https://github.com/LemonAppDev/konsist/blob/main/src/main/kotlin/com/lemon/konsist/core/declaration/KoScope.kt) class is the entry point to the Konsist library. It is the first step in defining the Konsist test. Scope represents a set of Kotlin files to be further queried, filtered ([declaration-quering-and-filtering.md](declaration-quering-and-filtering.md "mention")) and verified ([assert.md](assert.md "mention")).

```mermaid
flowchart TB
    Step1["1. Retrieve The Scope"]-->Step2
    Step2["2. Query and Filter The Declarations"]-->Step3
    Step3["3. Assert"]
    style Step1 fill:#bbf,stroke:#666,stroke-width:2px,color:#fff
```

Every scope consists set of declarations ([declaration.md](declaration.md "mention")). The scope can be created for a single Kotlin file, folder, package, module, or entire project.

Consider this scope:

```mermaid
---
title: Kotlin code base representation
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

## Scope Creation

The `KoScope` class allows creating scope containing all Kotlin files present in the project:

```kotlin
KoScope.fromProjectFiles() // All Kotlin files present in the project
KoScope.fromProjectFiles(module = "app") // All Kotlin files present in the "app" module
KoScope.fromProjectFiles(sourceSet = "test") // All Kotlin files present in the "test" source sets
```

The `module` and `sourceSet` arguments allow to create of more granular scopes.

### More Granular Scopes

More granular scopes such as module scope or package scope can be defined to store different subsets of project files e.g.

* scope representing production code
* scope representing for test code
* scope representing specific application layer
* ...

Here is an example of creating scopes for production code and test code:

```kotlin
KoScope.fromProjectTestFiles() // All Kotlin files present test source sets
KoScope.fromProjectProductionFiles() // All Kotlin files present production source sets
```

Here is an example of creating scope for all files stored in `usecase` package:

```kotlin
val myScope = KoScope.fromPackage("..usecase..")
```

> You can read more about package selector in [PackageSelector.md](packageselector.md).

Here is an example of creating scope for all files stored in `domain` folder\`:

```kotlin
val myScope = KoScope.fromPath("/domain")
```

It is also possible to create scope from a single file:

```kotlin
val myScope = KoScope.fromFile("/domain/UseCase.kt")
```

For even more granular control you can use the `KoScope.slice` method to retrieve a scope containing a subset of files from the scope:

```kotlin
// scope containing all files in the 'test' folder
koScope.slice { it.relativePath.contains("/test/") }

// scope containing all files in 'com.domain.usecase' package
koScope.slice { it.hasImport("com.domain.usecase") }

// scope containing all files in 'usecase' package and its sub-packages
koScope.slice { it.hasImport("usecase..") }
```

The `KoScope` can be printed to display a list of all files present in the scope. Here is an example:

```kotlin
println(koScope)
```

## Scope Reuse

Scopes should be reused across tests to improve test performance. Avoid creating scope for every individual test:

<pre class="language-kotlin"><code class="lang-kotlin">// Test.kt
class DataTest {
<strong>    @Test
</strong>    fun `test 1`() {
        KoScope
            .fromProject() // Create a new KoScope
            .classes()
            .assert { // .. } 
    }

    fun `test 2`() {
        KoScope
            .fromProject() // Create a new KoScope
            .classes()
            .assert { // .. } 
    }
}
</code></pre>

It is advised to share scope instances as much as possible. One way would be to create the instance in object and access it from multiple tests:

```kotlin
// DataTest.kt
class DataTest {    
    @Test
    fun `test 1`() {
        projectScope
            .classes()
            .assert { // .. } 
    }

    fun `test 2`() {
        projectScope
            .interfaces()
            .assert { // .. } 
    }
}

// AppTest.kt
class AppTest {    
    @Test
    fun `test 1`() {
        projectScope
            .objects()
            .assert { // .. } 
    }
}

// ScopeProvider.kt
object {
    val projectScope = KoScope.fromProject() // Create a new KoScope
}
```

Here is the file structure representing the above snippet:

```
tests/
├─ data/
│  ├─ DataTest.kt
├─ app/
│  ├─ AppTest.kt
├─ ScopeProvider.kt   <--- Instance of the KoScope used in both DataTest and AppTest classes.
```

## Scope Composition

It is possible to compose scopes using Kotlin operators:

```kotlin
// add scopes
val allKoScope = productionScope + testScope

// subtract scopes
val outerLayersScope = allLayersScope - domainLayerScope
```

## Access Specific Declarations

To access specific declaration types such as interfaces, classes, constructors, functions etc. utilize the [declaration-quering-and-filtering.md](declaration-quering-and-filtering.md "mention").
