# Developer Readme

## Status

| Repository                                                                        | Build Status                                                                                                    |
|-----------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------|
| [Konsist](https://github.com/LemonAppDev/konsist)                                 | ![Check Workflow](https://github.com/LemonAppDev/konsist/actions/workflows/check.yml/badge.svg)                 |
| [Konsist Documentation](https://github.com/LemonAppDev/konsist-documentation)     | -                                                                                                               |

## Publish Konsist Artifact To Maven Repository

- Publish Local Snapshot: `./gradlew publishToMavenLocal -Pkonsist.releaseTarget=local` publish to local
- `~/.m2/repository`
- Publish Public Snapshot `./gradlew publish -Pkonsist.releaseTarget=snapshot` publish to
  [snapshot repository](https://s01.oss.sonatype.org/content/repositories/snapshots/com/lemonappdev/konsist/)
- Publish Release `./gradlew publish -Pkonsist.releaseTarget=release` publish to
  [release repository](https://s01.oss.sonatype.org/content/repositories/releases/com/lemonappdev/konsist/). This
  artefact will be transferred to [maven central](https://central.sonatype.com/artifact/com.lemonappdev/konsist)
  repository after some time.

## Hotfix Release

1. Create `hotfix/KON-XXX-...` branch from `main`
2. Fix the bug and open PR targeting `mian`
3. Merge the PR
4. Create `release/vX.Y.Z` branch from `main`
5. Update Konsist version
    1. [gradle.properties](gradle.properties) file
    2. [README.md](README.md) file
    3. Sample projects
6. Open the release PR targeting `main` and wait for all checks to pass
7. Merge PR
8. Switch to the `main` branch and pull changes
9. Run `./gradlew publish -Pkonsist.releaseTarget=release` on the `main` branch to release a new version
10. Create a new [GitHub release](https://github.com/LemonAppDev/konsist/releases/new)
    1. set `vX.Y.Z` as tag version
    2. set `vX.Y.Z` as release title
11. Update Konsist version in the
    Konsist [Quick Start](https://app.gitbook.com/o/PQj191UX5M2C2XxCZuYO/s/RYeSMx6WDKivnwWx7PdP/getting-started/getting-started)
    docs page
12. Run `/scripts/update-snippets.py` snippet to generate PR with updated snippets for Konsist Documentation
13. Merge `main` to `develop`
14. Upgrade `konist` version on `develop` branch
15. Notify devs who have reported the issue (community link in original ticket)

Respond
fix starter link

## Production Release

1. Merge `main` to `develop`
2. Create `release/vX.Y.Z` branch from `develop`
3. Update Konsist version
    1. [gradle.properties](gradle.properties) file
    2. [README.md](README.md) file
4. Remove deprecated API
5. Open PR targeting `main` and wait for all checks to pass
6. Release local artifact and test 3rd party projects using local artifact
    1. [Android-showcase](https://github.com/igorwojda/android-showcase)
    2. Mango (internal)
    3. [CleanArchitectureForAndroid](https://github.com/EranBoudjnah/CleanArchitectureForAndroid)
7. Merge PR to `main`
8. Run `./gradlew publish -Pkonsist.releaseTarget=release` on the `main` branch to release a new version
9. Create a new [GitHub release](https://github.com/LemonAppDev/konsist/releases/new)
    1. set `vX.Y.Z` as tag version
    2. set `vX.Y.Z` as release title
10. Update Konsist version in the
    Konsist [Quick Start](https://app.gitbook.com/o/PQj191UX5M2C2XxCZuYO/s/RYeSMx6WDKivnwWx7PdP/getting-started/gettingstarted)
    docs page
11. Run `/scripts/deploy_snippets_to_kotlin_documentation_repo.py` snippet to generate PR with updated snippets for
    Konsist Documentation
12. Merge `main` to `develop` (`release` branch will have changes e.g. version, deprecated APi removed...)
13. Notify the community about the release
14. Notify devs who have reported the issue or asked for the new feature (community links in original ticket)

## Sonatype

- [Nexus Repository Manager](https://s01.oss.sonatype.org/#nexus-search;quick~konsist)
- [Sonatype Jira](https://issues.sonatype.org/secure/Dashboard.jspa)

## Repositories Links

- [mvnrepository.com](https://mvnrepository.com/artifact/com.lemonappdev/konsist/)
- [central.sonatype.com](https://central.sonatype.com/artifact/com.lemonappdev/konsist/)

## Generate KDocs

- `./gradlew dokkaHtml` - generate KDocs in `./lib/build/dokka/html/index.html`

## Naming Conventions

### Naming Conventions For Providers With Property With `List<KoXDeclaration>` Type

We have three options:
- `KoXDeclaration` not implements `KoNameProvider`  (e.g. `KoBaseDeclaration`, `KoInitBlockDeclaration`)
- `KoXDeclaration` implements `KoNameProvider`(so it has access to `name` property, e.g. `KoClassDeclaration`,
  `KoFunctionDeclaration`)
- some exceptions, like: `KoKDocTagDeclaration`, `KoModifier` etc.

#### Option 1: `KoXDeclaration` not implements `KoNameProvider`
In provider with property with type `List<KoXDeclaration>` where `KoXDeclaration` not implements `KoNameProvider` we create:
- properties
    - with prefix `num` and the name of the X item in the plural number
        - Specifies how many items there are.
- functions
    - with prefix `count` and the name of the X item in the plural number with `predicate` lambda parameter
        - Specifies how many items satisfies given predicate.
    - with prefix `has` and the name of the X item in the plural number
        - Specifies whether declaration has any item.
    - with prefix `has` and the name of the X item in the singular number with `predicate` lambda parameter
        - Specifies whether declaration has at least one item that satisfies given predicate.
    - with prefix `hasAll` and the name of the X item in the plural number with `predicate` lambda parameter
        - Specifies whether declaration has all items that satisfies given predicate.

#### Option 2: `KoXDeclaration` implements `KoNameProvider`
In provider with property with type `List<KoXDeclaration>` where `KoXDeclaration` implements `KoNameProvider` we create
all properties and functions from the `Option 1` and also:
- functions
    - with prefix `has`, the name of the X item in the singular number and suffix `WithName` and with
      parameters: `(name: String, vararg names: String)`
        - Specifies whether the declaration has at least one item whose name matches any of the specified names
    - with prefix `has` and the name of the X item in the plural number and suffix `WithAllNames` and with
      parameters: `(name: String, vararg names: String)`
        - Specifies whether the declaration has items with all the specified names

#### Option 3: Some exceptions, like `KoModifierProvider` or `KoKDocTagProvider`
In this option we have providers for which it makes no sense to pass `predicate` lambda parameter, especially if the
list contains enum values. Instead of passing lambda parameter, we pass concrete enums.
- properties
    - with prefix `num` and the name of the X item in the plural number
        - Specifies how many items there are.
- functions
    - with prefix `has` and the name of the X item in the plural number
        - Specifies whether declaration has any item.
    - with prefix `has` and the name of the X item in the singular number with
      parameters: `(koXDeclaration: KoXDeclaration, vararg koXDeclarations: KoXDeclaration)`
        - Specifies whether declaration has at least one specified item.
    - with prefix `hasAll` and the name of the X item in the plural number with
      parameters: `(koXDeclaration: KoXDeclaration, vararg koXDeclarations: KoXDeclaration)`
        - Specifies whether declaration has all specified items.

### Naming Conventions For List Extensions When Provider Has Property With `List<KoXDeclaration>` Type
We have the same three options like above.

#### Option 1: `KoXDeclaration` not implements `KoNameProvider`
For providers with property with type `List<KoXDeclaration>` where `KoXDeclaration` not implements `KoNameProvider` we
create extensions:
- properties
    - with the name of the X item in the plural number
        - Mapping declaration to its items.
- functions
    - with prefix `with/without` and the name of the X item in the plural number
        - Filtering declarations with/without any item.
    - with prefix `with/without` and the name of the X item in the singular number with `predicate` lambda parameter
      (to lambda we pass `KoXDeclaration`)
        - Filtering declarations that have at least one/ not have item satisfying the provided predicate.
    - with prefix `withAll/withoutAll` and the name of the X item in the plural number with `predicate` lambda parameter
      (to lambda we pass `KoXDeclaration`)
        - Filtering declarations that have all/have at least one item satisfying the provided predicate.
    - with prefix `with/without` and the name of the X item in the plural number with `predicate` lambda parameter
      (to lambda we pass `List<KoXDeclaration>`)
        - Filtering declarations with/without items satisfying the provided predicate.

#### Option 2: `KoXDeclaration` implements `KoNameProvider`
For providers with property with type `List<KoXDeclaration>` where `KoXDeclaration` implements `KoNameProvider` we create
all properties and functions extensions from the `Option 1` and also:
- functions
    - with prefix `with/without`, the name of the X item in the singular number and suffix `Named` and with
      parameters: `(name: String, vararg names: String)`
        - Filtering declarations that have at least one/ not have item with the specified name(s)
    - with prefix `withAll/withoutAll` and the name of the X item in the plural number and suffix `Named` and with
      parameters: `(name: String, vararg names: String)`
        - Filtering declarations that have all/ not have any items with the specified name(s)

#### Option 3: Some exceptions, like `KoModifierProvider` or `KoKDocTagProvider`
For such providers we create extensions:
- properties
    - with the name of the X item in the plural number
        - Mapping declaration to its items.
- functions
    - with prefix `with/without` and the name of the X item in the plural number
        - Filtering declarations with/without any item.
    - with prefix `with/without`, the name of the X item in the singular number with
      parameters: `(koXDeclaration: KoXDeclaration, vararg koXDeclarations: KoXDeclaration)`
        - Filtering declarations that have at least one/not have the specified item
    - with prefix `withAll/withoutAll`, the name of the X item in the plural number with
      parameters: `(koXDeclaration: KoXDeclaration, vararg koXDeclarations: KoXDeclaration)`
        - Filtering declarations that have all/not have any the specified item

### Naming Conventions For List Extensions When Provider Has Property With `KoXDeclaration` Type
Examples of such providers: `KoTypeProvider`, `KoReturnTypeProvider`.
We create extensions:
- properties
    - with the name of the X item in the plural number
        - Mapping declaration to its item.
- functions
    - with prefix `with/without` and the name of the property in the singular number with `predicate` lambda parameter
        - Filtering declarations that have/ not have item satisfying the provided predicate.

### Naming Conventions For List Extensions When Provider Has Property With Other Singular Type
#### Type always exist
E.g. In `KoNameProvider`, the `name` property returns `String` (singular - it's one object),
so we create two extensions:
- `withName(name: String, vararg names: String)`
- `withoutName(name: String, vararg names: String)`

and if it makes sense:
- `withName(predicate: (String) -> Boolean)`
- `withoutName(predicate: (String) -> Boolean)`

#### Type is optional
E.g. In `KoAliasProvider`, the `alias` property returns `String?` (singular - it's one object:
`String` or null ),
so we create two extensions:
- `withAlias(vararg names: String)`
- `withoutAlias(vararg names: String)`

#### The difference is that in the first case we force passing the parameter, in the second it is optional.

### If parameters of extensions is of `KClass` type, then we create analogous extensions like for providers not implementing `KoNameProvider` but with suffix `Of`!

## Build Errors

Error
```
No matching variant of project :buildSrc was found. The consumer was configured to find a library for use during runtime, compatible with Java 17, packaged as a jar,
```

Fix
Change Gradle version `File -> Settings -> Build, Execution, Deployment -> Build Tools -> Gradle`

## Assert method - test method name

We take the test method name from `Thread.currentThread().stackTrace[index].methodName` which has a different behavior
due to the way `assert` is called (depending on whether we use default parameters values or not in the `assertTrue`
or `assertFalse` method).

- If we don't use any of the default parameters (we set them directly) the stack trace looks like this:
  ![Pasted Graphic 3.png](..%2F..%2FLibrary%2FGroup%20Containers%2Fgroup.com.apple.notes%2FAccounts%2FLocalAccount%2FMedia%2FC395A466-A547-4F90-8315-48F28F039D5D%2FPasted%20Graphic%203.png)
  So the test method name is at the fourth index.
- If we use any parameter with a default value (we don't set any of them directly) the stack trace looks like this:
  ![Pasted Graphic 2.png](..%2F..%2FLibrary%2FGroup%20Containers%2Fgroup.com.apple.notes%2FAccounts%2FLocalAccount%2FMedia%2F8EBEB1ED-C419-4899-8B2D-76297AE0A8AE%2FPasted%20Graphic%202.png)
  So the test method name is at the fifth index.
