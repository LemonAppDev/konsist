package com.lemonappdev.konsist.core.filesystem

import java.io.File
import java.util.Optional
import java.util.concurrent.ConcurrentHashMap

/**
 * Detects module directories. Module directory contains a Gradle or Maven build file (e.g. "build.gradle.kts",
 * "settings.gradle.kts", "pom.xml") or the "src" directory.
 * Results are cached, because many files reside in the same directories.
 */
internal object ModuleDirectoryDetector {
    private val moduleDirectories = ConcurrentHashMap<File, Optional<String>>()

    private val moduleDirectoryMarkers =
        listOf(
            "build.gradle",
            "build.gradle.kts",
            "settings.gradle",
            "settings.gradle.kts",
            "pom.xml",
            "src",
        )

    /**
     * Returns the closest module directory containing the directory with given [projectPath] (relative to
     * [rootProjectPath], "/" separators, e.g. "/feature/config", or "" for the project root directory), e.g. "/feature".
     * Returns null if the directory does not exist in the project or no module directory contains it.
     */
    fun getModuleDirectory(
        rootProjectPath: String,
        projectPath: String,
    ): String? =
        moduleDirectories
            .getOrPut(File(rootProjectPath, projectPath)) {
                Optional.ofNullable(findModuleDirectory(rootProjectPath, projectPath))
            }.orElse(null)

    private fun findModuleDirectory(
        rootProjectPath: String,
        projectPath: String,
    ): String? {
        if (!File(rootProjectPath, projectPath).isDirectory) {
            return null
        }

        var directory = projectPath

        while (!isModuleDirectory(File(rootProjectPath, directory))) {
            if (directory.isEmpty()) {
                return null
            }

            directory = directory.substringBeforeLast('/', "")
        }

        return directory
    }

    private fun isModuleDirectory(directory: File): Boolean = moduleDirectoryMarkers.any { File(directory, it).exists() }
}
