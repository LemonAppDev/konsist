# Developer Readme

## Status

| Repository                                                                        | Build Status                                                                                                    |
|-----------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------|
| [Konsist](https://github.com/LemonAppDev/konsist)                                 | ![Check Workflow](https://github.com/LemonAppDev/konsist/actions/workflows/check.yml/badge.svg)                 |
| [Konsist Documentation](https://github.com/LemonAppDev/konsist-documentation)     | -                                                                                                               |

## Publish To Maven Repository

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
4. Create `release/vx.y.z` branch from `main`
5. Update Konsist version
    1. [gradle.properties](gradle.properties) file
    2. [README.md](README.md) file
6. Open the release PR targeting `main` and wait for all checks to pass
7. Merge PR
8. Run `./gradlew publish -Pkonsist.releaseTarget=release` on the `main` branch to release a new version
9. Create a new [GitHub release](https://github.com/LemonAppDev/konsist/releases/new)
    1. set `vx.y.z` as tag version
    2. set `vx.y.z` as release title
10. Update Konsist version in the
    Konsist [Quick Start](https://app.gitbook.com/o/PQj191UX5M2C2XxCZuYO/s/RYeSMx6WDKivnwWx7PdP/getting-started/getting-started)
    docs page
11. (If needed) Run `/scripts/update-snippets.py` snippet to generate PR with updated snippets for Konsist Documentation
12. Merge `main` to `develop`
13. Notify devs who have reported the issue (community link in original ticket)

Respond
fix starter link

## Production Release

1. Merge `main` to `develop`
2. Create `release/vx.y.z` branch from `develop`
3. Open PR targeting `main`
4. Update Konsist version
    1. [gradle.properties](gradle.properties) file
    2. [README.md](README.md) file
5. Open the release PR and wait for all checks to pass
6. Release local artifact and test 3rd party projects using local artifact
   1. [Android-showcase](https://github.com/igorwojda/android-showcase)
   2. Mango (internal)
   3. [CleanArchitectureForAndroid](https://github.com/EranBoudjnah/CleanArchitectureForAndroid)
7. Merge PR
8. Run `./gradlew publish -Pkonsist.releaseTarget=release` on the `main` branch to release a new version
9. Create a new [GitHub release](https://github.com/LemonAppDev/konsist/releases/new)
    1. set `vx.y.z` as tag version
    2. set `vx.y.z` as release title
10. Update Konsist version in the
    Konsist [Quick Start](https://app.gitbook.com/o/PQj191UX5M2C2XxCZuYO/s/RYeSMx6WDKivnwWx7PdP/getting-started/gettingstarted)
    docs page
11. Run `/scripts/update-snippets.py` snippet to generate PR with updated snippets for Konsist Documentation
12. Merge `main` to develop (`release` will have version changed)
13. Notify the community about the release
14. Notify devs who have reported the issue or asked for the ne feature (community link in original ticket)

## Sonatype

- [Nexus Repository Manager](https://s01.oss.sonatype.org/#nexus-search;quick~konsist)
- [Sonatype Jira](https://issues.sonatype.org/secure/Dashboard.jspa)

## Repositories Links

- [mvnrepository.com](https://mvnrepository.com/artifact/com.lemonappdev/konsist/)
- [central.sonatype.com](https://central.sonatype.com/artifact/com.lemonappdev/konsist/)

## Generate KDocs

- `./gradlew dokkaHtml` - generate KDocs in `./lib/build/dokka/html/index.html`

# Naming Conventions

If we create extensions with `vararg` for a property in a declaration class we must check its return type.

## Singular return type

We have two options: this type always exist (like name or path) or is optional (like package or parent class).
In both cases create two extensions:

- with prefix 'with' and the name of the property in the singular number
    - Such extension filters all objects in which this property complies with one of the given conditions
- with prefix 'without' and the name of the property in the singular number
    - Such extension filters all objects in which this property not complies with any of the given conditions

### Type always exist
E.g. In `KoClassDeclaration`, the `name` property returns `String` (singular - it's one object),
so we create two extensions:
- `withName(name: String, vararg names: String)`
- `withoutName(name: String, vararg names: String)`

### Type is optional
E.g. In `KoClassDeclaration`, the `parentClass` property returns `KoParentDeclaration` (singular - it's one object), 
so we create two extensions:
    - `withParentClass(vararg names: String)`
    - `withoutParentClass(vararg names: String)`

The difference is that in the first case we force passing the parameter, in the second it is optional.

## Return type is a list of objects

Create six extensions:
- with prefix 'with' and the name of the property in the plural number
  - Such extension filters all objects in which this property complies with any condition
- with prefix 'withAll' and the name of the property in the plural number
  - Such extension filters all objects in which this property complies with all the given conditions
- with prefix 'withSome' and the name of the property in the plural number
  - Such extension filters all objects in which this property complies with at least one of the given conditions
- with prefix 'without' and the name of the property in the plural number
    - Such extension filters all objects in which this property not complies with any condition
- with prefix 'withoutAll' and the name of the property in the plural number
  - Such extension filters all objects in which this property not complies with all the given conditions
- with prefix 'withoutSome' and the name of the property in the plural number
    - Such extension filters all objects in which this property not complies with at least one of the given conditions

E.g. In `KoClassDeclaration`, the `parentInterfaces` property returns `List<KoParentDeclaration>` (plural - it's list 
of objects), so we create three extensions:
- `withParentInterfaces()`
- `withAllParentInterfaces(name: String, vararg names: String)`
- `withSomeParentInterfaces(name: String, vararg names: String)`
- `withoutParentInterfaces()`
- `withoutAllParentInterfaces(name: String, vararg names: String)`
- `withoutSomeParentInterfaces(name: String, vararg names: String)`

Note that there are no parameters with the `with` and `without` prefixes, and in other cases we force the parameter 
to be passed.

## If parameters of extensions is of KClass type, then extension must have suffix 'Of'.

E.g. In `KoClassDeclaration`:
- `withParentClassOf(vararg names: KClass<*>)`
- `withoutParentClassOf(vararg names: KClass<*>)`

- `withAllParentInterfacesOf(name: KClass<*>, vararg names: KClass<*>)`
- `withSomeParentInterfacesOf(name: KClass<*>, vararg names: KClass<*>)`
- `withoutAllParentInterfacesOf(name: KClass<*>, vararg names: KClass<*>)`
- `withoutSomeParentInterfacesOf(name: KClass<*>, vararg names: KClass<*>)`

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
