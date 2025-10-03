package com.lemonappdev.konsist.buildlogic

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class JavaLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "java-library")

            configure<JavaPluginExtension> {
                // Make Konsist artifact compatible with Java 11 (bytecode version 55.0)
                toolchain {
                    @Suppress("detekt.MagicNumber")
                    languageVersion.set(JavaLanguageVersion.of(11))
                }

                // Generated sources.jar for the library jar
                withSourcesJar()

                /// Generated javadoc.jar for the library jar
                withJavadocJar()
            }
        }
    }
}
