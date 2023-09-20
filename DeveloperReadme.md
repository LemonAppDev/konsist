# Developer Readme

## Status

| Repository                                                                        | Build Status                                                                                                    |
|-----------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------|
| [Konsist](https://github.com/LemonAppDev/konsist)                                 | ![Check Workflow](https://github.com/LemonAppDev/konsist/actions/workflows/check.yml/badge.svg)                 |
| [Konsist Documentation](https://github.com/LemonAppDev/konsist-documentation)     | -                                                                                                               |

## Publish To Maven Repository

- Publish Local: `./gradlew publishToMavenLocal -Pkonsist.releaseTarget=local` publish to local `~/.m2/repository`
- Publish Snapshot `./gradlew publish -Pkonsist.releaseTarget=snapshot` publish to
  [snapshot repository](https://s01.oss.sonatype.org/content/repositories/snapshots/com/lemonappdev/konsist/)
- Publish Release `./gradlew publish -Pkonsist.releaseTarget=release` publish to
  [release repository](https://s01.oss.sonatype.org/content/repositories/releases/com/lemonappdev/konsist/). This
  artefact will be transferred to [maven central](https://central.sonatype.com/artifact/com.lemonappdev/konsist)
  repository after some time.

## Release

1. Merge `main` to `develop`
2. Create `release-vx.y.z` branch from `develop`
3. Open Release PR and wait for all checks to pass
4. Release local artifact and test 3rd party projects using local artifact
   1. [Android-showcase](https://github.com/igorwojda/android-showcase)
   2. Mango (internal)
   3. [CleanArchitectureForAndroid](https://github.com/EranBoudjnah/CleanArchitectureForAndroid)
5. Update Konsist version
   1. [gradle.properties](gradle.properties) file
   2. [README.md](README.md) file
6. Run `./gradlew publish -Pkonsist.releaseTarget=release` to publish to release repository
7. Merge `release-vx.y.z` branch to `main`
8. Create a new [GitHub release](https://github.com/LemonAppDev/konsist/releases/new)
    1. set `vx.y.z` as tag version
    2. set `vx.y.z` as release title
9. Update Konsist version in the Konsist [Quick Start](https://app.gitbook.com/o/PQj191UX5M2C2XxCZuYO/s/RYeSMx6WDKivnwWx7PdP/getting-started/gettingstarted) docs page 
10. Run `/scripts/update-snippets.py` snippet to generate PR with updated snippets for Konsist Documentation
11. Merge release PR to `develop`
12. Merge `main` to develop (`release` will have some changes)
13. Update version on `develop` (to next one)
14. Notify the community

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
