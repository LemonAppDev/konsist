package com.lemonappdev.konsist.buildlogic

import com.diffplug.gradle.spotless.SpotlessExtension
import com.lemonappdev.konsist.buildlogic.ext.getTomlVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

class SpotlessConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.diffplug.spotless")

            configure<SpotlessExtension> {
                kotlin {
                    target("**/*.kt")
                    targetExclude("**/build/**/*.kt")

                    val ktlintCliVersion = project.getTomlVersion("ktlintCliVersion")
                    ktlint(ktlintCliVersion)

                    endWithNewline()
                }

                kotlinGradle {
                    target("*.gradle.kts")
                    ktlint()
                }

                isEnforceCheck = false
            }
        }
    }
}