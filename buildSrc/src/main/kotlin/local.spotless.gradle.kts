import ext.getTomlVersion

plugins {
    id("com.diffplug.spotless")
}

spotless {
    kotlin {
        project.fileTree(project.rootDir) {
            exclude(".gradle/**")
        }

        val ktlintCliVersion = project.getTomlVersion("ktlintCliVersion")
        ktlint(ktlintCliVersion)

        indentWithSpaces()
        endWithNewline()
    }

    // Don't add spotless as dependency for the Gradle's check task to facilitate separated codebase checks
    isEnforceCheck = false
}
