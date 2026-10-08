package com.lemonappdev.konsist.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

class KotlinConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "org.jetbrains.kotlin.jvm")

            kotlin {
                @Suppress("detekt.MagicNumber")
                jvmToolchain(25)

                compilerOptions {
                    // Make Konsist artifact compatible with Java 11 (bytecode version 55.0)
                    jvmTarget.set(JvmTarget.JVM_11)
                    freeCompilerArgs.add("-Xjdk-release=11")
                    apiVersion.set(KotlinVersion.KOTLIN_2_0)
                    languageVersion.set(KotlinVersion.KOTLIN_2_0)
                }
            }
        }
    }
}

private fun Project.kotlin(action: KotlinJvmProjectExtension.() -> Unit) {
    extensions.configure("kotlin", action)
}
