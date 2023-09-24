plugins {
    id("local.base")
    id("local.publish")
}

dependencies {
    implementation(libs.kotlin.stdlib.jdk8)
    implementation(libs.kotlin.compiler)

    testImplementation(libs.junitJupiterEngine)
    testImplementation(libs.junitJupiterParams)
    testImplementation(libs.mockk)
    testImplementation(libs.kluent)
}

kotlin {
    jvmToolchain(19)
}

@Suppress("UnstableApiUsage")
testing {
    suites {
        dependencies {
            testImplementation(libs.junitJupiterEngine)
            testImplementation(libs.junitJupiterParams)
        }

        register("integrationTest", JvmTestSuite::class) {
            dependencies {
                implementation(project(":lib"))
                implementation(libs.mockk)
                implementation(libs.kluent)
                implementation(libs.koTest)
            }
        }

        register("konsistTest", JvmTestSuite::class) {
            dependencies {
                implementation(project(":lib"))
            }
        }

        register("apiTest", JvmTestSuite::class) {
            dependencies {
                implementation(project(":lib"))
                implementation(libs.kluent)
            }
        }

        register("snippet", JvmTestSuite::class) {
            dependencies {
                implementation(project(":lib"))
            }
        }
    }
}
