package com.lemonappdev.konsist.core.filesystem

import java.io.File

/**
 * Determines which directories are skipped while scanning the project for Kotlin files.
 *
 * Ignored directories are pruned during the file tree walk, so their content is never visited. A directory is ignored
 * when its path (relative to the project root) contains:
 * - a hidden directory (name starting with "."), e.g. ".git", ".gradle", ".idea", ".claude" (may contain git worktrees
 *   with full copies of the project)
 * - a "node_modules" directory
 * - a build output directory ("build" for Gradle, "target" for Maven) located outside the "src" directory
 *   (e.g. "app/build") or in the module directory (see [ModuleDirectoryDetector.isModuleDirectory]) located in the
 *   "src" directory (e.g. "src/app/build" for module located in a directory named "src" and
 *   "app/src/test/resources/project/build" for Gradle test fixture project).
 *   Packages named "build" or "target" (e.g. "app/src/main/kotlin/com/build") are not ignored.
 *
 * [isModuleDirectory] returns true if the given directory is a module directory. It is called only for build output
 * directories located in the "src" directory, so other directories are not checked on the file system.
 */
internal class ProjectDirectoryFilter(
    private val projectRootDir: File,
    private val isModuleDirectory: (File) -> Boolean = ModuleDirectoryDetector::isModuleDirectory,
) {
    fun isIgnored(directory: File): Boolean {
        val segments =
            directory
                .relativeTo(projectRootDir)
                .invariantSeparatorsPath
                .split('/')

        val sourceDirIndex = segments.indexOf(SOURCE_DIR).takeIf { it != -1 } ?: segments.size

        return segments.withIndex().any { (index, segment) ->
            isHiddenDirectory(segment) ||
                segment == NODE_MODULES_DIR ||
                (segment in BUILD_OUTPUT_DIRS && (index < sourceDirIndex || isLocatedInModuleDirectory(segments, index)))
        }
    }

    private fun isHiddenDirectory(name: String) = name.startsWith('.')

    /*
     * Returns true if the parent directory of the directory at given [index] of [segments] is a module directory.
     */
    private fun isLocatedInModuleDirectory(
        segments: List<String>,
        index: Int,
    ): Boolean = isModuleDirectory(File(projectRootDir, segments.take(index).joinToString("/")))

    companion object {
        private const val SOURCE_DIR = "src"
        private const val NODE_MODULES_DIR = "node_modules"
        private const val GRADLE_BUILD_DIR = "build"
        private const val MAVEN_BUILD_DIR = "target"
        private val BUILD_OUTPUT_DIRS = setOf(GRADLE_BUILD_DIR, MAVEN_BUILD_DIR)
    }
}
