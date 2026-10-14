package com.lemonappdev.konsist.buildlogic

import com.lemonappdev.konsist.buildlogic.config.jvmReleaseFor
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

class KotlinConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "org.jetbrains.kotlin.jvm")

            kotlin {
                @Suppress("detekt.MagicNumber")
                jvmToolchain(25)

                // Keep aligned with "kotlinStdlibVersion" (see DeveloperReadme.md "Kotlin Compatibility")
                compilerOptions {
                    apiVersion.set(KotlinVersion.KOTLIN_2_0)
                    languageVersion.set(KotlinVersion.KOTLIN_2_0)
                }
            }

            // Make Konsist artifact compatible with Java 11 (bytecode version 55.0).
            // Test source sets use Java 17, because JUnit Platform 6 requires it.
            tasks.withType<KotlinJvmCompile>().configureEach {
                val jvmRelease = jvmReleaseFor(name)

                compilerOptions {
                    jvmTarget.set(JvmTarget.fromTarget(jvmRelease.toString()))
                    freeCompilerArgs.addAll("-Xjdk-release=$jvmRelease", "-Xbackend-threads=4")
                }
            }
        }
    }
}

private fun Project.kotlin(action: KotlinJvmProjectExtension.() -> Unit) {
    extensions.configure("kotlin", action)
}
