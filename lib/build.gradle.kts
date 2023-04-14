import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("local.base")
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
