plugins {
    id("local.spring")
}

dependencies {
    implementation(libs.kotlin.stdlib.jdk8)
    implementation(libs.kotlin.reflect)
    implementation(libs.jacksonKotlin)
    api(libs.jacksonJsr310)
    api(libs.spring.boot.starter.web)
    api(libs.spring.boot.starter.jpa)

    compileOnly(libs.spring.boot.devtools)

    runtimeOnly(libs.h2)
}

@Suppress("UnstableApiUsage")
testing {
    suites {
        dependencies {
            api(libs.junitJupiterEngine)
            api(libs.spring.boot.starter.test)
            api(libs.kluent)
            implementation(libs.mockk)
        }
        register("integrationTest", JvmTestSuite::class) {
            dependencies {
                implementation(project())
            }
        }

        register("konsistTest", JvmTestSuite::class) {
            dependencies {
                implementation(project())
                implementation(project(":konsist"))
                implementation(libs.kotlin.compiler)
            }
        }
    }
}
