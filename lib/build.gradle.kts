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

@Suppress("UnstableApiUsage")
testing {
    suites {
        dependencies {
            testImplementation(libs.junitJupiterEngine)
            testImplementation(libs.junitJupiterParams)
        }

        register("integrationTest", JvmTestSuite::class) {
            dependencies {
                implementation(project(":lib")) // Konsist
                implementation(libs.mockk)
                implementation(libs.kluent)
                implementation(libs.koTest)

                // Include JAR to be able to test external parents (generated from sample-external-library project)
                implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("sample-external-library-1.2.jar"))))
            }
        }

        register("konsistTest", JvmTestSuite::class) {
            dependencies {
                implementation(project(":lib")) // Konsist
            }
        }

        register("apiTest", JvmTestSuite::class) {
            dependencies {
                implementation(project(":lib")) // Konsist
                implementation(libs.kluent)
                implementation(libs.koTest)
            }
        }

        register("snippet", JvmTestSuite::class) {
            dependencies {
                implementation(project(":lib")) // Konsist
                implementation(libs.koTest)
            }
        }
    }
}
