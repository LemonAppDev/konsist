plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.testLogger)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.kotlin.stdlib.jdk8)

    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.3")
}
