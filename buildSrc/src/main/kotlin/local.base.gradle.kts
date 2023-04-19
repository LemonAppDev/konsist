plugins {
    id("local.kotlin")
    id("local.spotless")
    id("local.test")
    `java-library`
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(19)
}

java {
    // Generated sources.jar for the library jar
    withSourcesJar()

    // Generated javadoc.jar for the library jar
    withJavadocJar()
}
