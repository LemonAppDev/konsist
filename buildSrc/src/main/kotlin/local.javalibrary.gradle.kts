plugins {
    `java-library`
}

java {
    // Make Konsist artifact compatible with Java 1.8
    toolchain {
        // Java 8 == bytecode version 52.0
        languageVersion.set(JavaLanguageVersion.of(8))
    }

    // Generated sources.jar for the library jar
    withSourcesJar()

    // Generated javadoc.jar for the library jar
    withJavadocJar()
}
