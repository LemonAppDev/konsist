import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

@Suppress("DSL_SCOPE_VIOLATION") // Because of IDE bug https://youtrack.jetbrains.com/issue/KTIJ-19370
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.spotless)
    alias(libs.plugins.testLogger)
    alias(libs.plugins.detekt)
    idea
}

repositories {
    mavenCentral()
}

subprojects {
    tasks.withType<Test> {
        useJUnitPlatform()

        // Enable parallel test execution
        systemProperties = mapOf(
            "junit.jupiter.execution.parallel.enabled" to "true",
            "junit.jupiter.execution.parallel.mode.default " to "concurrent",
        )
    }

    // Required for konsist to use Kotlin 1.9 preview features
    tasks
        .withType<KotlinCompilationTask<*>> {
            compilerOptions
                .languageVersion
                .set(KotlinVersion.KOTLIN_1_9)
        }
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

kotlin {
    jvmToolchain(19)
}

spotless {
    kotlin {
        ktlint()

        indentWithSpaces()
        endWithNewline()
    }

    // Don't add spotless as dependency for the Gradle's check task
    isEnforceCheck = false
}

val detektCheck by tasks.registering(Detekt::class) {
    description = "Checks that sourcecode satisfies detekt rules."
    autoCorrect = false
}

val detektApply by tasks.registering(Detekt::class) {
    description = "Applies code formatting rules to sourcecode in-place."
    autoCorrect = true
}

configure(listOf(detektCheck, detektApply)) {
    configure {
        group = "verification"
        parallel = true
        ignoreFailures = false
        setSource(file(projectDir))

        // Custom detekt config
        config.setFrom("$projectDir/detekt.yml")

        // Use default configuration on top of custom config
        // (new detect rules will work out of the box after upgrading detekt version)
        buildUponDefaultConfig = true

        /*
        Runs detekt for all files in the Gradle project and all subprojects without
        a need to configure detekt plugin in every subproject.
         */
        include("**/*.kt", "**/*.kts")
        exclude("**/resources/**", "**/build/**", "**/generated/**")

        reports {
            html.required.set(true)
            xml.required.set(true)
        }
    }
}
