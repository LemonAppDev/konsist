package com.lemonappdev.konsist.buildlogic

import com.lemonappdev.konsist.buildlogic.ext.getKonsistVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.withType
import org.jetbrains.dokka.gradle.DokkaTask

class DokkaConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "org.jetbrains.dokka")

            tasks.withType<DokkaTask>().configureEach {
                moduleName.set("Konsist ${project.getKonsistVersion()}")
                failOnWarning.set(true)

                dokkaSourceSets.configureEach {
                    perPackageOption {
                        matchingRegex.set("com.lemonappdev.konsist.api.*")
                        suppress.set(false)
                    }

                    perPackageOption {
                        matchingRegex.set(".*")
                        suppress.set(true)
                    }
                }
            }
        }
    }
}