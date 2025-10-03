plugins {
    alias(libs.plugins.kotlinJvm)
}

kotlin {
    jvmToolchain(21)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    testImplementation(libs.konsist)
    testImplementation(libs.kotest.runner.junit5)
}
