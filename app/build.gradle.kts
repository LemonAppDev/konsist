plugins {
    id("local.spring")
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
