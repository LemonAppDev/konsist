plugins {
    id("com.lemonappdev.konsist.convention.base")
    id("com.lemonappdev.konsist.convention.publish")
}

dependencies {
    implementation(libs.kotlinStdlib)
    implementation(libs.kotlinCompilerEmbeddable) {
        // Compiler depends on kotlin-stdlib of the same version, use the lowest supported version instead
        // (see DeveloperReadme.md "Kotlin Compatibility")
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib")
    }
    implementation(libs.kotlinx.coroutines.core.jvm)

    // Test framework extensions (KoJUnitExtension, KoKotestExtension) are used only when the framework is on the classpath
    compileOnly(libs.junitJupiterApi)
    compileOnly(libs.kotestFrameworkEngine)

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

                // Include JAR to be able to test external parents (generated from fixture-external-library project)
                implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("fixture-external-library-1.2.jar"))))
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

        register("testsnippet", JvmTestSuite::class) {
            dependencies {
                implementation(project(":lib")) // Konsist
                implementation(libs.kotest)
            }
        }
    }
}
