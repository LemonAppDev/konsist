package com.lemonappdev.konsist.buildlogic

import com.lemonappdev.konsist.buildlogic.config.ReleaseTarget
import com.lemonappdev.konsist.buildlogic.ext.getLocalPropertyOrGradleProperty
import com.lemonappdev.konsist.buildlogic.ext.getReleaseTarget
import nmcp.NmcpAggregationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure

/**
 * Uploads publications of projects added to the `nmcpAggregation` configuration to the Central Portal
 * (https://central.sonatype.com/).
 *
 * Registers `publish` task, so `./gradlew publish -Pkonsist.releaseTarget=...` publishes to the target repository.
 */
class PublishAggregationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.gradleup.nmcp.aggregation")

            configure<NmcpAggregationExtension> {
                centralPortal {
                    // Central Portal user token (https://central.sonatype.com/account)
                    getLocalPropertyOrGradleProperty("konsist.mavenCentralUsername")?.let { username.set(it) }
                    getLocalPropertyOrGradleProperty("konsist.mavenCentralPassword")?.let { password.set(it) }
                    publishingType.set("AUTOMATIC")
                }
            }

            tasks.register("publish") {
                group = "publishing"
                description = "Publishes Konsist artifact to the repository defined by 'konsist.releaseTarget' property."

                when (getReleaseTarget()) {
                    ReleaseTarget.LOCAL -> Unit
                    ReleaseTarget.SNAPSHOT -> dependsOn("publishAggregationToCentralSnapshots")
                    ReleaseTarget.RELEASE -> dependsOn("publishAggregationToCentralPortal")
                }
            }
        }
    }
}
