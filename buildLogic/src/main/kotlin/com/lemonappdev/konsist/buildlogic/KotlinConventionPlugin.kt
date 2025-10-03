package com.lemonappdev.konsist.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

class KotlinConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "org.jetbrains.kotlin.jvm")

            kotlin {
                jvmToolchain(21)

                compilerOptions {
                    apiVersion.set(KotlinVersion.KOTLIN_1_8)
                    languageVersion.set(KotlinVersion.KOTLIN_1_8)
                }
            }
        }
    }
}

private fun Project.kotlin(action: KotlinJvmProjectExtension.() -> Unit) {
    extensions.configure("kotlin", action)
}