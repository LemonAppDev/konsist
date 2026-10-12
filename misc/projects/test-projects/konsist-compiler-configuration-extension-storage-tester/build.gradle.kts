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
    // Mimics Spring Boot dependency management - aligns all Kotlin artifacts (including kotlin-compiler-embeddable used
    // by Konsist) with the project Kotlin version
    implementation(enforcedPlatform(libs.kotlinBom))
    implementation(libs.kotlinStdlibJdk8)

    testImplementation(libs.konsist)
    testImplementation(libs.kotlinCompilerEmbeddable) // Allows to verify Kotlin compiler version used at runtime
    testImplementation(libs.junitJupiterEngine)
    testImplementation(libs.kluent)

    testRuntimeOnly(libs.junitPlatformLauncher)
}
