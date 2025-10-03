package com.lemonappdev.konsist.buildlogic

import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.register
import org.gradle.kotlin.dsl.repositories

class DetektConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "io.gitlab.arturbosch.detekt")

            repositories {
                mavenCentral()
            }

            tasks.register<Detekt>("detektCheck") {
                description = "Checks that sourcecode satisfies detekt rules."
                autoCorrect = false
                group = "verification"
                parallel = true
                ignoreFailures = false
                setSource(file(rootDir))

                config.setFrom("$rootDir/detekt.yml")
                buildUponDefaultConfig = true

                include("**/*.kt", "**/*.kts")
                exclude(
                    "**/resources/**",
                    "**/build/**",
                    "**/target/**",
                    "**/generated/**",
                    "**/samples/starter-projects/**",
                )

                reports {
                    html.required.set(true)
                    xml.required.set(true)
                }
            }

            tasks.register<Detekt>("detektApply") {
                description = "Applies code formatting rules to sourcecode in-place."
                autoCorrect = true
                group = "verification"
                parallel = true
                ignoreFailures = false
                setSource(file(rootDir))

                config.setFrom("$rootDir/detekt.yml")
                buildUponDefaultConfig = true

                include("**/*.kt", "**/*.kts")
                exclude(
                    "**/resources/**",
                    "**/build/**",
                    "**/target/**",
                    "**/generated/**",
                    "**/samples/starter-projects/**",
                )

                reports {
                    html.required.set(true)
                    xml.required.set(true)
                }
            }
        }
    }
}