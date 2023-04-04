import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

@Suppress("DSL_SCOPE_VIOLATION") // Because of IDE bug https://youtrack.jetbrains.com/issue/KTIJ-19370
plugins {
    alias(libs.plugins.springframework.boot)
    alias(libs.plugins.spring.dependencyManagement)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.spring)
    alias(libs.plugins.kotlin.plugin.jpa)
    alias(libs.plugins.spotless)
    id("org.gradle.jvm-test-suite")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.kotlin.stdlib.jdk8)
    implementation(libs.kotlin.reflect)
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.jpa)
    implementation(libs.jacksonKotlin)
    implementation(libs.jacksonJsr310)

    compileOnly(libs.spring.boot.devtools)

    runtimeOnly(libs.h2)

    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.junitJupiterEngine)
    testImplementation(libs.kluent)
    testImplementation(libs.mockk)
}

@Suppress("UnstableApiUsage")
testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter()
        }

        register("integrationTest", JvmTestSuite::class) {
            dependencies {
                implementation(project())
                implementation(libs.kluent)

                implementation(libs.jacksonJsr310)
                implementation(libs.spring.boot.starter.test)
                implementation(libs.spring.boot.starter.web)
            }

            targets {
                all {
                    testTask.configure {
                        shouldRunAfter(test)
                    }
                }
            }
        }

        register("konsistTest", JvmTestSuite::class) {
            dependencies {
                implementation(project())
                implementation(project(":konsist"))
                implementation(libs.kotlin.compiler)
                implementation(libs.spring.boot.starter.jpa)
                implementation(libs.spring.boot.starter.web)

                implementation(libs.kluent)
            }

            targets {
                all {
                    testTask.configure {
                        shouldRunAfter(test)
                    }
                }
            }
        }
    }
}

// Need to be here, so spotless works at root project level "gw spotlessCheck"
spotless {
    kotlin {
        ktlint()

        indentWithSpaces()
        endWithNewline()
    }

    // Don't add spotless as dependency for the Gradle's check task
    isEnforceCheck = false
}

// Required for konsist features preview from Kotlin 1.9
tasks
    .withType<KotlinCompilationTask<*>>()
    .configureEach {
        compilerOptions
            .languageVersion
            .set(KotlinVersion.KOTLIN_1_9)
    }
