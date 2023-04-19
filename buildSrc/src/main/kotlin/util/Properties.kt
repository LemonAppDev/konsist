package util

import org.gradle.api.Project
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.Properties

fun Project.getLocalOrGradleProperty(propertyName: String): String? {
    println("getProjectProperty name: $propertyName, value ${getProjectProperty(propertyName)}")
    println("gradleLocalProperty name: $propertyName, value ${gradleLocalProperty(propertyName)}")
    return gradleLocalProperty(propertyName) ?: getProjectProperty(propertyName)
}

private fun Project.getProjectProperty(propertyName: String): String? = properties[propertyName] as String?

private fun gradleLocalProperty(propertyName: String): String? {
    val properties = Properties()
    val localProperties = File("local.properties")

    if (localProperties.isFile) {
        InputStreamReader(FileInputStream(localProperties), Charsets.UTF_8).use { reader ->
            properties.load(reader)
        }
    } else {
        error("File from not found")
    }

    return properties.getProperty(propertyName)
}
