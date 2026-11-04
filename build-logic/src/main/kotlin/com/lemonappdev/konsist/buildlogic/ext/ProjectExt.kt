package com.lemonappdev.konsist.buildlogic.ext

import com.lemonappdev.konsist.buildlogic.config.ReleaseTarget
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Properties

fun Project.getLocalPropertyOrGradleProperty(propertyName: String) = gradleLocalProperty(propertyName) ?: getProjectProperty(propertyName)

fun Project.getFullKonsistVersion(releaseTarget: ReleaseTarget): String {
    val version = getLocalPropertyOrGradleProperty("konsist.version") ?: error("konsist.version is not provided.")

    // Optional qualifier separates snapshots not published from develop (e.g. "pr2016" -> 1.0.0-pr2016-SNAPSHOT)
    val snapshotQualifier = getLocalPropertyOrGradleProperty("konsist.snapshotQualifier")?.let { "-$it" }.orEmpty()

    return when (releaseTarget) {
        ReleaseTarget.LOCAL -> "$version-SNAPSHOT"
        ReleaseTarget.SNAPSHOT -> "$version$snapshotQualifier-SNAPSHOT"
        ReleaseTarget.RELEASE -> version
    }
}

fun Project.getKonsistVersion() = getLocalPropertyOrGradleProperty("konsist.version") ?: error("konsist.version is not provided.")

private fun Project.getProjectProperty(propertyName: String): String? = properties[propertyName] as String?

private fun Project.gradleLocalProperty(propertyName: String): String? {
    val localProperties = Properties()
    val localPropertiesFile = File("$rootDir/local.properties")

    if (localPropertiesFile.isFile) {
        InputStreamReader(FileInputStream(localPropertiesFile), Charsets.UTF_8).use { reader ->
            localProperties.load(reader)
        }
    }

    return localProperties.getProperty(propertyName)
}

fun Project.getReleaseTarget(): ReleaseTarget {
    val releaseTargetStr = getLocalPropertyOrGradleProperty("konsist.releaseTarget")

    return ReleaseTarget
        .values()
        .firstOrNull { it.value == releaseTargetStr }
        ?: ReleaseTarget.LOCAL
}

/**
 * Fails Central Portal upload tasks registered by nmcp for given publication kind (e.g. "Aggregation") when
 * 'konsist.releaseTarget' does not match the target repository, so snapshot can't be published as release and vice versa.
 */
fun Project.requireReleaseTargetForNmcpPublishTasks(kind: String) {
    val releaseTarget = getReleaseTarget()

    mapOf(
        "nmcpPublish${kind}ToCentralPortal" to ReleaseTarget.RELEASE,
        "nmcpPublish${kind}ToCentralPortalSnapshots" to ReleaseTarget.SNAPSHOT,
    ).forEach { (taskName, requiredReleaseTarget) ->
        tasks.named(taskName) {
            doFirst {
                check(releaseTarget == requiredReleaseTarget) {
                    "Task '$taskName' requires '-Pkonsist.releaseTarget=${requiredReleaseTarget.value}', " +
                        "but release target is '${releaseTarget.value}'."
                }
            }
        }
    }
}

fun Project.getTomlVersion(versionName: String): String {
    val catalogs = extensions.getByType<VersionCatalogsExtension>()
    val libs = catalogs.named("libs")
    return checkNotNull(libs.findVersion(versionName).get().toString()) {
        "Version '$versionName' does not exist in the libs.versions.toml file."
    }
}
