plugins {
    id("local.kotlin")
    id("local.spotless")
    id("local.test")
    id("local.javalibrary")
    id("local.dokka")
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(19)
}
