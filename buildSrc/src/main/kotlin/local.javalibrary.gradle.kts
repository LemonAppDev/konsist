import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.`java-library`

plugins {
    `java-library`
}

java {
    // Make consist compatible with  Java 1.9
    toolchain {
        // Java 9 == bytecode version 53.0
        languageVersion.set(JavaLanguageVersion.of(9))
    }

    // Generated sources.jar for the library jar
    withSourcesJar()

    // Generated javadoc.jar for the library jar
    withJavadocJar()
}
