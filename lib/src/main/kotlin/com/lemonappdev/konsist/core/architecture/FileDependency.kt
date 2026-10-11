package com.lemonappdev.konsist.core.architecture

/**
 * Code element through which a file depends on a declaration from another package.
 *
 * @property type The type of the dependency.
 * @property name The fully qualified name of the referenced declaration, e.g. "com.app.data.Repository".
 * @property location The location of the dependency in the file, e.g. "/project/src/Sample.kt:3:1".
 */
internal data class FileDependency(
    val type: FileDependencyType,
    val name: String,
    val location: String,
)

internal enum class FileDependencyType(
    val displayName: String,
) {
    /**
     * Import directive, e.g. `import com.app.data.Repository`.
     */
    IMPORT("Import"),

    /**
     * Fully qualified reference written in code without an import, e.g. `com.app.data.Repository()`.
     */
    REFERENCE("Reference"),
}
