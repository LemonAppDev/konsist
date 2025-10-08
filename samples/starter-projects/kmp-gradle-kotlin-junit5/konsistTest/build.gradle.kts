plugins {
    alias(libs.plugins.kotlinJvm)
}

kotlin {
    jvmToolchain(21)
}

tasks.withType<Test> {
    // Configure JUnit 5 tests
    useJUnitPlatform()
}

dependencies {
    testImplementation(libs.konsist)
    testImplementation(libs.junit.jupiter.engine)
}
