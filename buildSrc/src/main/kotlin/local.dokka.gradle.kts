import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    id("org.jetbrains.dokka")
}

// Dokka config
// https://kotlinlang.org/docs/dokka-gradle.html#package-options
tasks.withType<DokkaTask>().configureEach {
    moduleName.set("Konsist")
    failOnWarning.set(true)

    dokkaSourceSets.configureEach {
        // Generate docs for 'api'
        perPackageOption {
            matchingRegex.set("com.lemonappdev.konsist.api.*")
            suppress.set(false)
        }

        // Don't generate docs for all packages
        perPackageOption {
            matchingRegex.set(".*")
            suppress.set(true)
        }
    }
}
