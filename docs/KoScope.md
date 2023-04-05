# PackageSelector

Package selector is used to

`KoScope` is a container for all the files to be analyzed by Konsist. It is a Kotlin class that contains a list of
`KoFile` objects.

Scopes should be reused across tests. rather than creating scope for every test. This will improve the performance of
konsist tests.

```
tests/
├─ data/
│  ├─ DataTest.kt
├─ app/
│  ├─ AppTest.kt
├─ MyScope.kt   <--- Instance of the KoScope used in both DataTest and AppTest

```

## Scope creation

The `KoScope` class allows to create scope containing all Kotlin files present in the project:

```kotlin
val myScope = KoScope.fromProject()

```

## More Granular Scopes

It is also possible to create more granular scopes to store different subsets of project files e.g.
- scope representing for production code
- scope representing for test code
- scope representing specific application layer
- ...

Here is an example of creating scope for all files stored in `usecase` package:

```kotlin
val myScope = KoScope.fromPackage("..usecase..")
```

You can read more about package selector in [PackageSelector.md](PackageSelector.md).

Here is an example of creating scope for all files stored in `domain` folder`:

```kotlin
val myScope = KoScope.fromPath("/domain")
```

It is also possible to create scope from a single file:

```kotlin
val myScope = KoScope.fromFile("/domain/UseCase.kt")
``` 

For even more granular control you can use `KoScope.slice` method to retrieve a scope containing subset of files 
from the scope:

```kotlin
// scope containing all files in "test" folder
koScope.slice { it.relativePath.contains("/test/") }

// scope containing all files in "com.domain.usecase" package
koScope.slice { it.hasImport("com.domain.usecase") }

// scope containing all files in "usecase" package and its subpackages
koScope.slice { it.hasImport("usecase..") }
```

## Scope composition

It is possible to compose scopes using Kotlin operators:

```kotlin
// add scopes
val allKoScope = productionScope + testScope

// subtract scopes
val outerLayersScope = alllayersScope - domainScope
```
