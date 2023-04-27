import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("local.base")
    id("local.publish")
    id("org.jetbrains.dokka") version "1.8.10"
}

dependencies {
    implementation(libs.kotlin.stdlib.jdk8)
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlin.compiler)
}

@Suppress("UnstableApiUsage")
testing {
    suites {
        dependencies {
            implementation(libs.junitJupiterEngine)
            implementation(libs.junitJupiterParams)
            api(libs.mockk)
            api(libs.kluent)
        }
        register("konsistTest", JvmTestSuite::class) {
            dependencies {
                implementation(project(":lib"))
            }
        }
    }
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    languageVersion = "1.9"
}
