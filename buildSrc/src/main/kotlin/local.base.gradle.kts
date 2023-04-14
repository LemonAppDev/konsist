plugins {
    id("local.kotlin")
    id("local.spotless")
    id("local.test")
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(19)
}
