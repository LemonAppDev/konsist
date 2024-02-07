plugins {
    alias(libs.plugins.kotlin.jvm)
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
    jvmToolchain(21)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation(libs.kotlin.stdlib.jdk8)

    testImplementation(libs.konsist)
    testImplementation(libs.junitJupiterEngine)
    testImplementation(libs.kluent)
}
