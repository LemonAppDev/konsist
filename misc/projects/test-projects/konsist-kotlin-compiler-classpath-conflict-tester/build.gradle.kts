plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.testLogger)
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
    jvmToolchain(25)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    // Non-embeddable Kotlin compiler is declared before Konsist, so it is loaded before 'kotlin-compiler-embeddable'
    // used by Konsist (mimics Android Lint 'intellij-core' or IDE test runner classpath)
    testImplementation(libs.kotlinCompiler)
    testImplementation(libs.konsist)
    testImplementation(libs.junitJupiterEngine)
    testImplementation(libs.kluent)

    testRuntimeOnly(libs.junitPlatformLauncher)
}
