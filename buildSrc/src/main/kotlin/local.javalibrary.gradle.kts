plugins {
    `java-library`
}

java {
    // Make consist compatible with  Java 1.8
    toolchain {
        // Java bytecode version to 52.0
        languageVersion.set(JavaLanguageVersion.of(8))
    }

    // Generated sources.jar for the library jar
    withSourcesJar()

    // Generated javadoc.jar for the library jar
    withJavadocJar()
}
