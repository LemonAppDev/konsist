@Suppress("DSL_SCOPE_VIOLATION") // Because of IDE bug https://youtrack.jetbrains.com/issue/KTIJ-19370
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.testLogger)
    id("org.gradle.jvm-test-suite")
}

repositories {
    mavenCentral()

    // Konsist artifact can be only retrieved from mavenLocal repository
    exclusiveContent {
        forRepository {
            mavenLocal()
        }
        filter {
            // This repository exclusively provides konsist artifact
            includeModule("com.lemonappdev", "konsist")
        }
    }
}

kotlin {
    jvmToolchain(21)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation(libs.kotlin.stdlib.jdk8)
}

@Suppress("UnstableApiUsage")
testing {
    suites {
        register("integrationTest", JvmTestSuite::class) {
            dependencies {
                implementation(libs.junitJupiterEngine)
                implementation(libs.kluent)
                implementation(libs.konsist)
            }
        }
    }
}



