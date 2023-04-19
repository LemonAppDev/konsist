package util

import org.gradle.api.Project
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.*

fun Project.getLocalOrGradleProperty(propertyName: String) = gradleLocalProperty(propertyName) ?: getProjectProperty(propertyName)

private fun Project.getProjectProperty(propertyName: String): String? = properties[propertyName] as String?

private fun gradleLocalProperty(propertyName: String): String? {
    val localProperties = Properties()
    val localPropertiesFile = File("local.properties")

    if (localPropertiesFile.isFile) {
        InputStreamReader(FileInputStream(localPropertiesFile), Charsets.UTF_8).use { reader ->
            localProperties.load(reader)
        }
    }

    return localProperties.getProperty(propertyName)
}
