plugins {
    id("local.base")
}

dependencies {
    implementation(libs.kotlin.stdlib.jdk8)
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlin.compiler)

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
