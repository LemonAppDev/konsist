plugins {
    alias(libs.plugins.springBoot)
    alias(libs.plugins.dependencyManagement)
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.kotlinSpring)
    alias(libs.plugins.jvmTestSuite)
}

group = "com.sample"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    // The 'api' is used, so classes are available in the konsistTest source set
    api(libs.spring.boot.starter)

    implementation(libs.kotlin.reflect)

    testImplementation(libs.spring.boot.starter.test)
}

kotlin {
    jvmToolchain(21)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.withType<Test> {
    useJUnitPlatform()
}

testing {
    suites {
        register("konsistTest", JvmTestSuite::class) {
            dependencies {
                // Add 'main' source set dependency
                implementation(project())

                // Add Konsist dependency
                implementation(libs.konsist)

                // Add junit-jupiter-params dependency (required for dynamic Tests)
                implementation(libs.junit.jupiter.params)
            }
        }
    }
}

// Optional block to run Konsist tests together with the Gradle 'check' task
tasks.named("check") {
    dependsOn(testing.suites.named("konsistTest"))
}
