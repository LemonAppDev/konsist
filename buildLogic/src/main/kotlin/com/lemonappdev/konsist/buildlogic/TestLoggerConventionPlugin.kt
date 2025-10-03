package com.lemonappdev.konsist.buildlogic

import com.adarshr.gradle.testlogger.TestLoggerExtension
import com.adarshr.gradle.testlogger.theme.ThemeType
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType

class TestLoggerConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "org.gradle.jvm-test-suite")
            apply(plugin = "com.adarshr.test-logger")

            tasks.withType<Test> {
                useJUnitPlatform()

                // Enable parallel test execution
                systemProperties = mapOf(
                    "junit.jupiter.execution.parallel.enabled" to "true",
                    "junit.jupiter.execution.parallel.mode.default " to "concurrent",
                )
            }

            configure<TestLoggerExtension> {
                theme = ThemeType.MOCHA
            }
        }
    }
}
