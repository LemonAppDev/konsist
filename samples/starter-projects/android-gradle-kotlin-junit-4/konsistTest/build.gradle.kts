import org.gradle.kotlin.dsl.androidTestImplementation
import org.gradle.kotlin.dsl.testImplementation

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.sample"

    compileSdk = 36

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
    // Add Appcompat dependency (to be able to access Android specific classes in Konsist tests)
    implementation(libs.androidx.appcompat)

    // Add JUnit dependency
    testImplementation(libs.junit)

    // Add Konsist dependency
    testImplementation(libs.konsist)
}
