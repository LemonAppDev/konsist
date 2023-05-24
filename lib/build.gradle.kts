plugins {
    id("local.base")
    id("local.publish")
}

dependencies {
    implementation(libs.kotlin.stdlib.jdk8)
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlin.compiler)

    implementation(libs.junitJupiterEngine)
    implementation(libs.junitJupiterParams)
    implementation(libs.mockk)
    implementation(libs.kluent)
}

@Suppress("UnstableApiUsage")
testing {
    suites {
        dependencies {
            implementation(libs.junitJupiterEngine)
            implementation(libs.junitJupiterParams)
        }

        register("integrationTest", JvmTestSuite::class) {
            dependencies {
                implementation(project(":lib"))
                implementation(libs.mockk)
                implementation(libs.kluent)
            }
        }

        register("konsistTest", JvmTestSuite::class) {
            dependencies {
                implementation(project(":lib"))
            }
        }
    }
}
