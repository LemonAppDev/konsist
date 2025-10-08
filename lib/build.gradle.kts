plugins {
    id("com.lemonappdev.konsist.convention.base")
    id("com.lemonappdev.konsist.convention.publish")
}

dependencies {
    implementation(libs.kotlinStdlibJdk8)
    implementation(libs.kotlinCompilerEmbeddable)
    implementation(libs.kotlinx.coroutines.core)

    testImplementation(libs.junitJupiterEngine)
    testImplementation(libs.junitJupiterParams)
    testImplementation(libs.mockk)
    testImplementation(libs.kluent)

    testRuntimeOnly(libs.junitPlatformLauncher)
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
                implementation(libs.kotest)

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
                implementation(libs.kotest)
            }
        }

        register("snippet", JvmTestSuite::class) {
            dependencies {
                implementation(project(":lib")) // Konsist
                implementation(libs.kotest)
            }
        }
    }
}
