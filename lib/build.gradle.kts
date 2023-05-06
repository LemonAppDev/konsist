import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("local.base")
    id("local.publish")
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
        register("integrationTest", JvmTestSuite::class) {
            dependencies {
                implementation(project(":lib"))
            }
        }
        register("konsistTest", JvmTestSuite::class) {
            dependencies {
                implementation(project(":lib"))
            }
        }
    }
}
