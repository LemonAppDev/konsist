plugins {
    id("com.diffplug.spotless")
}

spotless {
    kotlin {
        project.fileTree(project.rootDir) {
            exclude(".gradle/**")
        }

        ktlint("1.2.1")

        indentWithSpaces()
        endWithNewline()
    }

    // Don't add spotless as dependency for the Gradle's check task to facilitate separated codebase checks
    isEnforceCheck = false
}
