# What is Konsist?

Konsist is a library that helps to guard [Kotlin](https://kotlinlang.org/) project consistency. It facilitates standardization of the Kotlin codebase by enforcing coding conventions and guarding project architecture.&#x20;

Here is a simple test that verifies if every use case class resides in `usecase` package:

```kotlin
@Test
fun `every use case must reside in use case package`() {
    KoScope
        .fromProject() // Define scope using all Kotlin files present in the project
        .classes() // Map to list of classes
        .withNameSuffix("UseCase") // Filter classes heaving name ending with 'UseCase'
        .assert { it.resideInPackages("..usecase..") } // Assert usecase
}
```

Take a look at [gettingstarted.md](getting-started/gettingstarted.md "mention") page to learn how to setup Konsist or go straight to the [Broken link](broken-reference "mention") section to review more examples.&#x20;

