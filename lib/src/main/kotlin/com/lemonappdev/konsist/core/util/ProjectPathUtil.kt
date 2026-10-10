package com.lemonappdev.konsist.core.util

import com.lemonappdev.konsist.core.ext.toMacOsSeparator

/**
 * Path rules used to create the project scope.
 *
 * Paths are compared segment by segment (no regex), so special characters in the project root path, module name or
 * source set name are matched literally.
 */
internal object ProjectPathUtil {
    private const val SEPARATOR = "/"
    private const val SOURCE_DIR = "src"
    private const val ROOT_MODULE_NAME = "root"
    private const val NODE_MODULES_DIR = "node_modules"

    /**
     * Gradle and Maven output directories, used to store the results of the build process such as compiled code,
     * packaged artifacts and generated sources (e.g. "build/generated", "target/generated-sources").
     */
    private val buildOutputDirs = setOf("build", "target")

    /**
     * Determines whether the directory should be skipped when scanning the project for Kotlin files.
     *
     * Only the part of the [directoryPath] relative to the [projectRootPath] is checked, so the project itself can be
     * located inside a directory matching these rules (e.g. a hidden directory).
     *
     * The directory is ignored when it is (or is located inside):
     * - a hidden directory (name starts with "."), e.g. ".git", ".gradle", ".idea" or a git worktree created inside
     * the project
     * - a "node_modules" directory
     * - a build output directory ("build" for Gradle, "target" for Maven) located outside the "src" directory, so
     * "app/build" is ignored, but the "build" package in "app/src/main/kotlin/com/build" is not.
     *
     * @param projectRootPath The absolute path to the project root directory.
     * @param directoryPath The absolute path to the directory located inside the project root directory.
     * @return `true` if the directory should be skipped, `false` otherwise.
     */
    fun isIgnoredDirectory(
        projectRootPath: String,
        directoryPath: String,
    ): Boolean {
        val segments =
            directoryPath
                .toMacOsSeparator()
                .removePrefix(projectRootPath.toMacOsSeparator())
                .split(SEPARATOR)
                .filter { it.isNotEmpty() }

        return segments.any { it.startsWith(".") || it == NODE_MODULES_DIR } || isBuildOutputDirectory(segments)
    }

    /**
     * Determines whether the file belongs to the given module and source set.
     *
     * @param projectPath The path to the file relative to the project root directory.
     * @param moduleName The name of the module ("root" for the root module). If null, all modules are matched.
     * @param sourceSetName The name of the source set. If null, all source sets are matched.
     * @return `true` if the file belongs to the given module and source set, `false` otherwise.
     */
    fun resideInModuleAndSourceSet(
        projectPath: String,
        moduleName: String?,
        sourceSetName: String?,
    ): Boolean {
        if (moduleName == null && sourceSetName == null) {
            return true
        }

        val path = SEPARATOR + projectPath.toMacOsSeparator().removePrefix(SEPARATOR)

        val sourceSetPath =
            if (sourceSetName != null) {
                "$SEPARATOR$SOURCE_DIR$SEPARATOR${sourceSetName.toMacOsSeparator()}$SEPARATOR"
            } else {
                "$SEPARATOR$SOURCE_DIR$SEPARATOR"
            }

        return when (moduleName) {
            null -> path.contains(sourceSetPath)
            ROOT_MODULE_NAME -> path.startsWith(sourceSetPath)
            else -> path.startsWith("$SEPARATOR${moduleName.toMacOsSeparator()}$sourceSetPath")
        }
    }

    private fun isBuildOutputDirectory(segments: List<String>): Boolean {
        val buildOutputDirIndex = segments.indexOfFirst { it in buildOutputDirs }
        val sourceDirIndex = segments.indexOf(SOURCE_DIR)

        return buildOutputDirIndex != -1 && (sourceDirIndex == -1 || buildOutputDirIndex < sourceDirIndex)
    }
}
