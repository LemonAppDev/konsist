plugins {
    `java-library`
}

java {
    // Make Konsist artifact compatible with Java 11
    toolchain {
        // Java 11 == bytecode version 55.0
        languageVersion.set(JavaLanguageVersion.of(11))
    }

    // Generated sources.jar for the library jar
    withSourcesJar()

    // Generated javadoc.jar for the library jar
    withJavadocJar()
}
