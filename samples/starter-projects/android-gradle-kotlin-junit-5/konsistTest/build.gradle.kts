plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("de.mannodermaus.android-junit5") version "1.11.2.0"
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
        jvmToolchain(11)
    }
}

dependencies {
    // Add Appcompat dependency (to be able to access Android specific classes in Konsit tests)
    implementation("androidx.appcompat:appcompat:1.7.0")

    // Add JUnit dependency
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.11.3")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.11.3")

    // Add Konsist dependency
    testImplementation("com.lemonappdev:konsist:0.17.0")
}
