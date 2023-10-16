plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "org.example"
version = "1.1"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(8)
}
