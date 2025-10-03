plugins {
    `java-library`
}

java {
    toolchain {
        // Java 17 == bytecode version 61.0
        languageVersion.set(JavaLanguageVersion.of(17))
    }

    // Generated sources.jar for the library jar
    withSourcesJar()

    // Generated javadoc.jar for the library jar
    withJavadocJar()
}
