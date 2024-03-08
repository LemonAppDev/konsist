plugins {
    id("com.diffplug.spotless")
}

spotless {
    kotlin {
        project.fileTree(project.rootDir) {
            exclude(".gradle/**")
        }

        val catalogs = extensions.getByType<VersionCatalogsExtension>()
        val libs = catalogs.named("libs")
        val ktlintCliVersion = libs.findVersion("ktlintCliVersion").get().toString()
        ktlint(ktlintCliVersion)

        indentWithSpaces()
        endWithNewline()
    }

    // Don't add spotless as dependency for the Gradle's check task to facilitate separated codebase checks
    isEnforceCheck = false
}
