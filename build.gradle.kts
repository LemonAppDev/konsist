import io.gitlab.arturbosch.detekt.Detekt
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("DSL_SCOPE_VIOLATION") // Because of IDE bug https://youtrack.jetbrains.com/issue/KTIJ-19370
plugins {
    alias(libs.plugins.springframework.boot)
    alias(libs.plugins.spring.dependencyManagement)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.spring)
    alias(libs.plugins.spotless)
    alias(libs.plugins.testLogger)
    alias(libs.plugins.detekt)
    id("org.gradle.jvm-test-suite")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.spring.boot.starter.web)
    implementation(libs.jacksonKotlin)
    implementation(libs.jacksonJsr310)
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlin.stdlib.jdk8)

    compileOnly(libs.spring.boot.devtools)

    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.junitJupiterEngine)
    testImplementation(libs.kluent)
    testImplementation(libs.mockk)
    testImplementation(libs.archunit)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// ================= Integration tests source set =================
testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter()
        }

        register("integrationTest", JvmTestSuite::class) {
            dependencies {
                implementation(project())
                implementation(libs.kluent)
                implementation(libs.jacksonJsr310)
                implementation(libs.spring.boot.starter.test)
                implementation(libs.spring.boot.starter.web)
            }

            targets {
                all {
                    testTask.configure {
                        shouldRunAfter(test)
                    }
                }
            }
        }
    }
}

tasks.named("check") {
    dependsOn(testing.suites.named("integrationTest"))
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

spotless {
    kotlin {
        ktlint()

        indentWithSpaces()
        endWithNewline()
    }
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
