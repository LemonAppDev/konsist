@Suppress("DSL_SCOPE_VIOLATION") // Because of IDE bug https://youtrack.jetbrains.com/issue/KTIJ-19370
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.testLogger)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.kotlin.stdlib.jdk8)

    testImplementation(files("../../../../lib/build/libs/lib.jar"))
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.3")
}
