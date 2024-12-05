plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
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
    testImplementation("junit:junit:4.13.2")

    // Add Konsist dependency
    testImplementation("com.lemonappdev:konsist:0.17.2")
}
