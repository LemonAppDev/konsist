@Suppress("DSL_SCOPE_VIOLATION") // Because of IDE bug https://youtrack.jetbrains.com/issue/KTIJ-19370
plugins {
    alias(libs.plugins.kotlinJvm)
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
    jvmToolchain(25)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// Simulates generated code (e.g. KSP, Hilt) to test that Konsist skips build output directories
val generateBuildDirectoryFixture by tasks.registering {
    val outputFile = layout.buildDirectory.file("generated/konsist/com/lemonappdev/fixture/GeneratedClass.kt")
    outputs.file(outputFile)

    doLast {
        outputFile.get().asFile.apply {
            parentFile.mkdirs()
            writeText("package com.lemonappdev.fixture\n\nclass GeneratedClass\n")
        }
    }
}

dependencies {
    implementation(libs.kotlinStdlibJdk8)
}

@Suppress("UnstableApiUsage")
testing {
    suites {
        register("integrationTest", JvmTestSuite::class) {
            dependencies {
                implementation(project(":app"))
                implementation(libs.junitJupiterEngine)
                implementation(libs.kluent)
                implementation(libs.konsist)
            }
        }
    }
}

tasks.named("integrationTest") {
    dependsOn(generateBuildDirectoryFixture)
}
