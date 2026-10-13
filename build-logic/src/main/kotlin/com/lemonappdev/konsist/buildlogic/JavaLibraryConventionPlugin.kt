package com.lemonappdev.konsist.buildlogic

import com.lemonappdev.konsist.buildlogic.config.jvmReleaseFor
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType

class JavaLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "java-library")

            configure<JavaPluginExtension> {
                // Generated sources.jar for the library jar
                withSourcesJar()

                // Generated javadoc.jar for the library jar
                withJavadocJar()
            }

            // Make Konsist artifact compatible with Java 11 (bytecode version 55.0).
            // Test source sets use Java 17, because JUnit Platform 6 requires it.
            tasks.withType<JavaCompile>().configureEach {
                options.release.set(jvmReleaseFor(name))
            }
        }
    }
}
