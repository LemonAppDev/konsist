plugins {
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
    kotlin("jvm") version "1.9.20"
    kotlin("plugin.spring") version "1.9.20"
    `jvm-test-suite`
}

group = "com.sample"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_19

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    // The 'api' is used, so classes are available in the konsistTest source set
    api("org.springframework.boot:spring-boot-starter")

    implementation("org.jetbrains.kotlin:kotlin-reflect")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

kotlin {
    jvmToolchain(19)
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
                implementation("com.lemonappdev:konsist:0.13.0")

                // Add KoTest dependency
                implementation("io.kotest:kotest-runner-junit5-jvm:5.8.0")
            }
        }
    }
}

// Optional block to run Konsist tests together with the Gradle 'check' task
tasks.named("check") {
    dependsOn(testing.suites.named("konsistTest"))
}
