
import ext.getKonsistVersion
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    id("org.jetbrains.dokka")
}

// Dokka config
// https://kotlinlang.org/docs/dokka-gradle.html#package-options
tasks.withType<DokkaTask>().configureEach {
    moduleName.set("Konsist ${project.getKonsistVersion()}")
    failOnWarning.set(true)

    dokkaSourceSets.configureEach {
        // Generate docs for 'com.lemonappdev.konsist.api' package
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
