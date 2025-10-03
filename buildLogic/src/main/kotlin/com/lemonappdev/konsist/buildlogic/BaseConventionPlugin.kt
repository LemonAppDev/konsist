package com.lemonappdev.konsist.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.repositories

class BaseConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply<KotlinConventionPlugin>()
            apply<SpotlessConventionPlugin>()
            apply<TestLoggerConventionPlugin>()
            apply<JavaLibraryConventionPlugin>()
            apply<DokkaConventionPlugin>()

            repositories {
                mavenCentral()
            }
        }
    }
}