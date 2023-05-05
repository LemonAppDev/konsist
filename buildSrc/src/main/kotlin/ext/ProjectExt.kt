package ext

import enu.ReleaseTarget
import org.gradle.api.Project
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.*

fun Project.getLocalPropertyOrGradleProperty(propertyName: String) =
    gradleLocalProperty(propertyName) ?: getProjectProperty(propertyName)

fun Project.getKonsistVersion(releaseTarget: ReleaseTarget): String {
    val version = getLocalPropertyOrGradleProperty("konsist.version") ?: error("konsist.version is not provided.")

    return when (releaseTarget) {
        ReleaseTarget.LOCAL -> "$version-SNAPSHOT"
        ReleaseTarget.SNAPSHOT -> "$version-SNAPSHOT"
        ReleaseTarget.RELEASE -> version
    }
}

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
