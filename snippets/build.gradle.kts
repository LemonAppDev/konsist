plugins {
    id("local.base")
    id("local.publish")
}

dependencies {
    implementation(project(":lib"))

    implementation(libs.kotlin.stdlib.jdk8)
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlin.compiler)

    testImplementation(libs.junitJupiterEngine)
}