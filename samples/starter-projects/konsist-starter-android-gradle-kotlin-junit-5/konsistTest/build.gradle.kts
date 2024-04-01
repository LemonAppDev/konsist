plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("de.mannodermaus.android-junit5") version "1.10.0.0"
}

android {
    namespace = "com.sample"

    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    kotlin {
        jvmToolchain(8)
    }
}

dependencies {
    // Add Appcompat dependency (to be able to access Android specific classes in Konsit tests)
    implementation("androidx.appcompat:appcompat:1.6.1")

    // Add JUnit dependency
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.2")

    // Add Konsist dependency
    testImplementation("com.lemonappdev:konsist:0.15.0")
}
