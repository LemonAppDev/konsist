import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.springframework.boot)
    alias(libs.plugins.spring.dependencyManagement)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.plugin.spring)
    alias(libs.plugins.spotless)
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.spring.boot.starter.web)
    implementation(libs.jackson)
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlin.stdlib.jdk8)

    testImplementation(libs.spring.boot.starter.test)
    testImplementation(libs.junitJupiterEngine)
    testImplementation(libs.kluent)
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

spotless {
    kotlin {
        ktlint()

        indentWithSpaces()
        endWithNewline()
    }
}
