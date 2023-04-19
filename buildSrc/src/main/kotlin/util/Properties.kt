package util

import org.gradle.api.Project
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.*

fun Project.getLocalPropertyOrGradleProperty(propertyName: String): String? {
    val property = gradleLocalProperty(propertyName) ?: getProjectProperty(propertyName)

    if (property == null) {
        logger.warn("Property $propertyName not found.")
    }

    return property
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
