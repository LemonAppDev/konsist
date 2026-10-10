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
 * - a build output directory ("build" for Gradle, "target" for Maven) located outside the "src" directory.
 *   Packages named "build" or "target" inside the "src" directory are not ignored.
 */
internal class ProjectDirectoryFilter(
    private val projectRootDir: File,
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
                (index < sourceDirIndex && segment in BUILD_OUTPUT_DIRS)
        }
    }

    private fun isHiddenDirectory(name: String) = name.startsWith('.')

    companion object {
        private const val SOURCE_DIR = "src"
        private const val NODE_MODULES_DIR = "node_modules"
        private const val GRADLE_BUILD_DIR = "build"
        private const val MAVEN_BUILD_DIR = "target"
        private val BUILD_OUTPUT_DIRS = setOf(GRADLE_BUILD_DIR, MAVEN_BUILD_DIR)
    }
}
