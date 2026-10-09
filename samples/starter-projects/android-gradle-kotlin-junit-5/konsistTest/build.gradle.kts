plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.junit)
}

android {
    namespace = "com.sample"

    compileSdk = 37

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    kotlin {
        jvmToolchain(17)
    }
}

dependencies {
    // Add Appcompat dependency (to be able to access Android specific classes in Konsist tests)
    implementation(libs.androidx.appcompat)

    // Add JUnit dependency
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)

    // Add Konsist dependency
    testImplementation(libs.konsist)
}
